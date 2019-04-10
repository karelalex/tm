package ru.karelin.tmserver.repository;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectRepositoryHiber implements ProjectRepository {
    @Override
    public List<Project> findAllByUserIdAndKeyword(String userId, String key, @NotNull EntityManager em) {
        key = "%"+key+"%";
        return em.createQuery("select p from Project p where p.user.id = :uid AND (p.name like :key or p.description like :key)", Project.class)
                .setParameter("uid", userId)
                .setParameter("key", key)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByStartDate(String userId, @NotNull EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.startDate", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByStartDateDesc(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.startDate desc ", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByStatus(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.status ", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByStatusDesc(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.status desc ", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByFinishDate(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.finishDate ", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByFinishDateDesc(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.finishDate desc ", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByCreationDate(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.creationDate", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserIdOrderByCreationDateDesc(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid order by p.creationDate desc ", Project.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Project> findAllByUserId(String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.user.id = :uid", Project.class).setParameter("uid", userId).getResultList();
    }

    @Override
    public Project findOneByIdAndUserId(String id, String userId, EntityManager em) {
        return em.createQuery("select p from Project p where p.id = :id and p.user.id = :uid", Project.class)
                .setParameter("uid", userId)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Project> findAll(EntityManager em) {
        return em.createQuery("select p from Project p", Project.class).getResultList();
    }

    @Override
    public Project findOne(String id, EntityManager em) {
        return em.find(Project.class, id);
    }

    @Override
    public void persist(Project project, EntityManager em) {
        em.persist(project);
    }

    @Override
    public void merge(Project project, EntityManager em) {
        em.merge(project);
    }

    @Override
    public void remove(Project project, EntityManager em) {
        em.remove(project);
    }

    @Override
    public void removeAll(EntityManager em) {

    }
}
