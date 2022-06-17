package com.github.as2122.backend.api.responses;

public class UploadResponse extends Response {
    private final String id;

    public UploadResponse(String status, String id) {
        super(status);
        this.id = id;
    }
}
