package ru.karelin.tm.service;


import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;

import java.util.Date;
import java.util.List;



public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void createProject(String userId, String name, String description, Date startDate, Date finishDate) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setUserId(userId);
        projectRepository.persist(project);
    }

    @Override
    public void editProject(String userId, String id, String name, String description, Date startDate, Date finishDate) {
        Project project = projectRepository.findOneByIdAndUserId(id, userId);
        if(!name.isEmpty()) project.setName(name);
        if(!description.isEmpty()) project.setDescription(description);
        if(startDate!=null) project.setStartDate(startDate);
        if(finishDate!=null) project.setFinishDate(finishDate);
        projectRepository.merge(project);
    }

    @Override
    public List<Project> getProjectsList(String userId) {
        return projectRepository.findAllByUserId(userId);
    }



    @Override
    public void removeProject(String userId, String projectID){
        Project project = projectRepository.findOneByIdAndUserId(projectID, userId);
        List<Task> taskList = taskRepository.findAllByProjectId(projectID);
        taskRepository.removeAll(taskList);
        projectRepository.remove(project);
    }


    @Override
    public boolean checkID(String userId, String projectId) {
       Project project = projectRepository.findOneByIdAndUserId(projectId, userId);
       return project!=null;
    }

    @Override
    public Project getProject(String userId, String projectId) {
        return projectRepository.findOneByIdAndUserId(projectId, userId);
    }


}
