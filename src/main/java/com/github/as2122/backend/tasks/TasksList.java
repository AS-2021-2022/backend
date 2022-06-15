package com.github.as2122.backend.tasks;

public class TasksList {
    private String name;
    private int id;
    private long time_left;

    public TasksList(String name, int id, long timeLeft) {
        this.name = name;
        this.id = id;
        this.time_left = timeLeft;
    }
}
