package com.github.as2122.backend.api.controllers.workflows;

import com.github.as2122.backend.workflows.WorkflowStep;

public class CreateWorkflowRequest {
    private final String name;
    private final WorkflowStep[] steps;

    public CreateWorkflowRequest(String name, WorkflowStep[] steps) {
        this.name = name;
        this.steps = steps;
    }

    public WorkflowStep[] getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }
}