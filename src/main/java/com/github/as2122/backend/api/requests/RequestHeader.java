package com.github.as2122.backend.api.requests;

public abstract class RequestHeader {
    private final String token;
    private final Request query;

    public RequestHeader(String token, Request query) {
        this.token = token;
        this.query = query;
    }
}
