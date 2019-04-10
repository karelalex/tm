package ru.karelin.tmserver.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.ProjectService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.Status;
import ru.karelin.tmserver.repository.ProjectRepositoryHiber;
import ru.karelin.tmserver.repository.UserRepositoryHiber;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        try {
            return projectRepository.findAllByUserId(userId, em);
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

    @Nullable
    @Override
    public Project getOne(String userId, String id) {
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        try {
            return projectRepository.findOneByIdAndUserId(id, userId, em);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void create(@NotNull final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        UserRepository userRepository = new UserRepositoryHiber();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            @NotNull final Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setStartDate(startDate);
            project.setFinishDate(finishDate);
            User user = userRepository.findOne(userId, em);
            if (user==null){
                transaction.rollback();
                return;
            }
            project.setUser(user);
            project.setStatus(Status.PLANNED);
            projectRepository.persist(project, em);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, Status status) {
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            @Nullable final Project project = projectRepository.findOneByIdAndUserId(id, userId, em);
            if (project != null) {
                if (!name.isEmpty()) project.setName(name);
                if (!description.isEmpty()) project.setDescription(description);
                if (startDate != null) project.setStartDate(startDate);
                if (finishDate != null) project.setFinishDate(finishDate);
                if (status != null) project.setStatus(status);
                projectRepository.merge(project, em);
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
    public List<Project> getSortedList(String userId, String sortField, boolean isStraight) {
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        try {
            switch (sortField) {
                case START_DATE_SORT_STRING:
                    if (isStraight) {
                        return projectRepository.findAllByUserIdOrderByStartDate(userId, em);
                    } else {
                        return projectRepository.findAllByUserIdOrderByStartDateDesc(userId, em);
                    }
                case FINISH_DATE_SORT_STRING:
                    if (isStraight) {
                        return projectRepository.findAllByUserIdOrderByFinishDate(userId, em);
                    } else {
                        return projectRepository.findAllByUserIdOrderByFinishDateDesc(userId, em);
                    }
                case CREATION_DATE_SORT_STRING:
                    if (isStraight) {
                        return projectRepository.findAllByUserIdOrderByCreationDate(userId, em);
                    } else {
                        return projectRepository.findAllByUserIdOrderByCreationDateDesc(userId, em);
                    }
                case STATUS_SORT_STRING:
                    if (isStraight) {
                        return projectRepository.findAllByUserIdOrderByStatus(userId, em);
                    } else {
                        return projectRepository.findAllByUserIdOrderByStatusDesc(userId, em);
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
    public void remove(final String userId, final String id) {
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Project p = projectRepository.findOneByIdAndUserId(id, userId, em);
            if (p != null)
                projectRepository.remove(p, em);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }

    }

    @Override
    public List<Project> getListByKeyword(String userId, String keyword) {
        EntityManager em = factory.createEntityManager();
        ProjectRepository projectRepository = new ProjectRepositoryHiber();
        try {
            return projectRepository.findAllByUserIdAndKeyword(userId, keyword, em);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            em.close();
        }
        return Collections.emptyList();
    }
}
