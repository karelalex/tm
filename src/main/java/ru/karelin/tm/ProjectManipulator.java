package ru.karelin.tm;

import java.util.List;


public class ProjectManipulator {
    private static List<Project> projects = Statics.projects;

    public int createProject(String description) {

        Project project = new Project();
        project.setDescription(description);
        projects.add(project);
        return projects.size() - 1;
    }

    public void showProjectsList() {
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("Project №" + i);
            System.out.println(projects.get(i).getDescription());
            System.out.println();
        }
    }

    public void editProjectDescription(int projectNumber, String newDescription){
        Project project = projects.get(projectNumber);
        project.setDescription(newDescription);
    }

    public void removeProject(int projectNumber){
        projects.remove(projectNumber);
    }

    public  void addNewTask(int projectNumber, String taskDescription) {
        Project project = projects.get(projectNumber);
        Task task = new Task();
        task.setDescription(taskDescription);
        project.getTaskList().add(task);
    }
    public  boolean checkProjectListBounds(int indexToCheck) {
        return (indexToCheck >= 0 && indexToCheck < projects.size());
    }
    public  boolean checkTaskListBounds(int projectNumber, int indexToCheck) {
        Project project = projects.get(projectNumber);
        return (indexToCheck >= 0 && indexToCheck < project.getTaskList().size());
    }
    public  void showTaskList(int projectNumber){
        Project project = projects.get(projectNumber);
        List<Task> taskList = project.getTaskList();
        System.out.println("Tasklist for project# "+ projectNumber);
        for (int i=0; i<taskList.size(); i++){
            System.out.println("Task # "+ i+": "+taskList.get(i).getDescription());
        }
        System.out.println();
    }
    public  void showTask(int projectNumber, int taskNumber){
        Project project = projects.get(projectNumber);
        Task task = project.getTaskList().get(taskNumber);
        System.out.println("Task# "+taskNumber+" from Project# "+projectNumber);
        System.out.println(task.getDescription());
    }

    public  void editTask(int projectNumber, int taskNumber, String taskDescription){
        Project project = projects.get(projectNumber);
        Task task = project.getTaskList().get(taskNumber);
        task.setDescription(taskDescription);
    }
    public  void removeTask(int projectNumber, int taskNumber){
        Project project = projects.get(projectNumber);
        project.getTaskList().remove(taskNumber);

    }
}
