package ru.karelin.tmserver.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public final class TaskServiceImpl extends AbstractSecuredEntityService<Task> implements TaskService {

    private static final String CREATION_DATE_SORT_STRING = "cre";
    private static final String FINISH_DATE_SORT_STRING = "fin";
    private static final String START_DATE_SORT_STRING = "start";
    private static final String STATUS_SORT_STRING = "stat";

    public TaskServiceImpl(final TaskRepository taskRepository) {
        super(taskRepository);

    }


    @Override
    public void create(final String userId, final String name, final String description, final Date startDate, final Date finishDate, final String projectId) {
        @NotNull final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStartDate(startDate);
        task.setFinishDate(finishDate);
        task.setUserId(userId);
        task.setStatus(Status.PLANNED);
        if (!projectId.isEmpty()) task.setProjectID(projectId);
        entityRepository.persist(task);
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, final String projectId, final Status status) {
        @Nullable final Task task = entityRepository.findOneByIdAndUserId(id, userId);
        if (task != null) {
            if (!name.isEmpty()) task.setName(name);
            if (!description.isEmpty()) task.setDescription(description);
            if (startDate != null) task.setStartDate(startDate);
            if (finishDate != null) task.setFinishDate(finishDate);
            if (!projectId.isEmpty()) task.setProjectID(projectId);
            if(status!=null) task.setStatus(status);
            entityRepository.merge(task);
        }
    }


    @Override
    public List<Task> getListByProjectId(final String userId, final String projectId) {
        return ((TaskRepository)entityRepository).findAllByProjectIdAndUserId(projectId, userId);
    }


    @Override
    public void remove(final String userId, final String id) {
       @Nullable final Task task = (entityRepository).findOneByIdAndUserId(id, userId);
        if (task != null)
            (entityRepository).remove(task);
    }

    @Override
    public List<Task> getSortedListByProjectId(String userId, String projectId, String sortField, boolean isStraight) {
        switch (sortField){
            case START_DATE_SORT_STRING:
                if(isStraight) {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByStartDate(userId, projectId);
                }
                else {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByStartDateDesc(userId, projectId);
                }
            case FINISH_DATE_SORT_STRING:
                if (isStraight){
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByFinishDate(userId, projectId);
                }
                else {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByFinishDateDesc(userId,projectId);
                }
            case CREATION_DATE_SORT_STRING:
                if (isStraight) {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByCreationDate(userId,projectId);
                }
                else {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByCreationDateDesc(userId,projectId);
                }
            case STATUS_SORT_STRING:
                if(isStraight) {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByStatus(userId, projectId);
                }
                else {
                    return ((TaskRepository)entityRepository).findAllByUserIdAndProjectIdOrderByStatusDesc(userId, projectId);
                }
            default:
                return getList(userId);
        }
    }

    @Override
    public List<Task> getSortedList(String userId, String sortField, boolean isStraight) {
        switch (sortField){
            case START_DATE_SORT_STRING:
                if(isStraight) {
                    return entityRepository.findAllByUserIdOrderByStartDate(userId);
                }
                else {
                    return entityRepository.findAllByUserIdOrderByStartDateDesc(userId);
                }
            case FINISH_DATE_SORT_STRING:
                if (isStraight){
                    return entityRepository.findAllByUserIdOrderByFinishDate(userId);
                }
                else {
                    return entityRepository.findAllByUserIdOrderByFinishDateDesc(userId);
                }
            case CREATION_DATE_SORT_STRING:
                if (isStraight) {
                    return entityRepository.findAllByUserIdOrderByCreationDate(userId);
                }
                else {
                    return entityRepository.findAllByUserIdOrderByCreationDateDesc(userId);
                }
            case STATUS_SORT_STRING:
                if(isStraight) {
                    return entityRepository.findAllByUserIdOrderByStatus(userId);
                }
                else {
                    return entityRepository.findAllByUserIdOrderByStatusDesc(userId);
                }
            default:
                return getList(userId);
        }
    }

    @Override
    public List<Task> getListByKeyword(String userId, String keyword) {
        return ((TaskRepository)entityRepository).findAllByUserIdAndKeyword(userId, keyword);
    }

}
