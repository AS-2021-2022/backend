package com.github.as2122.backend.chat;

public class Message {
    private final String origin;
    private final String text;

    public Message(String origin, String text) {
        this.origin = origin;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getOrigin() {
        return origin;
    }
}
