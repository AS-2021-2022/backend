package com.github.as2122.backend.api.requests;

import com.github.as2122.backend.workflows.WorkflowStep;

public class CreateWorkflowRequest implements RequestQuery {
    private final WorkflowStep[] steps;

    public CreateWorkflowRequest(WorkflowStep[] steps) {
        this.steps = steps;
    }

    public WorkflowStep[] getSteps() {
        return steps;
    }
}
