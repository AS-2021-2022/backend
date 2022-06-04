package com.github.as2122.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncrementWorkflow {
    @GetMapping("/incrementWorkflow")
    public String incrementWorkflow(String request) {
        return "";
    }
}
