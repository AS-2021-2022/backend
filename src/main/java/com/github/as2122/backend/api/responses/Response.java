package com.github.as2122.backend.api.responses;

public abstract class Response {
    private final String status;

    protected Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
