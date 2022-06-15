package com.github.as2122.backend.api.responses;

public class GetTaskResponse extends Response {
    private String start;
    private String end;
    private String priority;
    private String description;

    public GetTaskResponse(String status, String start, String end, String priority, String description) {
        super(status);
        this.start = start;
        this.end = end;
        this.priority = priority;
        this.description = description;
    }
}
