package ru.karelin.tm.repository;

import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.entity.Project;

import java.util.Collection;
import java.util.List;

public interface ProjectBatisRepo extends ProjectRepository {
    @Override

    List<Project> findAllByUserId(String userId);

    @Override
    Project findOneByIdAndUserId(String id, String userId);

    @Override
    List<Project> findAll();

    @Override
    Project findOne(String id);

    @Override
    void persist(Project project);

    @Override
    Project merge(Project project);

    @Override
    boolean remove(Project project);

    @Override
    void removeAll(Collection<Project> projects);
}
