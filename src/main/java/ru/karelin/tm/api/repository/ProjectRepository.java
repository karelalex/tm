package ru.karelin.tm.api.repository;

import ru.karelin.tm.entity.Project;

import java.util.List;

public interface ProjectRepository extends SortableEntityRepository<Project> {
    List<Project> findAllByUserIdAndKeyword(String userId, String key);
}
