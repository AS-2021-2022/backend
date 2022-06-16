package com.github.as2122.backend.api.responses;

import java.util.List;

public class GetFilesListResponse extends Response {
    private final List<String> files;

    public GetFilesListResponse(String status, List<String> files) {
        super(status);
        this.files = files;
    }
}
