package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManager;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.as2122.backend.api.responses.WorkflowResponse;
import com.github.as2122.backend.workflows.UserWorkflow;
import com.github.as2122.backend.workflows.WorkflowManager;
import com.google.gson.Gson;

import java.util.List;

@RestController
public class GetWorkflows {
    private final Gson jsonParser;
    private final WorkflowManager workflowManager;
    private final AccountManagerInterface accountManager;

    public GetWorkflows(@Autowired Gson jsonParser, @Autowired WorkflowManager workflowManager, AccountManagerInterface accountManager) {
        this.jsonParser = jsonParser;
        this.workflowManager = workflowManager;
        this.accountManager = accountManager;
    }
    
    @GetMapping("/getWorkflows")
    public String getWorkflows(String token) {
        final String user = accountManager.getByToken(token); // como ter o utilizador a partir do request???
        final List<UserWorkflow> userWorkflows = workflowManager.getUserWorkflows(user);
        return jsonParser.toJson(new WorkflowResponse("accepted", userWorkflows));
    }
}
