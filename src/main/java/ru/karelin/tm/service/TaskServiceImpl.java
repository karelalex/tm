package ru.karelin.tm.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.service.TaskService;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.enumeration.Status;

import java.util.Date;
import java.util.List;


public final class TaskServiceImpl extends AbstractSecuredEntityService<Task> implements TaskService {



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
    public void remove(final String userId, final String taskId) {
       @Nullable final Task task = ((TaskRepository)entityRepository).findOneByIdAndUserId(taskId, userId);
        if (task != null)
            ((TaskRepository)entityRepository).remove(task);
    }

    @Override
    public List<Task> getSortedListByProjectId(String userId, String projectId, String sortField, boolean isStraight) {
        return ((TaskRepository)entityRepository).findAllByProjectIdAndUserId(projectId, userId, sortField, isStraight);
    }

    @Override
    public List<Task> getSortedList(String userId, String sortField, boolean isStraight) {
        return ((TaskRepository)entityRepository).findAllByUserId(userId, sortField, isStraight);
    }

    @Override
    public List<Task> getListByKeyword(String userId, String keyword) {
        return ((TaskRepository)entityRepository).findAllByUserIdAndKeyword(userId, keyword);
    }
}
