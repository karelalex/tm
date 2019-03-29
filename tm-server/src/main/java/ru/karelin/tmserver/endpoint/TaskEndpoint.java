package ru.karelin.tmserver.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public class TaskEndpoint {
    @NotNull private TaskService taskService;

    public TaskEndpoint(@NotNull TaskService taskService) {
        this.taskService = taskService;
    }

    @WebMethod
    public void createTask(@WebParam(name = "userId") final String userId,
                           @WebParam(name = "taskName") final String name,
                           @WebParam(name = "taskDescription") final String description,
                           @WebParam(name = "taskStartDate") final Date startDate,
                           @WebParam(name = "taskFinishDate") final Date finishDate,
                           @WebParam(name = "taskProjectId") String projectId) {
        taskService.create(userId, name, description, startDate, finishDate, projectId);
    }

    @WebMethod
    public void editTask(@WebParam(name = "userId") final String userId,
                         @WebParam(name = "taskId") final String id,
                         @WebParam(name = "taskName") final String name,
                         @WebParam(name = "taskDescription") final String description,
                         @WebParam(name = "taskStartDate") final Date startDate,
                         @WebParam(name = "taskFinishDate") final Date finishDate,
                         @WebParam(name = "taskProjectId") String projectId,
                         @WebParam(name = "taskStatus") Status status) {
        taskService.edit(userId, id, name, description, startDate, finishDate, projectId, status);
    }

    @WebMethod
    public List<Task> getTaskListByProjectId(@WebParam(name = "userId") final String userId,
                                             @WebParam(name = "projectId") final String projectId) {
        return taskService.getListByProjectId(userId, projectId);
    }

    @WebMethod
    public List<Task> getSortedTaskListByProjectId(@WebParam(name = "userId") final String userId,
                                                   @WebParam(name = "tasksProjectId") final String projectId,
                                                   @WebParam(name = "sortField") String sortField,
                                                   @WebParam(name = "isOrderStraight") boolean isStraight) {
        return taskService.getSortedListByProjectId(userId, projectId, sortField, isStraight);
    }

    @WebMethod
    public List<Task> getSortedTaskList(@WebParam(name = "userId") final String userId,
                                        @WebParam(name = "sortField") final String sortField,
                                        @WebParam(name = "isOrderStraight") boolean isStraight) {
        return taskService.getSortedList(userId, sortField, isStraight);
    }

    @WebMethod
    public List<Task> getTaskListByKeyword(@WebParam(name = "userId") final String userId,
                                           @WebParam(name = "searchString") String keyword) {
        return taskService.getListByKeyword(userId, keyword);
    }

    @WebMethod
    public List<Task> getTaskList(@WebParam(name = "userId") final String userId) {
        return taskService.getList(userId);
    }

    @WebMethod
    public void removeTask(@WebParam(name = "userId") final String userId,
                           @WebParam(name = "taskId") final String id) {
        taskService.remove(userId, id);
    }

    @WebMethod
    public boolean checkTaskId(@WebParam (name = "userId") final String userId,
                               @WebParam(name = "taskId") final String id) {
        return taskService.checkId(userId, id);
    }

    @WebMethod
    public Task getTask(@WebParam(name = "userId") String userId,
                        @WebParam(name = "taskId") String id) {
        return taskService.getOne(userId, id);
    }
}
