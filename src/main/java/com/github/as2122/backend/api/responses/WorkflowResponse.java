package com.github.as2122.backend.api.responses;

import com.github.as2122.backend.workflows.UserWorkflow;

public class WorkflowResponse extends Response {
    private final UserWorkflow[] workflows;

    public WorkflowResponse(String status) {
        super(status);
        workflows = new UserWorkflow[0];
    }
    
    public WorkflowResponse(String status, UserWorkflow[] workflows) {
        super(status);
        this.workflows = workflows;
    }

    public UserWorkflow[] getWorkflows() {
        return workflows;
    }
}
