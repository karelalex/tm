package ru.karelin.tmserver.repository;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryHiber implements ru.karelin.tmserver.api.repository.UserRepository {

    @NotNull
    private final EntityManager em;

    public UserRepositoryHiber(@NotNull EntityManager em) {
        this.em = em;
    }

    @Override
    public User findOneByLoginAndPassword(String login, String passHash) {
        return em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.passwordHash = :pass", User.class)
                .setParameter("login", login)
                .setParameter("pass", passHash)
                .getSingleResult();

    }

    @Override
    public User findOneByLogin(String login) {
        return em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User findOne(String id) {
        return em.find(User.class, id);
    }

    @Override
    public void persist(User user) {
        em.persist(user);
    }

    @Override
    public void merge(User user) {
        em.merge(user);
    }

    @Override
    public void remove(User user) {
        em.remove(user);
    }

    @Override
    public void removeAll() {
        em.createQuery("DELETE from User").executeUpdate();
    }
}
