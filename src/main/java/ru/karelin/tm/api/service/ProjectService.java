package ru.karelin.tm.api.service;

import ru.karelin.tm.entity.Project;
import ru.karelin.tm.enumeration.Status;

import java.util.Date;
import java.util.List;

public interface ProjectService extends SecuredEntityService<Project> {

    void create(String userId, String name, String description, Date startDate, Date finishDate);

    void edit(String userId, String id, String name, String description, Date startDate, Date finishDate, Status status);

    List<Project> getSortedList(String userId, String sortField, boolean isStraight);

    List<Project> getListByKeyword(String userId, String keyword);


}
