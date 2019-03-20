package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.User;

import java.util.*;


public final class ProjectRepositoryImpl implements ProjectRepository {
    private static final Map<String, Project> projects = new LinkedHashMap<>();

    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public List<Project> findAllByUserId(final String userId) {
        final ArrayList<Project> list = new ArrayList<>();
        for (Project p: projects.values()) {
            if (p.getUserId().equals(userId)) list.add(p);
        }
        return list;
    }


    @Override
    public Project findOne(final String id) {
        return projects.get(id);
    }

    @Override
    public Project findOneByIdAndUserId(final String id, final String userId) {
        final Project project = findOne(id);
        if (project.getUserId().equals(userId)) return project;
        return null;
    }

    @Override
    public void persist(final Project project) {
        if (projects.containsKey(project.getId())) throw new ObjectAlreadyExistsException("Project with id="+project.getId()+" is already save in db");
        projects.put(project.getId(), project);
    }

    @Override
    public Project merge(final Project project) {
        projects.put(project.getId(), project);
        return projects.get(project.getId());

    }

    @Override
    public boolean remove(final Project project) {
        Project removedProject = projects.remove(project.getId());
        return removedProject != null;
    }

    @Override
    public void removeAll(final Collection<Project> projectCollection) {
        for (Project p : projectCollection) {
            projects.remove(p.getId());
        }
    }

}
