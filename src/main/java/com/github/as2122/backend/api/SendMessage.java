package com.github.as2122.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {
    @GetMapping("/sendMessage")
    public String getMessage(String request) {
        return "";
    }
}
