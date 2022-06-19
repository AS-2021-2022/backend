package com.github.as2122.backend.api.responses;

public abstract class Response {
    private final String status;
    private final String info;

    protected Response(String status, String info) {
        this.status = status;
        this.info = info;
    }

    protected Response(String status) {
        this(status, null);
    }

    public String getStatus() {
        return status;
    }
}
