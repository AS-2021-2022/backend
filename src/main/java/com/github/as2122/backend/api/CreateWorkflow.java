package com.github.as2122.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.requests.CreateWorkflowRequest;
import com.github.as2122.backend.api.requests.Request;
import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.Workflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;

@RestController
public class CreateWorkflow {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;

    public CreateWorkflow(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
    }

    @GetMapping("/createWorkflow")
    public String createWorkflow(String request) {
        CreateWorkflowRequest createWorkflowRequest = (CreateWorkflowRequest) jsonParser.fromJson(request, Request.class).getParams();
        if (createWorkflowRequest == null) {
            return jsonParser.toJson(new WorkflowResponse("rejected"));
        }
        String name = "request should send a name???";
        workflowManager.createWorkflow(name, new Workflow(name, createWorkflowRequest.getSteps()));
        return jsonParser.toJson(new WorkflowResponse("accepted"));
    }
}
