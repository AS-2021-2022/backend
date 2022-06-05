package com.github.as2122.backend.api.requests;

public class GetWorkflowRequest implements RequestQuery {
    private final int id;

    public GetWorkflowRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
