package com.github.as2122.backend.workflows;

public class Workflow {
    private static int STATIC_ID = 0;
    private final int id;
    private final String name;
    private final WorkflowStep[] steps;
    private final int[] files;
    private int step = 0;

    public Workflow(String name, WorkflowStep[] steps, int[] files) {
        this.id = STATIC_ID++;
        this.name = name;
        this.steps = steps;
        this.files = files;
    }

    public Workflow(String name, WorkflowStep[] steps) {
        this(name, steps, new int[0]);
    }

    public Workflow(String name) {
        this(name, new WorkflowStep[0]);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WorkflowStep[] getSteps() {
        return steps;
    }

    public int[] getFiles() {
        return files;
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

    public boolean increment(String fileName) {
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