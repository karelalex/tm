package ru.karelin.tm.service;

import ru.karelin.tm.entity.Project;

import java.util.Date;

public interface ProjectService extends SecuredEntitiesService<Project> {

    void create(String userId, String name, String description, Date startDate, Date finishDate);

    void edit(String userId, String id, String name, String description, Date startDate, Date finishDate);


}
