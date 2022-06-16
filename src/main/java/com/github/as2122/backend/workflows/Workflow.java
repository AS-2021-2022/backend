package com.github.as2122.backend.workflows;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


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

    public boolean userInWorkflow(String id) {
        for (WorkflowStep step : steps) {
            if (step.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean increment(String fileName) {
        if (step >= steps.length) {
            return false;
        }
        if (fileName != null) {
            steps[step].setFileName(fileName);
        }
        step++;
        return true;
    }

    public boolean getPending(String user) {
        for (int i = step; i < steps.length; i++) {
            if (Objects.equals(steps[i].getId(), user)) {
                return true;
            }
        }
        return false;
    }

    public int getUserStep(String user) {
        for (int i = 0; i < steps.length; i++) {
            if (steps[i].getId().equals(user)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return "Workflow " + id + " {" + name + ", step " + step + ", [" + Arrays.stream(steps).map(WorkflowStep::toString).collect(Collectors.joining(", ")) + "]}";
    }
}
