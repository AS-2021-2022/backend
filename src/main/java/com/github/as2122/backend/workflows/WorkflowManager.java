package com.github.as2122.backend.workflows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class WorkflowManager {
    private static final Map<String, Workflow> workflows = new HashMap<>();

    public Map<String, Workflow> getWorkflows() {
        return workflows;
    }

    public Workflow getWorkflow(String name) {
        if (workflows.containsKey(name)) {
            return workflows.get(name);
        }
        return null;
    }

    public boolean createWorkflow(String name, Workflow workflow) {
        if (workflows.containsKey(name)) {
            return false;
        }
        workflows.put(name, workflow);
        return true;
    }

    public boolean incrementWorkflow(String name, String fileName) {
        if (!workflows.containsKey(name)) {
            return false;
        }
        workflows.get(name).incrementWorkflow(fileName);
        return true;
    }

    public UserWorkflow[] getUserWorkflows(int user) {
        List<UserWorkflow> res = new ArrayList<>();
        for (Workflow wf: workflows.values()) {
            if (wf.userInWorkflow(user)) {
                res.add(new UserWorkflow(wf.getName(), wf.getPending(user), 420));
            }
        }
        return (UserWorkflow[]) res.toArray();
    }
}
