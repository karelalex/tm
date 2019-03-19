package ru.karelin.tm.repository;

import ru.karelin.tm.entity.Project;

import java.util.List;

public interface ProjectRepository extends Repository<Project> {
    List<Project> findAllByUserId(String userId);

    Project findOneByIdAndUserId(String id, String userId);
}
