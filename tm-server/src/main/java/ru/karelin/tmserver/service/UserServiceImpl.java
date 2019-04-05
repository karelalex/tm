package ru.karelin.tmserver.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;
import ru.karelin.tmserver.exception.ObjectAlreadyExistsException;
import ru.karelin.tmserver.repository.UserRepositoryBatis;
import ru.karelin.tmserver.util.MD5Generator;

import java.text.ParseException;


public final class UserServiceImpl implements UserService {

    @NotNull
    private final SqlSessionFactory factory;


    public UserServiceImpl(@NotNull SqlSessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public User getUserByLoginAndPassword(final String login, final char[] password) {
        try (SqlSession session = factory.openSession()) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            return userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
        }

    }

    @Override
    public boolean isUserExistByLogin(final String login) {
        try (SqlSession session = factory.openSession()) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            return userRepository.findOneByLogin(login) != null;
        }
    }

    @Override
    public void registerNewUser(final String login, final char[] pass, final String name) {
        registerNewUser(login, pass, name, RoleType.ORDINARY_USER);
    }

    @Override
    public User getUserById(final String userId) {
        try (SqlSession session = factory.openSession()) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            return userRepository.findOne(userId);
        }
    }

    @Override
    public void editUser(final String userId, final String userName) {
        SqlSession session = factory.openSession();
        try {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);

            @Nullable final User user = userRepository.findOne(userId);
            if (user != null) {
                if (!userName.isEmpty()) user.setUserName(userName);
                userRepository.merge(user);
            }
            session.commit();
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void registerNewUser(final String login, char[] pass, final String name, final RoleType role) {
        if (isUserExistByLogin(login)) throw new ObjectAlreadyExistsException("User with login " + login + " exists");
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(MD5Generator.generate(pass));
        user.setUserName(name);
        user.setRole(role);
        SqlSession session = factory.openSession();
        try {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            userRepository.persist(user);
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean changePassword(final String userId, final char[] oldPass, final char[] newPass) {
        SqlSession session = factory.openSession();
        try {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            @Nullable final User user = userRepository.findOne(userId);
            if (user == null || !user.getPasswordHash().equals(MD5Generator.generate(oldPass))) return false;
            user.setPasswordHash(MD5Generator.generate(newPass));
            userRepository.merge(user);
            return true;
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
