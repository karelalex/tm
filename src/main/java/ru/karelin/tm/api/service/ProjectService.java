package ru.karelin.tm.api.service;

import ru.karelin.tm.entity.Project;

import java.util.Date;

public interface ProjectService extends SecuredEntityService<Project> {

    void create(String userId, String name, String description, Date startDate, Date finishDate);

    void edit(String userId, String id, String name, String description, Date startDate, Date finishDate);


}
