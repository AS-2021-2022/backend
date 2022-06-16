package com.github.as2122.backend.api.responses;

import com.github.as2122.backend.workflows.WorkflowStep;

public class GetWorkflowResponse extends Response {
    private final String name;
    private final WorkflowStep[] steps;
    private final int[] files;
    private final int progress;

    public GetWorkflowResponse(String status, String name, WorkflowStep[] steps, int[] files, int progress) {
        super(status);
        this.name = name;
        this.steps = steps;
        this.files = files;
        this.progress = progress;
    }

    public GetWorkflowResponse(String status, String name, WorkflowStep[] steps, int[] files) {
        this(status, name, steps, files, 0);
    }
}
