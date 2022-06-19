package com.github.as2122.backend.api.controllers.task;

import com.github.as2122.backend.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.accounts.AccountLevel;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.AssignTaskResponse;
import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.tasks.Task;
import com.github.as2122.backend.tasks.TaskManager;
import com.google.gson.Gson;

@RestController
public class AssignTask {
    private final Gson gsonParser;
    private final AccountManagerInterface accountManagerInterface;
    private final TaskManager taskManager;

    public AssignTask(@Autowired Gson gsonParser, @Autowired AccountManagerInterface accountManager, @Autowired TaskManager taskManager) {
        this.gsonParser = gsonParser;
        this.accountManagerInterface = accountManager;
        this.taskManager = taskManager;
    }

    @GetMapping("/assignTask")
    public String assignTask(String token, String name, String start, String end, String priority, String description, String assignee_id) {
        if (name == null || name.isBlank())
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        if (start == null || start.isBlank())
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        if (end == null || end.isBlank())
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        if (priority == null || priority.isBlank())
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        if (description == null || description.isBlank())
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        if (assignee_id == null || assignee_id.isBlank())
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));

        final Account acc = accountManagerInterface.getByName(accountManagerInterface.getByToken(token));
        if (acc.getLevel() == AccountLevel.EMPLOYEE && !assignee_id.equals("me")) {
            return gsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        } // if employee tries to assign a task to someone other than themselves

        if (assignee_id.equals("me"))
            assignee_id = acc.getId();

        taskManager.assignTask(new Task(name, start, end, priority, description, assignee_id));
        
        return gsonParser.toJson(new AssignTaskResponse("accepted"));
    }
}
