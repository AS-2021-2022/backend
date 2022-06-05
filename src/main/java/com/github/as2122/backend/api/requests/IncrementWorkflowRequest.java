package com.github.as2122.backend.api.requests;

public class IncrementWorkflowRequest implements RequestQuery {
    private final String name;
    private final int id;

    public IncrementWorkflowRequest(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
