package com.github.as2122.backend.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.springframework.stereotype.Component;

// @Component
public class TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();

    public TaskManager() {
        tasks.put(0, new Task("Babagee", "15/06/2022", "22/06/2022", "urgent", "user1@nsn.pt"));
    }

    public List<TasksList> getTasksListFromUser(String user) {
        List<TasksList> res = new ArrayList<>();
        for (Integer key: tasks.keySet()) {
            Task task = tasks.get(key);
            if (task.getAssignee().equals(user)) {
                res.add(new TasksList(task.getName(), key, task.getTimeLeft()));
            }
        }
        return res;
    }

    public Task getTask(int id) {
        return tasks.containsKey(id) ? tasks.get(id) : null;
    }

    public Integer assignTask(Task task) {
        Integer key = tasks.size();
        tasks.put(key, task);
        return key;
    }
}
