package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public interface ProjectRepository extends SortableEntityRepository<Project> {
    List<Project> findAllByUserIdAndKeyword(String userId, String key, EntityManager em);
}
