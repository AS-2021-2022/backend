package com.github.as2122.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.requests.GetWorkflowRequest;
import com.github.as2122.backend.api.requests.Request;
import com.github.as2122.backend.api.responses.GetWorkflowResponse;
import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.Workflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;

@RestController
public class GetWorkflow {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;

    public GetWorkflow(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
    }

    @GetMapping("/getWorkflow")
    public String getWorkflow(String token, int id) {
//        GetWorkflowRequest getWorkflowRequest = (GetWorkflowRequest) jsonParser.fromJson(request, Request.class).getParams();
//        if (getWorkflowRequest == null) {
//            return jsonParser.toJson(new WorkflowResponse("rejected"));
//        }
        final Workflow workflow = workflowManager.getWorkflow(id);
        if (workflow == null) {
            return jsonParser.toJson(new WorkflowResponse("rejected")); // Invalid workflow id
        }
        return jsonParser.toJson(new GetWorkflowResponse("accepted", workflow.getName(), workflow.getSteps(), workflow.getFiles()));
    }
}
