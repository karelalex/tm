package ru.karelin.tmserver.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public final class ProjectServiceImpl extends AbstractSecuredEntityService<Project> implements ProjectService {

    final private TaskRepository taskRepository;

    private static final String CREATION_DATE_SORT_STRING = "cre";
    private static final String FINISH_DATE_SORT_STRING = "fin";
    private static final String START_DATE_SORT_STRING = "start";
    private static final String STATUS_SORT_STRING = "stat";

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
    public void remove(final String userId, final String id) {
        final List<Task> taskList = taskRepository.findAllByProjectId(id);
        taskRepository.removeAll(taskList);
        super.remove(userId, id);
    }

    @Override
    public List<Project> getListByKeyword(String userId, String keyword) {
        return ((ProjectRepository)entityRepository).findAllByUserIdAndKeyword(userId, keyword);
    }

}
