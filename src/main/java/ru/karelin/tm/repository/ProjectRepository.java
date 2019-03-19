package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.User;

import java.util.*;


public class ProjectRepository implements Repository<Project> {
    private static Map<String, Project> projects = new LinkedHashMap<>();

    @Override
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public Project findOne(String id) {
        return projects.get(id);
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
