package com.github.as2122.backend.workflows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.as2122.backend.files.FileManager;

//import org.springframework.stereotype.Component;

//@Component
public class WorkflowManager {
    @Autowired
    private FileManager fileManager;
    private final Map<Integer, Workflow> workflows = new HashMap<>();

    public WorkflowManager() {
        workflows.put(0, new Workflow("Seguro da empresa TBNS", new WorkflowStep[] {
                new WorkflowStep("user1@nsn.pt", "Provisionar formulário de adesão"), 
                new WorkflowStep("user2@nsn.pt", "Verificar estatuto da empresa"), 
                new WorkflowStep("user4@nsn.pt", "Calcular prejuízos e propor valor"),
                new WorkflowStep("user1@nsn.pt", "Notificar empresa")
            }));
    }

    public Map<Integer, Workflow> getWorkflows() {
        return workflows;
    }

    public Workflow getWorkflow(int workflow) {
        if (workflows.containsKey(workflow)) {
            return workflows.get(workflow);
        }
        return null;
    }

    public boolean createWorkflow(Workflow workflow) {
        int id = workflow.getId();
        if (workflows.containsKey(id)) {
            return false;
        }
        workflows.put(id, workflow);
        return true;
    }

    public boolean incrementWorkflow(int workflow, String fileID) {
        if (!workflows.containsKey(workflow)) {
            return false;
        }
        Workflow wf = workflows.get(workflow);
        wf.increment(fileID, fileManager.getFileNameFromId(fileID));
        // register file to workflow
        if (fileID != null) {
            fileManager.registerToWorkflow(workflow, fileID);
        }
        // delete workflow if done
        if (wf.isDone()) {
            deleteWorkflow(workflow);
            fileManager.removeWorkflowFromMap(workflow);
        }
        return true;
    }

    public List<UserWorkflow> getUserWorkflows(String user) {
        List<UserWorkflow> res = new ArrayList<>();
        for (Workflow wf : workflows.values()) {
            if (wf.userInWorkflow(user)) {
                res.add(new UserWorkflow(wf.getId(), wf.getName(), wf.getPending(user), wf.getUserStep(user)));
            }
        }
        return res;
    }

    public void deleteWorkflow(int workflowId) {
        if (workflows.containsKey(workflowId)) {
            workflows.remove(workflowId);
        }
    }
}
