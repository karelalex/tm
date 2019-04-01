package ru.karelin.tmserver.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebService
public class TaskEndpoint {
    @NotNull private TaskService taskService;
    @NotNull private SessionService sessionService;

    public TaskEndpoint(@NotNull TaskService taskService, @NotNull SessionService sessionService) {
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @WebMethod
    public void createTask(@WebParam(name = "session") final Session session,
                           @WebParam(name = "taskName") final String name,
                           @WebParam(name = "taskDescription") final String description,
                           @WebParam(name = "taskStartDate") final Date startDate,
                           @WebParam(name = "taskFinishDate") final Date finishDate,
                           @WebParam(name = "taskProjectId") String projectId) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) {
            taskService.create(session.getUserId(), name, description, startDate, finishDate, projectId);
        }
    }

    @WebMethod
    public void editTask(@WebParam(name = "session") final Session session,
                         @WebParam(name = "taskId") final String id,
                         @WebParam(name = "taskName") final String name,
                         @WebParam(name = "taskDescription") final String description,
                         @WebParam(name = "taskStartDate") final Date startDate,
                         @WebParam(name = "taskFinishDate") final Date finishDate,
                         @WebParam(name = "taskProjectId") String projectId,
                         @WebParam(name = "taskStatus") Status status) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) taskService.edit(session.getUserId(), id, name, description, startDate, finishDate, projectId, status);
    }

    @WebMethod
    public List<Task> getTaskListByProjectId(@WebParam(name = "session") final Session session,
                                             @WebParam(name = "projectId") final String projectId) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return taskService.getListByProjectId(session.getUserId(), projectId);
        return Collections.emptyList();
    }

    @WebMethod
    public List<Task> getSortedTaskListByProjectId(@WebParam(name = "session") final Session session,
                                                   @WebParam(name = "tasksProjectId") final String projectId,
                                                   @WebParam(name = "sortField") String sortField,
                                                   @WebParam(name = "isOrderStraight") boolean isStraight) throws WrongSessionException {
        if (sessionService.isSessionExists(session)) return taskService.getSortedListByProjectId(session.getUserId(), projectId, sortField, isStraight);
        return Collections.emptyList();
    }

    @WebMethod
    public List<Task> getSortedTaskList(@WebParam(name = "session") final Session session,
                                        @WebParam(name = "sortField") final String sortField,
                                        @WebParam(name = "isOrderStraight") boolean isStraight) throws WrongSessionException {
       if(sessionService.isSessionExists(session)) return taskService.getSortedList(session.getUserId(), sortField, isStraight);
       return Collections.emptyList();
    }

    @WebMethod
    public List<Task> getTaskListByKeyword(@WebParam(name = "session") final Session session,
                                           @WebParam(name = "searchString") String keyword) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) return taskService.getListByKeyword(session.getUserId(), keyword);
        return Collections.emptyList();
    }

    @WebMethod
    public List<Task> getTaskList(@WebParam(name = "session") final Session session) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) return taskService.getList(session.getUserId());
        return Collections.emptyList();
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final Session session,
                           @WebParam(name = "taskId") final String id) throws WrongSessionException {
       if(sessionService.isSessionExists(session)) taskService.remove(session.getUserId(), id);
    }

    @WebMethod
    public boolean checkTaskId(@WebParam(name = "session") final Session session,
                               @WebParam(name = "taskId") final String id) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) return taskService.checkId(session.getUserId(), id);
        return false;
    }

    @WebMethod
    public Task getTask(@WebParam(name = "session") final Session session,
                        @WebParam(name = "taskId") String id) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) return taskService.getOne(session.getUserId(), id);
        return null;
    }
}
