package ru.karelin.tm.service;

import ru.karelin.tm.Statics;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ProjectManipulator {
    private static Map<String, Project> projects = Statics.projects;

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String createProject(String name, String description, Date startDate, Date finishDate) {

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        projects.put(project.getId(), project);
        return project.getId();
    }

    public void editProject(String id, String name, String description, Date startDate, Date finishDate) {

        Project project = projects.get(id);
        if(!name.isEmpty()) project.setName(name);
        if(!description.isEmpty()) project.setDescription(description);
        if(startDate!=null) project.setStartDate(startDate);
        if(finishDate!=null) project.setFinishDate(finishDate);
        projects.put(project.getId(), project);
    }

    public void showProjectsList() {
        for (Map.Entry<String, Project> entry:projects.entrySet()) {
            System.out.println("Projectid: " + entry.getKey());
            System.out.println("Project name: " +entry.getValue().getName() );
            System.out.println("Project name: " + entry.getValue().getDescription());
            System.out.println();
        }
    }



    public void removeProject(String projectID){
        projects.remove(projectID);
    }


    public boolean checkID(String projectId) {
       return projects.containsKey(projectId);
    }

    public void showProject(String projectId) {
        Project project = projects.get(projectId);
        System.out.println("Project name: " + project.getName() );
        System.out.println("Project name: " + project.getDescription());
        System.out.println("Start Date: " + dateFormat.format(project.getStartDate()));
        System.out.println("End Date: " + dateFormat.format(project.getFinishDate()));
        System.out.println();
    }
}
