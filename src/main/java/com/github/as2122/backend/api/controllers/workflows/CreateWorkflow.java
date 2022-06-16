package com.github.as2122.backend.api.controllers.workflows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/createWorkflow")
    public String createWorkflow(@RequestBody String body) {
        CreateWorkflowRequest createWorkflowRequest = jsonParser.fromJson(body, CreateWorkflowRequest.class);
        workflowManager.createWorkflow(new Workflow(createWorkflowRequest.getName(), createWorkflowRequest.getSteps()));
        System.out.println(workflowManager.getWorkflow(1));
        return jsonParser.toJson(new WorkflowResponse("accepted"));
    }
}
