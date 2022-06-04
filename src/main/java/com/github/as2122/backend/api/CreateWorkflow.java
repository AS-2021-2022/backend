package com.github.as2122.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkflow {
    @GetMapping("/createWorkflow")
    public String createWorkflow(String request) {
        return "";
    }
}
