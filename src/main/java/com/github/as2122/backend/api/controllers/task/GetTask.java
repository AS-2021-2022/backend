package com.github.as2122.backend.api.controllers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.GetTaskResponse;
import com.github.as2122.backend.tasks.Task;
import com.github.as2122.backend.tasks.TaskManager;
import com.google.gson.Gson;

@RestController
public class GetTask {
    private final Gson gsonParser;
    private final AccountManagerInterface accountManagerInterface;
    private final TaskManager taskManager;

    public GetTask(@Autowired Gson gsonParser, @Autowired AccountManagerInterface accountManagerInterface, @Autowired TaskManager taskManager) {
        this.gsonParser = gsonParser;
        this.accountManagerInterface = accountManagerInterface;
        this.taskManager = taskManager;
    }

    @GetMapping("/getTask")
    public String getTask(String token, int id) {
        Task task = taskManager.getTask(id);
        return task != null ?
                gsonParser.toJson(new GetTaskResponse("accepted", task.getStartDate(), task.getEndDate(), task.getPriority(), task.getDescription()))
                : gsonParser.toJson(new GetTaskResponse("rejected"));
    }
}
