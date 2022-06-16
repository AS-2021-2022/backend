package com.github.as2122.backend.api.controllers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.ConcludeTaskResponse;
import com.github.as2122.backend.tasks.TaskManager;
import com.google.gson.Gson;

@RestController
public class ConcludeTask {
    private final Gson gsonParser;
    private final AccountManagerInterface accountManagerInterface;
    private final TaskManager taskManager;

    public ConcludeTask(@Autowired Gson gsonParser, @Autowired AccountManagerInterface accountManagerInterface, @Autowired TaskManager taskManager) {
        this.gsonParser = gsonParser;
        this.accountManagerInterface = accountManagerInterface;
        this.taskManager = taskManager;
    }

    @GetMapping("/concludeTask")
    public String concludeTask(String token, int id) {
        return taskManager.concludeTask(id) ? gsonParser.toJson(new ConcludeTaskResponse("accepted")) : gsonParser.toJson(new ConcludeTaskResponse("rejected"));
    }
}
