package com.github.as2122.backend.api.responses;

import java.util.List;

import com.github.as2122.backend.tasks.TasksList;

public class GetTasksListResponse extends Response {
    private List<TasksList> tasks;

    public GetTasksListResponse(String status, List<TasksList> tasks) {
        super(status);
        this.tasks = tasks;
    }
}
