package com.example.goalkeeper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal {
    private String name;
    private Date deadline;
    private boolean isCompleted;

    public Goal(String name, Date deadline, boolean isCompleted) {
        this.name = name;
        this.deadline = deadline;
        this.isCompleted = isCompleted;

    }

    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getDeadlineString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(deadline);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return name + " - " + deadline.toString();
    }
}
