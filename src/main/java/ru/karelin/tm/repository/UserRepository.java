package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.User;

import java.util.*;

public class UserRepository implements Repository<User> {
    private static Map<String, User> users = new HashMap<>();

    @Override
    public List<User> findAll() {
        return new ArrayList(users.values());
    }

    @Override
    public User findOne(String id) {
        return users.get(id);
    }

    @Override
    public void persist(User user) {
        if(users.containsKey(user.getId())) throw new ObjectAlreadyExistsException("User with ID="+user.getId()+" is already stored in database");
        users.put(user.getId(), user);
    }

    @Override
    public User merge(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public boolean remove(User user) {
        return users.remove(user.getId())!=null;
    }

    @Override
    public void removeAll(Collection<User> userCollection) {
        for (User u : userCollection) {
            users.remove(u.getId());
        }
    }

    public User findOneByLoginAndPassword(String login, String passHash) {
        for (User u : users.values()
             ) {
            if(u.getLogin().equals(login) && u.getPasswordHash().equals(passHash)) return u;
        }
        return null;
    }
}
