package com.github.as2122.backend.workflows;

public class WorkflowStep {
    private final String id;
    private final String description;
    private String fileName;

    public WorkflowStep(String id, String description) {
        this(id, description, "");
    }

    public WorkflowStep(String id, String description, String fileName) {
        this.id = id;
        this.description = description;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String toString() {
        return "WorkflowStep {" + id + ", " + description + (fileName != null ? ", " + fileName : "") + "}";
    }
}
