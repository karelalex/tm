package ru.karelin.tm.service;

import ru.karelin.tm.Statics;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.ProjectRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String createProject(String name, String description, Date startDate, Date finishDate) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        projectRepository.persist(project);
        return project.getId();
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate) {
        Project project = projectRepository.findOne(id);
        if(!name.isEmpty()) project.setName(name);
        if(!description.isEmpty()) project.setDescription(description);
        if(startDate!=null) project.setStartDate(startDate);
        if(finishDate!=null) project.setFinishDate(finishDate);
        projectRepository.merge(project);
    }

    public List<Project> getProjectsList() {
        return projectRepository.findAll();
    }



    public void removeProject(String projectID){
        Project project = projectRepository.findOne(projectID);
        projectRepository.remove(project);

    }


    public boolean checkID(String projectId) {
       Project project = projectRepository.findOne(projectId);
        return project!=null;
    }

    public Project getProject(String projectId) {
        return projectRepository.findOne(projectId);
    }
}
