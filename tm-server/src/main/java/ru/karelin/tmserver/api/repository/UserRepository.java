package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.User;



public interface UserRepository extends Repository<User> {
    User findOneByLoginAndPassword(String login, String passHash);

    User findOneByLogin(String login);
}
