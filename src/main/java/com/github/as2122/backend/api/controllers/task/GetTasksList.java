package com.github.as2122.backend.api.controllers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.GetTasksListResponse;
import com.github.as2122.backend.tasks.TaskManager;
import com.google.gson.Gson;

@RestController
public class GetTasksList {
    private final Gson jsonParser;
    private final AccountManagerInterface accountManagerInterface;
    private final TaskManager taskManager;

    public GetTasksList(@Autowired Gson jsonParser, @Autowired AccountManagerInterface accountManagerInterface, @Autowired TaskManager taskManager) {
        this.jsonParser = jsonParser;
        this.accountManagerInterface = accountManagerInterface;
        this.taskManager = taskManager;
    }

    @GetMapping("/getTasksList")
    public String getTasksList(String token) {
        String user = accountManagerInterface.getByName(accountManagerInterface.getByToken(token)).getId();
        return jsonParser.toJson(new GetTasksListResponse("accepted", taskManager.getTasksListFromUser(user)));
    }
}
