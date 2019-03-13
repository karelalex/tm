package ru.karelin.tm;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String description;
    private List<Task> taskList = new ArrayList<Task>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
