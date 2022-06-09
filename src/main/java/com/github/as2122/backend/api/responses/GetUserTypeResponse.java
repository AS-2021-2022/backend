package com.github.as2122.backend.api.responses;

public class GetUserTypeResponse extends Response {

    private final String type;

    public GetUserTypeResponse(String status, String type) {
        super(status);
        this.type = type;
    }
}
