package ru.karelin.tm.service;


import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;
import java.util.Date;
import java.util.List;



public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
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
        List<Task> taskList = taskRepository.findAllByProjectId(projectID);
        taskRepository.removeAll(taskList);
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
