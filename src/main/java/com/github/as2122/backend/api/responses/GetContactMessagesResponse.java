package com.github.as2122.backend.api.responses;

import com.github.as2122.backend.chat.Message;

import java.util.List;

public class GetContactMessagesResponse extends Response {

    private final List<Message> messages;

    public GetContactMessagesResponse(String status, List<Message> messages) {
        super(status);
        this.messages = messages;
    }

//    public List<Message> getMessages() {
//        return messages;
//    }
}
