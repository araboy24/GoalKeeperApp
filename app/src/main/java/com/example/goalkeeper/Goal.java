package com.example.goalkeeper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal {
    private String name;
    private String deadline, id;
    private boolean isCompleted;

    public Goal(String name, String deadline, boolean isCompleted, String id) {
        this.name = name;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }


    public String getDeadline() {
        return deadline;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(String deadline) {
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
