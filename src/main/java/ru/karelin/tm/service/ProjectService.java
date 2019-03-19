package ru.karelin.tm.service;


import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.repository.ProjectRepository;
import ru.karelin.tm.repository.TaskRepository;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void createProject(User currentUser, String name, String description, Date startDate, Date finishDate) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setUserId(currentUser.getId());
        projectRepository.persist(project);
    }

    public void editProject(User currentUser, String id, String name, String description, Date startDate, Date finishDate) {
        Project project = projectRepository.findOne(id);
        checkSecurity(currentUser, project);
        if(!name.isEmpty()) project.setName(name);
        if(!description.isEmpty()) project.setDescription(description);
        if(startDate!=null) project.setStartDate(startDate);
        if(finishDate!=null) project.setFinishDate(finishDate);
        projectRepository.merge(project);
    }

    public List<Project> getProjectsList(User currentUser) {
        return filterList(currentUser, projectRepository.findAll());
    }



    public void removeProject(User currentUser, String projectID){
        Project project = projectRepository.findOne(projectID);
        checkSecurity(currentUser, project);
        List<Task> taskList = taskRepository.findAllByProjectId(projectID);
        taskRepository.removeAll(taskList);
        projectRepository.remove(project);
    }


    public boolean checkID(User currentUser, String projectId) {
       Project project = projectRepository.findOne(projectId);
       checkSecurity(currentUser, project);
        return project!=null;
    }

    public Project getProject(User currentUser, String projectId) {
        Project project = projectRepository.findOne(projectId);
        checkSecurity(currentUser, project);
        return project;
    }

    private void checkSecurity(User user, Project project){
        if (project.getUserId().equals(user.getId())) return;
        else throw new SecurityException("Current user: " + user.getLogin() + " can not manipulate with project: " + project.getId());
    }
    private List<Project> filterList (User user, List<Project> list){
        Iterator<Project> iter = list.iterator();
        while (iter.hasNext()){
            Project p = iter.next();
            if (!p.getUserId().equals(user.getId())) iter.remove();
        }
        return list;
    }
}
