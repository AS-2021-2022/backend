package com.github.as2122.backend.api.responses;

public class SimpleResponse extends Response {
    public SimpleResponse(String status) {
        super(status);
    }

    public SimpleResponse(String status, String info) {
        super(status, info);
    }
}
