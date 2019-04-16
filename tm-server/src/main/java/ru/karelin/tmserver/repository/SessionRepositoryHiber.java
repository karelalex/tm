package ru.karelin.tmserver.repository;


import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.entity.Session;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class SessionRepositoryHiber implements ru.karelin.tmserver.api.repository.SessionRepository {

    @NotNull final private EntityManager em;

    public SessionRepositoryHiber(@NotNull EntityManager em) {
        this.em = em;
    }

    @Override
    public void removeOlder(Date date) {
        em.createQuery("DELETE FROM Session s WHERE s.creationDate < :date").setParameter("date", date).executeUpdate();
    }

    @Override
    public List<Session> findAll() {
        return em.createQuery("SELECT s FROM Session s", Session.class).getResultList();
    }

    @Override
    public Session findOne(String id) {
        return em.find(Session.class, id);
    }

    @Override
    public void persist(Session session) {
        em.persist(session);
    }

    @Override
    public void merge(Session session) {
        em.merge(session);
    }

    @Override
    public void remove(Session session) {
        em.remove(session);
    }

    @Override
    public void removeAll() {
        em.createQuery("DELETE from Session").executeUpdate();
    }
}
