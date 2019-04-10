package ru.karelin.tmserver.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.dto.TaskDto;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebService
@NoArgsConstructor
public class TaskEndpoint {
    @NotNull private TaskService taskService;
    @NotNull private SessionService sessionServiceImpl;

    public TaskEndpoint(@NotNull TaskService taskService, @NotNull SessionService sessionServiceImpl) {
        this.taskService = taskService;
        this.sessionServiceImpl = sessionServiceImpl;
    }

    @WebMethod
    public void createTask(@WebParam(name = "session") final SessionDto session,
                           @WebParam(name = "taskName") final String name,
                           @WebParam(name = "taskDescription") final String description,
                           @WebParam(name = "taskStartDate") final Date startDate,
                           @WebParam(name = "taskFinishDate") final Date finishDate,
                           @WebParam(name = "taskProjectId") String projectId) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) {
            taskService.create(session.getUserId(), name, description, startDate, finishDate, projectId);
        }
    }

    @WebMethod
    public void editTask(@WebParam(name = "session") final SessionDto session,
                         @WebParam(name = "taskId") final String id,
                         @WebParam(name = "taskName") final String name,
                         @WebParam(name = "taskDescription") final String description,
                         @WebParam(name = "taskStartDate") final Date startDate,
                         @WebParam(name = "taskFinishDate") final Date finishDate,
                         @WebParam(name = "taskProjectId") String projectId,
                         @WebParam(name = "taskStatus") Status status) throws WrongSessionException {
        if (sessionServiceImpl.isSessionExists(session)) taskService.edit(session.getUserId(), id, name, description, startDate, finishDate, projectId, status);
    }

    @WebMethod
    public List<TaskDto> getTaskListByProjectId(@WebParam(name = "session") final SessionDto session,
                                                @WebParam(name = "projectId") final String projectId) throws WrongSessionException {
        if (sessionServiceImpl.isSessionExists(session)) return convert(taskService.getListByProjectId(session.getUserId(), projectId));
        return Collections.emptyList();
    }

    @WebMethod
    public List<TaskDto> getSortedTaskListByProjectId(@WebParam(name = "session") final SessionDto session,
                                                   @WebParam(name = "tasksProjectId") final String projectId,
                                                   @WebParam(name = "sortField") String sortField,
                                                   @WebParam(name = "isOrderStraight") boolean isStraight) throws WrongSessionException {
        if (sessionServiceImpl.isSessionExists(session)) return convert(taskService.getSortedListByProjectId(session.getUserId(), projectId, sortField, isStraight));
        return Collections.emptyList();
    }

    @WebMethod
    public List<TaskDto> getSortedTaskList(@WebParam(name = "session") final SessionDto session,
                                        @WebParam(name = "sortField") final String sortField,
                                        @WebParam(name = "isOrderStraight") boolean isStraight) throws WrongSessionException {
       if(sessionServiceImpl.isSessionExists(session)) return convert(taskService.getSortedList(session.getUserId(), sortField, isStraight));
       return Collections.emptyList();
    }

    @WebMethod
    public List<TaskDto> getTaskListByKeyword(@WebParam(name = "session") final SessionDto session,
                                           @WebParam(name = "searchString") String keyword) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) return convert(taskService.getListByKeyword(session.getUserId(), keyword));
        return Collections.emptyList();
    }

    @WebMethod
    public List<TaskDto> getTaskList(@WebParam(name = "session") final SessionDto session) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) return convert(taskService.getList(session.getUserId()));
        return Collections.emptyList();
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final SessionDto session,
                           @WebParam(name = "taskId") final String id) throws WrongSessionException {
       if(sessionServiceImpl.isSessionExists(session)) taskService.remove(session.getUserId(), id);
    }

    @WebMethod
    public boolean checkTaskId(@WebParam(name = "session") final SessionDto session,
                               @WebParam(name = "taskId") final String id) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) return taskService.checkId(session.getUserId(), id);
        return false;
    }

    @WebMethod
    public TaskDto getTask(@WebParam(name = "session") final SessionDto session,
                        @WebParam(name = "taskId") String id) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) return convert(taskService.getOne(session.getUserId(), id));
        return null;
    }

    @Contract(value = "null -> null")
    private TaskDto convert(@Nullable final Task task){
        if (task==null) return null;
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setProjectId(task.getProject().getId());
        taskDto.setCreationDate(task.getCreationDate());
        taskDto.setStartDate(task.getStartDate());
        taskDto.setFinishDate(task.getFinishDate());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    private List<TaskDto> convert (List<Task> tasks){
        List<TaskDto> taskDtoList = new ArrayList<>();
        for(Task t: tasks){
            taskDtoList.add(convert(t));
        }
        return taskDtoList;
    }
}
