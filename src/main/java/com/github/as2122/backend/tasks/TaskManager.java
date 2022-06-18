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
        tasks.put(0, new Task("Prove P=NP", "2022-06-24T17:22:01.999+00:00", "2022-06-24T23:59:59.999+00:00", "urgent", "user1@nsn.pt"));
    }

    public List<TasksList> getTasksListFromUser(String user) {
        List<TasksList> res = new ArrayList<>();
        for (Integer key : tasks.keySet()) {
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

    public boolean concludeTask(int id) {
        if (!tasks.containsKey(id)) {
            return false;
        }
        tasks.remove(id);
        return true;
    }
}
