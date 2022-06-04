package com.github.as2122.backend.api.requests;

public abstract class Request {
    private final String token;
    private final String type;
    private final RequestQuery params;

    public Request(String token, String type, RequestQuery params) {
        this.token = token;
        this.type = type;
        this.params = params;
    }

    public RequestQuery getParams() {
        return params;
    }
}
