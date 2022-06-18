package com.github.as2122.backend.api.responses;

import java.util.List;

import com.github.as2122.backend.files.File;
import com.github.as2122.backend.workflows.WorkflowStep;

public class GetWorkflowResponse extends Response {
    private final String name;
    private final int progress;
    private final WorkflowStep[] steps;
    private final List<File> files;

    public GetWorkflowResponse(String status, String name, WorkflowStep[] steps, List<File> files, int progress) {
        super(status);
        this.name = name;
        this.progress = progress;
        this.steps = steps;
        this.files = files;
    }

    public GetWorkflowResponse(String status, String name, WorkflowStep[] steps, List<File> files) {
        this(status, name, steps, files, 0);
    }
}
