package ru.karelin.tmserver.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.Status;

import java.util.Date;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String CREATION_DATE_SORT_STRING = "cre";
    private static final String FINISH_DATE_SORT_STRING = "fin";
    private static final String START_DATE_SORT_STRING = "start";
    private static final String STATUS_SORT_STRING = "stat";


    @Override
    public List<Project> getList(String userId) {
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public boolean checkId(String userId, String id) {
        return getOne(userId, id) != null;
    }

    @Nullable
    @Override
    public Project getOne(String userId, String id) {
        return projectRepository.findOneByIdAndUserId(id, userId);
    }

    @Override
    public void create(@NotNull final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        User user = userRepository.findOne(userId);
        if (user == null) {
            return;
        }
        project.setUser(user);
        project.setStatus(Status.PLANNED);
        projectRepository.save(project);
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, Status status) {
        @Nullable final Project project = projectRepository.findOneByIdAndUserId(id, userId);
        if (project != null) {
            if (!name.isEmpty()) project.setName(name);
            if (!description.isEmpty()) project.setDescription(description);
            if (startDate != null) project.setStartDate(startDate);
            if (finishDate != null) project.setFinishDate(finishDate);
            if (status != null) project.setStatus(status);
            projectRepository.save(project);
        }
    }

    @Override
    public List<Project> getSortedList(String userId, @NotNull String sortField, boolean isStraight) {
        switch (sortField) {
            case START_DATE_SORT_STRING:
                if (isStraight) {
                    return projectRepository.findAllByUserIdOrderByStartDate(userId);
                } else {
                    return projectRepository.findAllByUserIdOrderByStartDateDesc(userId);
                }
            case FINISH_DATE_SORT_STRING:
                if (isStraight) {
                    return projectRepository.findAllByUserIdOrderByFinishDate(userId);
                } else {
                    return projectRepository.findAllByUserIdOrderByFinishDateDesc(userId);
                }
            case CREATION_DATE_SORT_STRING:
                if (isStraight) {
                    return projectRepository.findAllByUserIdOrderByCreationDate(userId);
                } else {
                    return projectRepository.findAllByUserIdOrderByCreationDateDesc(userId);
                }
            case STATUS_SORT_STRING:
                if (isStraight) {
                    return projectRepository.findAllByUserIdOrderByStatus(userId);
                } else {
                    return projectRepository.findAllByUserIdOrderByStatusDesc(userId);
                }
            default:
                return getList(userId);
        }
    }

    @Override
    public void remove(final String userId, final String id) {
        Project p = projectRepository.findOneByIdAndUserId(id, userId);
        if (p != null)
            projectRepository.delete(p);
    }

    @Override
    public List<Project> getListByKeyword(String userId, String keyword) {
        return projectRepository.findAllByUserIdAndKeyword(userId, '%'+keyword+'%');
    }
}