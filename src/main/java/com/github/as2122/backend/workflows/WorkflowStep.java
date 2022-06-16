package com.github.as2122.backend.workflows;

public class WorkflowStep {
    private final String id;
    private final String description;

    public WorkflowStep(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "WorkflowStep {" + id + ", " + description + "}";
    }
}
