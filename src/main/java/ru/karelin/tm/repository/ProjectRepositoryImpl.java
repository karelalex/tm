package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.User;

import java.util.*;


public final class ProjectRepositoryImpl extends AbstractRepository<Project> implements ProjectRepository {




    @Override
    public List<Project> findAllByUserId(final String userId) {
        final ArrayList<Project> list = new ArrayList<>();
        for (Project p: items.values()) {
            if (p.getUserId().equals(userId)) list.add(p);
        }
        return list;
    }

    @Override
    public Project findOneByIdAndUserId(final String id, final String userId) {
        final Project project = findOne(id);
        if (project!=null && project.getUserId().equals(userId)) return project;
        return null;
    }









}
