package com.github.as2122.backend.api.controllers.workflows;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.responses.GetWorkflowResponse;
import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.files.File;
import com.github.as2122.backend.workflows.Workflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.github.as2122.backend.workflows.WorkflowStep;
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
        final Workflow workflow = workflowManager.getWorkflow(id);
        String rejectedResponse = jsonParser.toJson(new SimpleResponse("rejected", "Incomplete fields"));
        if (workflow == null) {
            return rejectedResponse; // Invalid workflow id
        }
        String name = workflow.getName();
        WorkflowStep[] steps = workflow.getSteps();
        if (name == null || name.isBlank())
            return rejectedResponse;
        if (steps == null || steps.length == 0)
            return rejectedResponse;
        return jsonParser.toJson(new GetWorkflowResponse("accepted", name, steps, workflow.getFiles(), workflow.getStep()));
    }
}
