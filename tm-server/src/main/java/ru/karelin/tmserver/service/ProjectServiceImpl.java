package ru.karelin.tmserver.service;


import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public final class ProjectServiceImpl /*extends AbstractSecuredEntityService<Project>*/ implements ProjectService {


    final private EntityManagerFactory factory;

    private static final String CREATION_DATE_SORT_STRING = "cre";
    private static final String FINISH_DATE_SORT_STRING = "fin";
    private static final String START_DATE_SORT_STRING = "start";
    private static final String STATUS_SORT_STRING = "stat";

    public ProjectServiceImpl(@NotNull final EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Project> getList(String userId) {
        SqlSession session = factory.openSession();
        ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
        List<Project> list;
        list = projectRepository.findAllByUserId(userId);
        session.close();
        return list;
    }

    @Override
    public boolean checkId(String userId, String id) {
        return getOne(userId, id) != null;
    }

    @Override
    public Project getOne(String userId, String id) {
        SqlSession session = factory.openSession();
        ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
        Project p = projectRepository.findOneByIdAndUserId(id, userId);
        session.close();
        return p;
    }

    @Override
    public void create(@NotNull final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        SqlSession session = factory.openSession();
        try {
            ProjectRepository entityRepository = session.getMapper(ProjectRepositoryBatis.class);
            @NotNull final Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setStartDate(startDate);
            project.setFinishDate(finishDate);
            project.setUserId(userId);
            project.setStatus(Status.PLANNED);
            entityRepository.persist(project);
            session.commit();
        } catch (PersistenceException e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, Status status) {
        SqlSession session = factory.openSession();
        ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
        try {
            @Nullable final Project project = projectRepository.findOneByIdAndUserId(id, userId);
            if (project != null) {
                if (!name.isEmpty()) project.setName(name);
                if (!description.isEmpty()) project.setDescription(description);
                if (startDate != null) project.setStartDate(startDate);
                if (finishDate != null) project.setFinishDate(finishDate);
                if (status != null) project.setStatus(status);
                projectRepository.merge(project);
                session.commit();
            }
        } catch (PersistenceException e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Project> getSortedList(String userId, String sortField, boolean isStraight) {
        try (SqlSession session = factory.openSession()) {
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
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
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void remove(final String userId, final String id) {
        SqlSession session = factory.openSession();
        ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
        TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
        try {
            final List<Task> taskList = taskRepository.findAllByProjectId(id);
            if (taskList.size() > 0)
                taskRepository.removeAllInList(taskList);
            Project p = projectRepository.findOneByIdAndUserId(id, userId);
            projectRepository.remove(p);
            session.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public List<Project> getListByKeyword(String userId, String keyword) {
        try (SqlSession session = factory.openSession()) {
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            return projectRepository.findAllByUserIdAndKeyword(userId, keyword);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
