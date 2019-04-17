package ru.karelin.tmserver.service;


import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.TaskService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.repository.ProjectRepositoryHiber;
import ru.karelin.tmserver.repository.TaskRepositoryHiber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TaskServiceImpl implements TaskService {

    private static final String CREATION_DATE_SORT_STRING = "cre";
    private static final String FINISH_DATE_SORT_STRING = "fin";
    private static final String START_DATE_SORT_STRING = "start";
    private static final String STATUS_SORT_STRING = "stat";

    @Inject
    @PersistenceUnitName("ENTERPRISE")
    private EntityManagerFactory factory;

    @Inject
    private UserRepository userRepository;




    @Override
    public List<Task> getList(String userId) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        try {
            return taskRepository.findAllByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean checkId(String userId, String id) {
        return getOne(userId, id) != null;
    }

    @Override
    public Task getOne(String userId, String id) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        try {
            return taskRepository.findOneByIdAndUserId(id, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void create(final String userId, final String name, final String description, final Date startDate, final Date finishDate, final String projectId) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
                ProjectRepository projectRepository = new ProjectRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            User user = userRepository.findOne(userId);
            if (user == null) {
                transaction.rollback();
                return;
            }
            Project project = projectRepository.findOneByIdAndUserId(projectId, userId);
            if (project == null) {
                transaction.rollback();
                return;
            }
            @NotNull final Task task = new Task();
            task.setUser(user);
            task.setStatus(Status.PLANNED);
            task.setProject(project);
            task.setName(name);
            task.setDescription(description);
            task.setStartDate(startDate);
            task.setFinishDate(finishDate);
            taskRepository.persist(task);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name, @NotNull final String description, @Nullable final Date startDate, @Nullable final Date finishDate, @NotNull final String projectId, @Nullable final Status status) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        ProjectRepository projectRepository = new ProjectRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            @Nullable final Task task = taskRepository.findOneByIdAndUserId(id, userId);
            if (task != null) {
                if (!name.isEmpty()) task.setName(name);
                if (!description.isEmpty()) task.setDescription(description);
                if (startDate != null) task.setStartDate(startDate);
                if (finishDate != null) task.setFinishDate(finishDate);
                if (!projectId.isEmpty()) {
                    @Nullable final Project project = projectRepository.findOneByIdAndUserId(projectId, userId);
                    if (project == null) {
                        transaction.rollback();
                        return;
                    }
                    task.setProject(project);
                }
                if (status != null) task.setStatus(status);
                taskRepository.merge(task);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public List<Task> getListByProjectId(final String userId, final String projectId) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        try {
            return taskRepository.findAllByProjectIdAndUserId(projectId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }


    @Override
    public void remove(final String userId, final String id) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            @Nullable final Task task = taskRepository.findOneByIdAndUserId(id, userId);
            if (task != null) {
                taskRepository.remove(task);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Task> getSortedListByProjectId(String userId, String projectId, String sortField, boolean isStraight) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Task> getSortedList(String userId, String sortField, boolean isStraight) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Task> getListByKeyword(String userId, String keyword) {
        EntityManager em = factory.createEntityManager();
        TaskRepository taskRepository = new TaskRepositoryHiber(em);
        try {
            return taskRepository.findAllByUserIdAndKeyword(userId, keyword);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

}
