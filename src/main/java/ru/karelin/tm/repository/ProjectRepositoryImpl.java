package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.User;

import java.util.*;


public class ProjectRepositoryImpl implements ProjectRepository {
    private static Map<String, Project> projects = new LinkedHashMap<>();

    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public List<Project> findAllByUserId(String userId) {
        ArrayList<Project> list = new ArrayList<>();
        for (Project p: projects.values()) {
            if (p.getUserId().equals(userId)) list.add(p);
        }
        return list;
    }


    @Override
    public Project findOne(String id) {
        return projects.get(id);
    }

    @Override
    public Project findOneByIdAndUserId(String id, String userId) {
        Project project = findOne(id);
        if (project.getUserId().equals(userId)) return project;
        return null;
    }

    @Override
    public void persist(Project project) {
        if (projects.containsKey(project.getId())) throw new ObjectAlreadyExistsException("Project with id="+project.getId()+" is already save in db");

        projects.put(project.getId(), project);
    }

    @Override
    public Project merge(Project project) {
        projects.put(project.getId(), project);
        return projects.get(project.getId());

    }

    @Override
    public boolean remove(Project project) {
        Project removedProject = projects.remove(project.getId());
        return removedProject != null;
    }

    @Override
    public void removeAll(Collection<Project> projectCollection) {
        for (Project p : projectCollection) {
            projects.remove(p.getId());
        }
    }

}
