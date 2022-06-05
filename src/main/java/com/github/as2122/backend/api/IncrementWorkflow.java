package com.github.as2122.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.requests.IncrementWorkflowRequest;
import com.github.as2122.backend.api.requests.Request;
import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;

@RestController
public class IncrementWorkflow {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;

    public IncrementWorkflow(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
    }

    @GetMapping("/incrementWorkflow")
    public String incrementWorkflow(String request) {
        IncrementWorkflowRequest incrementWorkflowRequest = (IncrementWorkflowRequest) jsonParser.fromJson(request, Request.class).getParams();
        // assumes file is mandatory to increment workflow
        if (incrementWorkflowRequest == null) {
            return jsonParser.toJson(new WorkflowResponse("rejected"));
        }
        if (!workflowManager.incrementWorkflow(incrementWorkflowRequest.getId(), incrementWorkflowRequest.getName())) {
            return jsonParser.toJson(new WorkflowResponse("rejected"));
        } // missing user permission check, need user id in request?
        return jsonParser.toJson(new WorkflowResponse("accepted"));
    }
}
