package com.github.as2122.backend.api;

import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncrementWorkflow {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;

    public IncrementWorkflow(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
    }

    @GetMapping("/incrementWorkflow")
    public String incrementWorkflow(String token, String name, int id) {
        // assumes file is mandatory to increment workflow
//        if (incrementWorkflowRequest == null) {
//            return jsonParser.toJson(new WorkflowResponse("rejected"));
//        }

        if (!workflowManager.incrementWorkflow(id, name)) {
            return jsonParser.toJson(new WorkflowResponse("rejected"));
        } // missing user permission check, need user id in request?
        return jsonParser.toJson(new WorkflowResponse("accepted"));
    }
}
