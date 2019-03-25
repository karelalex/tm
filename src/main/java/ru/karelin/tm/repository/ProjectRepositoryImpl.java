package ru.karelin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.entity.Project;

import java.util.*;


public final class ProjectRepositoryImpl extends AbstractRepository<Project> implements ProjectRepository {




    @Override
    public List<Project> findAllByUserId(final String userId) {
        @NotNull final ArrayList<Project> list = new ArrayList<>();
        for (Project p: items.values()) {
            if (p.getUserId().equals(userId)) list.add(p);
        }
        return list;
    }

    @Override
    public Project findOneByIdAndUserId(final String id, final String userId) {
        @Nullable final Project project = findOne(id);
        if (project!=null && project.getUserId().equals(userId)) return project;
        return null;
    }









}
