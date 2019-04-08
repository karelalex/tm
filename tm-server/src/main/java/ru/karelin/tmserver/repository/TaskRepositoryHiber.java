package ru.karelin.tmserver.repository;

import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskRepositoryHiber implements TaskRepository {
    @Override
    public List<Task> findAllByProjectId(String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDate(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStatus(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(String userId, String projectId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndKeyword(String userId, String key, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDate(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDateDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatus(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatusDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDate(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDateDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDate(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDateDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAllByUserId(String userId, EntityManager em) {
        return null;
    }

    @Override
    public Task findOneByIdAndUserId(String id, String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Task> findAll(EntityManager em) {
        return null;
    }

    @Override
    public Task findOne(String id, EntityManager em) {
        return null;
    }

    @Override
    public void persist(Task task, EntityManager em) {

    }

    @Override
    public void merge(Task task, EntityManager em) {

    }

    @Override
    public void remove(Task task, EntityManager em) {

    }

    @Override
    public void removeAll(EntityManager em) {

    }
}
