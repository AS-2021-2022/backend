package com.github.as2122.backend.chat;

public class Contact {
    private final String name;
    private final String id;
    private final Status status;

    public Contact(String name, String id, Status status) {
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
