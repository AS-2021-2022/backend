package com.github.as2122.backend.api.responses;

import com.github.as2122.backend.workflows.UserWorkflow;

import java.util.ArrayList;
import java.util.List;

public class WorkflowResponse extends Response {
    private final List<UserWorkflow> workflows;

    public WorkflowResponse(String status) {
        super(status);
        workflows = new ArrayList<>();
    }
    
    public WorkflowResponse(String status, List<UserWorkflow> workflows) {
        super(status);
        this.workflows = workflows;
    }

    public List<UserWorkflow> getWorkflows() {
        return workflows;
    }
}
