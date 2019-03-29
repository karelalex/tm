package ru.karelin.tmserver.repository;

import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.entity.Project;

import java.util.ArrayList;
import java.util.List;


public final class ProjectRepositoryImpl extends AbstractSortableEntityRepository<Project> implements ProjectRepository {
    @Override
    public List<Project> findAllByUserIdAndKeyword(String userId, String key) {
        List<Project> out = new ArrayList<>();
        List<Project> in = findAllByUserId(userId);
        for (Project p : in) {
            if (p.getName().contains(key) || p.getDescription().contains(key)) out.add(p);
        }
        return out;
    }
}