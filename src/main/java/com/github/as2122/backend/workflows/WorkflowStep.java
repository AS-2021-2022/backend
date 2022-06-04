package com.github.as2122.backend.workflows;

public class WorkflowStep {
    private final int id;
    private final String description;

    public WorkflowStep(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
