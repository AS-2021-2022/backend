package com.github.as2122.backend.workflows;

public class UserWorkflow {
    private final String name;
    private final boolean pending;
    private final int id;

    public UserWorkflow(String name, boolean pending, int id) {
        this.name = name;
        this.pending = pending;
        this.id = id;
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
