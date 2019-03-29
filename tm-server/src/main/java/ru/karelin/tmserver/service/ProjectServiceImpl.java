package ru.karelin.tmserver.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import java.util.Date;
import java.util.List;


public final class ProjectServiceImpl extends AbstractSecuredEntityService<Project> implements ProjectService {

    final private TaskRepository taskRepository;

    public ProjectServiceImpl(@NotNull final ProjectRepository projectRepository, @NotNull final TaskRepository taskRepository) {
        super(projectRepository);
        this.taskRepository = taskRepository;

    }

    @Override
    public void create(@NotNull final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setUserId(userId);
        project.setStatus(Status.PLANNED);
        entityRepository.persist(project);
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, Status status) {
        @Nullable final Project project = entityRepository.findOneByIdAndUserId(id, userId);
        if (project != null) {
            if (!name.isEmpty()) project.setName(name);
            if (!description.isEmpty()) project.setDescription(description);
            if (startDate != null) project.setStartDate(startDate);
            if (finishDate != null) project.setFinishDate(finishDate);
            if (status!=null) project.setStatus(status);
            entityRepository.merge(project);
        }
    }

    @Override
    public List<Project> getSortedList(String userId, String sortField, boolean isStraight) {
        return entityRepository.findAllByUserId(userId, sortField, isStraight);
    }

    @Override
    public void remove(final String userId, final String id) {
        super.remove(userId, id);
        final List<Task> taskList = taskRepository.findAllByProjectId(id);
        taskRepository.removeAll(taskList);
    }

    @Override
    public List<Project> getListByKeyword(String userId, String keyword) {
        return ((ProjectRepository)entityRepository).findAllByUserIdAndKeyword(userId, keyword);
    }

}
