package ru.karelin.tm.service;

import ru.karelin.tm.entity.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    void createProject(String userId, String name, String description, Date startDate, Date finishDate);

    void editProject(String userId, String id, String name, String description, Date startDate, Date finishDate);

    List<Project> getProjectsList(String userId);

    void removeProject(String userId, String projectID);

    boolean checkID(String userId, String projectId);

    Project getProject(String userId, String projectId);
}
