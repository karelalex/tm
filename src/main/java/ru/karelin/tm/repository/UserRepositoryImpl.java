package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.User;

import java.util.*;

public final class UserRepositoryImpl implements UserRepository {
    private static Map<String, User> users = new HashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(users.values());
    }

    @Override
    public User findOne(final String id) {
        return users.get(id);
    }

    @Override
    public void persist(final User user) {
        if(users.containsKey(user.getId())) throw new ObjectAlreadyExistsException("User with ID="+user.getId()+" is already stored in database");
        users.put(user.getId(), user);
    }

    @Override
    public User merge(final User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public boolean remove(final User user) {
        return users.remove(user.getId())!=null;
    }

    @Override
    public void removeAll(final Collection<User> userCollection) {
        for (User u : userCollection) {
            users.remove(u.getId());
        }
    }

    @Override
    public User findOneByLoginAndPassword(final String login, final String passHash) {
        for (User u : users.values()) {
            if(u.getLogin().equals(login) && u.getPasswordHash().equals(passHash)) return u;
        }
        return null;
    }

    @Override
    public User findOneByLogin(final String login) {
        for (User u : users.values()) {
            if(u.getLogin().equals(login)) return u;
        }
        return null;
    }
}
