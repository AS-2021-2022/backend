package com.github.as2122.backend.api.controllers.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.responses.GetTaskResponse;
import com.github.as2122.backend.api.responses.SimpleResponse;
import com.github.as2122.backend.tasks.Task;
import com.github.as2122.backend.tasks.TaskManager;
import com.google.gson.Gson;

@RestController
public class GetTask {
    private final Gson gsonParser;
    private final TaskManager taskManager;

    public GetTask(@Autowired Gson gsonParser, @Autowired TaskManager taskManager) {
        this.gsonParser = gsonParser;
        this.taskManager = taskManager;
    }

    @GetMapping("/getTask")
    public String getTask(String token, int id) {
        Task task = taskManager.getTask(id);
        String rejectedResponse = gsonParser.toJson(new SimpleResponse("rejected", "Incomplete fields"));
        if (task == null) {
            return rejectedResponse;
        }
        String startDate = task.getStartDate();
        String endDate = task.getEndDate();
        String priority = task.getPriority();
        String description = task.getDescription();
        if (startDate == null || startDate.isBlank())
            return rejectedResponse;
        if (endDate == null || endDate.isBlank())
            return rejectedResponse;
        if (priority == null || priority.isBlank())
            return rejectedResponse;
        if (description == null || description.isBlank())
            return rejectedResponse;
        return gsonParser.toJson(new GetTaskResponse("accepted", startDate, endDate, priority, description));
    }
}
