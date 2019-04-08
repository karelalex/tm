package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.User;

import javax.persistence.EntityManager;

public interface UserRepository extends Repository<User> {
    User findOneByLoginAndPassword(String login, String passHash, EntityManager em);

    User findOneByLogin(String login, EntityManager em);
}
