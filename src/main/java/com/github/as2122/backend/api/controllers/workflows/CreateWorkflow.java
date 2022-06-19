package com.github.as2122.backend.api.controllers.workflows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.Workflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.github.as2122.backend.workflows.WorkflowStep;
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
        String name = createWorkflowRequest.getName();
        WorkflowStep[] steps = createWorkflowRequest.getSteps();
        if (name == null || name.isBlank()) {
            return jsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        }
        if (steps == null || steps.length == 0) {
            return jsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
        }
        for (WorkflowStep step: steps) {
            String user = step.getId();
            if (user == null || user.isBlank()) {
                return jsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
            }
            String description = step.getDescription();
            if (description == null || description.isBlank()) {
                return jsonParser.toJson(new SimpleResponse("rejected", "Invalid fields"));
            }
        }
        workflowManager.createWorkflow(new Workflow(name, steps));
        return jsonParser.toJson(new WorkflowResponse("accepted"));
    }
}
