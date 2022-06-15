package com.github.as2122.backend.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task {
    // Date format is DD/MM/YYYY
    private String name;
    private String startDate;
    private String endDate;
    private String priority;
    private String description;
    private String assignee;

    public Task(String name, String startDate, String endDate, String priority, String description, String assignee) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.description = description;
        this.assignee = assignee;
    }

    public Task(String name, String startDate, String endDate, String priority, String assignee) {
        this(name, startDate, endDate, priority, null, assignee);
    }

    public long getTimeLeft() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date additive = sdf.parse(endDate);
            Calendar today = Calendar.getInstance();
            Date subtractive = today.getTime();
            // System.out.println("additive " + additive);
            // System.out.println("subtractive " + subtractive);
            return TimeUnit.MINUTES.convert(additive.getTime() - subtractive.getTime(), TimeUnit.MILLISECONDS);
        } catch (ParseException p) {
            return 0L;
        } 
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignee() {
        return assignee;
    }
}
