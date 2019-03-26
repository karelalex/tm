package ru.karelin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.util.MD5Generator;
import ru.karelin.tm.exception.ObjectAlreadyExistsException;
import ru.karelin.tm.enumeration.RoleType;
import ru.karelin.tm.api.service.UserService;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.api.repository.UserRepository;

import java.io.*;
import java.util.List;

public final class UserServiceImpl implements UserService {
    private final MD5Generator md5Generator;
    private final UserRepository userRepository;

    public UserServiceImpl(@NotNull final MD5Generator md5Generator, @NotNull final UserRepository userRepository) {
        this.md5Generator = md5Generator;
        this.userRepository = userRepository;
    }


    @Override
    public User getUserByLoginAndPassword(final String login, final char[] password) {
        return userRepository.findOneByLoginAndPassword(login, md5Generator.generate(password));
    }

    @Override
    public boolean isUserExistByLogin(final String login) {
        return userRepository.findOneByLogin(login) != null;
    }

    @Override
    public void registerNewUser(final String login, final char[] pass, final String name) {
        registerNewUser(login, pass, name, RoleType.ORDINARY_USER);
    }

    @Override
    public void editUser(final String userId, final String username) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user != null) {
            if (!username.isEmpty()) user.setUserName(username);
            userRepository.merge(user);
        }
    }

    @Override
    public void registerNewUser(final String login, char[] pass, final String name, final RoleType role) {
        if (isUserExistByLogin(login)) throw new ObjectAlreadyExistsException("User with login " + login + " exists");
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(md5Generator.generate(pass));
        user.setUserName(name);
        user.setRole(role);
        userRepository.persist(user);
    }

    @Override
    public boolean changePassword(final String userId, final char[] oldPass, final char[] newPass) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null || !user.getPasswordHash().equals(md5Generator.generate(oldPass))) return false;
        user.setPasswordHash(md5Generator.generate(newPass));
        userRepository.merge(user);
        return true;
    }

    @Override
    public void getSerialize() throws IOException, ClassNotFoundException {
        File f = new File("users.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
        Object o;
        while ((o = objectInputStream.readObject()) != null) {
            System.out.println(o.getClass().getSimpleName());
            if (o instanceof User) {
                userRepository.merge((User) o);
            }
        }
        objectInputStream.close();
    }

    @Override
    public void saveSerialize() throws IOException {
        File f = new File("users.ser");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f))){
            List<User> list = userRepository.findAll();
            for (User u : list) {
                objectOutputStream.writeObject(u);
            }
        }
        catch (EOFException e){
            e.printStackTrace();
        }
    }
}
