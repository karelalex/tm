package ru.karelin.tm.service;


import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.api.repository.TaskRepository;

import java.util.Date;
import java.util.List;



public final class ProjectServiceImpl extends AbstractSecuredEntityService<Project> implements ProjectService {


    final private TaskRepository taskRepository;

    public ProjectServiceImpl(final ProjectRepository projectRepository, final TaskRepository taskRepository) {
        super(projectRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public void create(final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setUserId(userId);
        entityRepository.persist(project);
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate) {
        final Project project = entityRepository.findOneByIdAndUserId(id, userId);
        if(!name.isEmpty()) project.setName(name);
        if(!description.isEmpty()) project.setDescription(description);
        if(startDate!=null) project.setStartDate(startDate);
        if(finishDate!=null) project.setFinishDate(finishDate);
        entityRepository.merge(project);
    }




    @Override
    public void remove(final String userId, final String projectID){
        super.remove(userId, projectID);
        final List<Task> taskList = taskRepository.findAllByProjectId(projectID);
        taskRepository.removeAll(taskList);
    }






}
