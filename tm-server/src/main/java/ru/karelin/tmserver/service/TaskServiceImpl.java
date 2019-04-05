package ru.karelin.tmserver.service;


import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.repository.TaskRepositoryBatis;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public final class TaskServiceImpl implements TaskService {

    private static final String CREATION_DATE_SORT_STRING = "cre";
    private static final String FINISH_DATE_SORT_STRING = "fin";
    private static final String START_DATE_SORT_STRING = "start";
    private static final String STATUS_SORT_STRING = "stat";
    final private SqlSessionFactory factory;

    public TaskServiceImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Task> getList(String userId) {
        try (SqlSession session = factory.openSession()) {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            return taskRepository.findAllByUserId(userId);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean checkId(String userId, String id) {
        return getOne(userId, id) != null;
    }

    @Override
    public Task getOne(String userId, String id) {
        try (SqlSession session = factory.openSession()) {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            return taskRepository.findOneByIdAndUserId(id, userId);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
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
        if (!projectId.isEmpty()) task.setProjectId(projectId);
        SqlSession session = factory.openSession();
        try {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            taskRepository.persist(task);
            session.commit();
        } catch (PersistenceException e) {
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, final String projectId, final Status status) {
        SqlSession session = factory.openSession();
        try {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            @Nullable final Task task = taskRepository.findOneByIdAndUserId(id, userId);
            if (task != null) {
                if (!name.isEmpty()) task.setName(name);
                if (!description.isEmpty()) task.setDescription(description);
                if (startDate != null) task.setStartDate(startDate);
                if (finishDate != null) task.setFinishDate(finishDate);
                if (!projectId.isEmpty()) task.setProjectId(projectId);
                if (status != null) task.setStatus(status);
                taskRepository.merge(task);
                session.commit();
            }
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public List<Task> getListByProjectId(final String userId, final String projectId) {
        try (SqlSession session = factory.openSession()) {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            return taskRepository.findAllByProjectIdAndUserId(projectId, userId);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    @Override
    public void remove(final String userId, final String id) {
        SqlSession session = factory.openSession();
        try {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            @Nullable final Task task = taskRepository.findOneByIdAndUserId(id, userId);
            if (task != null)
                taskRepository.remove(task);
            session.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Task> getSortedListByProjectId(String userId, String projectId, String sortField, boolean isStraight) {
        try (SqlSession session = factory.openSession()) {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            switch (sortField) {
                case START_DATE_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByStartDate(userId, projectId);
                    } else {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByStartDateDesc(userId, projectId);
                    }
                case FINISH_DATE_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByFinishDate(userId, projectId);
                    } else {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByFinishDateDesc(userId, projectId);
                    }
                case CREATION_DATE_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByCreationDate(userId, projectId);
                    } else {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByCreationDateDesc(userId, projectId);
                    }
                case STATUS_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByStatus(userId, projectId);
                    } else {
                        return taskRepository.findAllByUserIdAndProjectIdOrderByStatusDesc(userId, projectId);
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
    public List<Task> getSortedList(String userId, String sortField, boolean isStraight) {
        try (SqlSession session = factory.openSession()) {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            switch (sortField) {
                case START_DATE_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdOrderByStartDate(userId);
                    } else {
                        return taskRepository.findAllByUserIdOrderByStartDateDesc(userId);
                    }
                case FINISH_DATE_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdOrderByFinishDate(userId);
                    } else {
                        return taskRepository.findAllByUserIdOrderByFinishDateDesc(userId);
                    }
                case CREATION_DATE_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdOrderByCreationDate(userId);
                    } else {
                        return taskRepository.findAllByUserIdOrderByCreationDateDesc(userId);
                    }
                case STATUS_SORT_STRING:
                    if (isStraight) {
                        return taskRepository.findAllByUserIdOrderByStatus(userId);
                    } else {
                        return taskRepository.findAllByUserIdOrderByStatusDesc(userId);
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
    public List<Task> getListByKeyword(String userId, String keyword) {
        try (SqlSession session = factory.openSession()) {
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            return taskRepository.findAllByUserIdAndKeyword(userId, keyword);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
