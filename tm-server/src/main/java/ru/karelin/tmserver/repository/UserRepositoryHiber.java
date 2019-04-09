package ru.karelin.tmserver.repository;

import ru.karelin.tmserver.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryHiber implements ru.karelin.tmserver.api.repository.UserRepository {
    @Override
    public User findOneByLoginAndPassword(String login, String passHash, EntityManager em) {
        return em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.passwordHash = :pass", User.class)
                .setParameter("login", login)
                .setParameter("pass", passHash)
                .getSingleResult();

    }

    @Override
    public User findOneByLogin(String login, EntityManager em) {
        return em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public List<User> findAll(EntityManager em) {
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User findOne(String id, EntityManager em) {
        return em.find(User.class, id);
    }

    @Override
    public void persist(User user, EntityManager em) {
        em.persist(user);
    }

    @Override
    public void merge(User user, EntityManager em) {
        em.merge(user);
    }

    @Override
    public void remove(User user, EntityManager em) {
        em.remove(user);
    }

    @Override
    public void removeAll(EntityManager em) {
        em.createQuery("DELETE from User").executeUpdate();
    }
}
