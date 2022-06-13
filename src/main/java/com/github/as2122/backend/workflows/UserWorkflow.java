package com.github.as2122.backend.workflows;

public class UserWorkflow {
    private final Integer workflow;
    private final String name;
    private final boolean pending;
    private final int id;

    public UserWorkflow(Integer workflow, String name, boolean pending, int id) {
        this.workflow = workflow;
        this.name = name;
        this.pending = pending;
        this.id = id;
    }

    public UserWorkflow(String name, boolean pending, int id) {
        this(null, name, pending, id);
    }

    public Integer getWorkflow() {
        return workflow;
    }

    public String getName() {
        return name;
    }

    public boolean getPending() {
        return pending;
    }

    public int getId() {
        return id;
    }
}
