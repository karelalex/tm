package ru.karelin.tmserver.repository;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepositoryHiber implements TaskRepository {
    @Override
    public List<Task> findAllByProjectId(String projectId, @NotNull EntityManager em) {
        return em.createQuery("SELECT t from Task t where t.project.id = :pid", Task.class)
                .setParameter("pid", projectId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.project.id = :pid and t.user.id = :uid", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDate(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate desc", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStatus(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status desc ", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate desc ", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(String userId, String projectId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate desc ", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndKeyword(String userId, String key, @NotNull EntityManager em) {
        return em.createQuery( "select t from Task t where t.user.id = :uid and (t.description like %:key% or t.name like %:key%)", Task.class)
                .setParameter("uid", userId)
                .setParameter("key", key)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDate(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.startDate", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDateDesc(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.startDate desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatus(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.status", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatusDesc(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.status desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDate(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.finishDate", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDateDesc(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.finishDate desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDate(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.creationDate", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDateDesc(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.creationDate desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserId(String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.user.id = :uid ", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public Task findOneByIdAndUserId(String id, String userId, @NotNull EntityManager em) {
        return em.createQuery("select t from Task t where t.id = :id and t.user.id = :uid", Task.class).getSingleResult();
    }

    @Override
    public List<Task> findAll(@NotNull EntityManager em) {
        return em.createQuery("select t from Task t", Task.class).getResultList();
    }

    @Override
    public Task findOne(String id, @NotNull EntityManager em) {
        return em.find(Task.class, id);
    }

    @Override
    public void persist(Task task, @NotNull EntityManager em) {
        em.persist(task);
    }

    @Override
    public void merge(Task task, @NotNull EntityManager em) {
        em.merge(task);
    }

    @Override
    public void remove(Task task, @NotNull EntityManager em) {
        em.remove(task);
    }

    @Override
    public void removeAll(@NotNull EntityManager em) {
        em.createQuery("delete from Task ").executeUpdate();
    }
}
