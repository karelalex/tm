package ru.karelin.tm.commands;

import ru.karelin.tm.Bootstrap;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.service.TaskService;

import java.text.DateFormat;

public class TaskShowCommand extends AbstractCommand {
    public TaskShowCommand(Bootstrap bootstrap) {
        super("st", "shows task with specified ID", bootstrap);
    }

    @Override
    public void execute(String... params) {
        TaskService taskService = bootstrap.getTaskService();
        String taskId = params[0];
        DateFormat dateFormat = bootstrap.getDateFormat();
        if(!taskService.checkID(taskId)){
            System.out.println("Wrong ID");
            return;
        }
        Task task = taskService.getTask(taskId);
        System.out.println("Task name: " + task.getName());
        System.out.println("Task description: " + task.getDescription());
        System.out.println("Task start date: " + dateFormat.format(task.getStartDate()));
        System.out.println("Task finish date " + dateFormat.format(task.getFinishDate()));
        System.out.println("Project ID: " + task.getProjectID());
        System.out.println();
    }
}
