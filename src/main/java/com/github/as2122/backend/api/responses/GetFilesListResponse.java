package com.github.as2122.backend.api.responses;

import com.github.as2122.backend.files.File;

import java.util.List;

public class GetFilesListResponse extends Response {
    private final List<File> files;

    public GetFilesListResponse(String status, List<File> files) {
        super(status);
        this.files = files;
    }
}
