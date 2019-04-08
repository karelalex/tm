package ru.karelin.tmserver.repository;

import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectRepositoryHiber implements ProjectRepository {
    @Override
    public List<Project> findAllByUserIdAndKeyword(String userId, String key, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByStartDate(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByStartDateDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByStatus(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByStatusDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByFinishDate(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByFinishDateDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByCreationDate(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserIdOrderByCreationDateDesc(String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAllByUserId(String userId, EntityManager em) {
        return null;
    }

    @Override
    public Project findOneByIdAndUserId(String id, String userId, EntityManager em) {
        return null;
    }

    @Override
    public List<Project> findAll(EntityManager em) {
        return null;
    }

    @Override
    public Project findOne(String id, EntityManager em) {
        return null;
    }

    @Override
    public void persist(Project project, EntityManager em) {

    }

    @Override
    public void merge(Project project, EntityManager em) {

    }

    @Override
    public void remove(Project project, EntityManager em) {

    }

    @Override
    public void removeAll(EntityManager em) {

    }
}
