package com.github.as2122.backend.workflows;

public class WorkflowStep {
    private final int id;
    private final String description;
    private String fileName;

    public WorkflowStep(int id, String description) {
        this(id, description, null);
    }

    public WorkflowStep(int id, String description, String fileName) {
        this.id = id;
        this.description = description;
        this.fileName = fileName;
    }

    public int getId() {
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
}
