package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.UserWorkflow;
import com.github.as2122.backend.workflows.Workflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncrementWorkflow {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;
    private final AccountManagerInterface accountManagerInterface;

    public IncrementWorkflow(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager, @Autowired AccountManagerInterface accountManagerInterface) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
        this.accountManagerInterface = accountManagerInterface;
    }

    @GetMapping("/incrementWorkflow")
    public String incrementWorkflow(String token, String name, int id) {
        // assumes file is mandatory to increment workflow
//        if (incrementWorkflowRequest == null) {
//            return jsonParser.toJson(new WorkflowResponse("rejected"));
//        }
        String user = (accountManagerInterface.getByName(accountManagerInterface.getByToken(token))).getId();
        /*
        List<UserWorkflow> userWorkflows = workflowManager.getUserWorkflows(user);
        if (!workflowManager.incrementWorkflow(id, name) || !userWorkflows.stream().anyMatch(x -> x.getWorkflow().equals(workflowManager.getWorkflow(id).getId()))) {
            return jsonParser.toJson(new WorkflowResponse("rejected"));
        }
        */
        return jsonParser.toJson(new WorkflowResponse("accepted"));
    }
}
