package com.github.as2122.backend.api.responses;

public class GetTaskResponse extends Response {
    private final String start;
    private final String end;
    private final String priority;
    private final String description;

    public GetTaskResponse(String status, String start, String end, String priority, String description) {
        super(status);
        this.start = start;
        this.end = end;
        this.priority = priority;
        this.description = description;
    }

    public GetTaskResponse(String status) {
        super(status);
        this.start = null;
        this.end = null;
        this.priority = null;
        this.description = null;
    }
}
