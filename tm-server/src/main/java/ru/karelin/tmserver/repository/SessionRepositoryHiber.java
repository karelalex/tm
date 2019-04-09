package ru.karelin.tmserver.repository;


import ru.karelin.tmserver.entity.Session;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class SessionRepositoryHiber implements ru.karelin.tmserver.api.repository.SessionRepository {
    @Override
    public void removeOlder(Date date, EntityManager em) {
        em.createQuery("DELETE FROM Session s WHERE s.creationDate < :date").setParameter("date", date).executeUpdate();
    }

    @Override
    public List<Session> findAll(EntityManager em) {
        return em.createQuery("SELECT s FROM Session s", Session.class).getResultList();
    }

    @Override
    public Session findOne(String id, EntityManager em) {
        return em.find(Session.class, id);
    }

    @Override
    public void persist(Session session, EntityManager em) {
        em.persist(session);
    }

    @Override
    public void merge(Session session, EntityManager em) {
        em.merge(session);
    }

    @Override
    public void remove(Session session, EntityManager em) {
        em.remove(session);
    }

    @Override
    public void removeAll(EntityManager em) {
        em.createQuery("DELETE from Session").executeUpdate();
    }
}
