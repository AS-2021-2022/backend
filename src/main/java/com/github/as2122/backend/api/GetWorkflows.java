package com.github.as2122.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.UserWorkflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;

@RestController
public class GetWorkflows {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;

    public GetWorkflows(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
    }
    
    @GetMapping("/getWorkflows")
    public String getWorkflows(String request) {
        int user = 69; // como ter o utilizador a partir do request???
        UserWorkflow[] userWorkflows = workflowManager.getUserWorkflows(user);
        return jsonParser.toJson(new WorkflowResponse("accepted", userWorkflows));
    }
}
