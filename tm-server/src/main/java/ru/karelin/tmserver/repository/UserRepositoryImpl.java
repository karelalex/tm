package ru.karelin.tmserver.repository;


import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;

public final class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {


    @Override
    public User findOneByLoginAndPassword(final String login, final String passHash) {
        for (User u : items.values()) {
            if(u.getLogin().equals(login) && u.getPasswordHash().equals(passHash)) return u;
        }
        return null;
    }

    @Override
    public User findOneByLogin(final String login) {
        for (User u : items.values()) {
            if(u.getLogin().equals(login)) return u;
        }
        return null;
    }
}
