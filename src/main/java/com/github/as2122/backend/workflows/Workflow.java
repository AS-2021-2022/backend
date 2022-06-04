package com.github.as2122.backend.workflows;

public class Workflow {
    private final String name;
    private final WorkflowStep[] steps;
    private int step = 0;

    public Workflow(String name, WorkflowStep[] steps) {
        this.name = name;
        this.steps = steps;
    }

    public Workflow(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public WorkflowStep[] getSteps() {
        return steps;
    }

    public int getStep() {
        return step;
    }

    public boolean userInWorkflow(int id) {
        for (WorkflowStep step: steps) {
            if (step.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean incrementWorkflow(String fileName) {
        if (step >= steps.length) {
            return false;
        }
        steps[step].setFileName(fileName);
        step++;
        return true;
    }

    public boolean getPending(int user) {
        for (int i = step; i < steps.length; i++) {
            if (steps[i].getId() == user) {
                return true;
            }
        }
        return false;
    }
}
