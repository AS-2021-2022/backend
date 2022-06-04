package com.github.as2122.backend.api.requests;

public class IncrementWorkflowRequest implements RequestQuery {
    private final String name;

    public IncrementWorkflowRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
