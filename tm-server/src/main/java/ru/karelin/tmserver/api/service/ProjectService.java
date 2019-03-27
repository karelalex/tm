package ru.karelin.tmserver.api.service;

import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.entity.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService extends SecuredEntityService<Project> {

    void create(String userId, String name, String description, Date startDate, Date finishDate);

    void edit(String userId, String id, String name, String description, Date startDate, Date finishDate, Status status);

    List<Project> getSortedList(String userId, String sortField, boolean isStraight);

    List<Project> getListByKeyword(String userId, String keyword);


}
