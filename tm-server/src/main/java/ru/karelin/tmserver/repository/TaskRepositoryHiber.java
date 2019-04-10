package ru.karelin.tmserver.repository;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepositoryHiber implements TaskRepository {

    @NotNull
    private final EntityManager em;

    public TaskRepositoryHiber(@NotNull EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Task> findAllByProjectId(String projectId) {
        return em.createQuery("SELECT t from Task t where t.project.id = :pid", Task.class)
                .setParameter("pid", projectId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId) {
        return em.createQuery("select t from Task t where t.project.id = :pid and t.user.id = :uid", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDate(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate desc", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStatus(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status desc ", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate desc ", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(String userId, String projectId) {
        return em.createQuery("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate desc ", Task.class)
                .setParameter("pid", projectId)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdAndKeyword(String userId, String key) {
        key = '%' + key + '%';
        return em.createQuery( "select t from Task t where t.user.id = :uid and (t.description like :key or t.name like :key)", Task.class)
                .setParameter("uid", userId)
                .setParameter("key", key)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDate(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.startDate", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDateDesc(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.startDate desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatus(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.status", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatusDesc(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.status desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDate(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.finishDate", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDateDesc(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.finishDate desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDate(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.creationDate", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDateDesc(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid order by t.creationDate desc", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public List<Task> findAllByUserId(String userId) {
        return em.createQuery("select t from Task t where t.user.id = :uid ", Task.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    @Override
    public Task findOneByIdAndUserId(String id, String userId) {
        return em.createQuery("select t from Task t where t.id = :id and t.user.id = :uid", Task.class)
                .setParameter("id", id)
                .setParameter("uid", userId)
                .getSingleResult();
    }

    @Override
    public List<Task> findAll() {
        return em.createQuery("select t from Task t", Task.class).getResultList();
    }

    @Override
    public Task findOne(String id) {
        return em.find(Task.class, id);
    }

    @Override
    public void persist(Task task) {
        em.persist(task);
    }

    @Override
    public void merge(Task task) {
        em.merge(task);
    }

    @Override
    public void remove(Task task) {
        em.remove(task);
    }

    @Override
    public void removeAll() {
        em.createQuery("delete from Task ").executeUpdate();
    }
}
