package ru.karelin.tm.service;


import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;

import java.util.Date;
import java.util.List;



public final class ProjectServiceImpl implements ProjectService {

    final private ProjectRepository projectRepository;
    final private TaskRepository taskRepository;

    public ProjectServiceImpl(final ProjectRepository projectRepository, final TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void createProject(final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setUserId(userId);
        projectRepository.persist(project);
    }

    @Override
    public void editProject(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate) {
        final Project project = projectRepository.findOneByIdAndUserId(id, userId);
        if(!name.isEmpty()) project.setName(name);
        if(!description.isEmpty()) project.setDescription(description);
        if(startDate!=null) project.setStartDate(startDate);
        if(finishDate!=null) project.setFinishDate(finishDate);
        projectRepository.merge(project);
    }

    @Override
    public List<Project> getProjectsList(final String userId) {
        return projectRepository.findAllByUserId(userId);
    }



    @Override
    public void removeProject(final String userId, final String projectID){
        final Project project = projectRepository.findOneByIdAndUserId(projectID, userId);
        final List<Task> taskList = taskRepository.findAllByProjectId(projectID);
        taskRepository.removeAll(taskList);
        projectRepository.remove(project);
    }


    @Override
    public boolean checkID(final String userId, final String projectId) {
       final Project project = projectRepository.findOneByIdAndUserId(projectId, userId);
       return project!=null;
    }

    @Override
    public Project getProject(final String userId, final String projectId) {
        return projectRepository.findOneByIdAndUserId(projectId, userId);
    }


}
