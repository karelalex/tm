package ru.karelin.tm.api.repository;

import ru.karelin.tm.entity.User;

public interface UserRepository extends Repository<User> {
    User findOneByLoginAndPassword(String login, String passHash);

    User findOneByLogin(String login);
}
