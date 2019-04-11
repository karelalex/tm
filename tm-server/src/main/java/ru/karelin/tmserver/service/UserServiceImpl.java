package ru.karelin.tmserver.service;


import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;
import ru.karelin.tmserver.repository.UserRepositoryHiber;
import ru.karelin.tmserver.util.MD5Generator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private EntityManagerFactory factory;

    @Nullable
    @Override
    public User getUserByLoginAndPassword(final String login, final char[] password) {
        EntityManager em = factory.createEntityManager();
        UserRepository userRepository = new UserRepositoryHiber(em);
        try {
            return userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public boolean isUserExistByLogin(final String login) {
        EntityManager em = factory.createEntityManager();
        UserRepository userRepository = new UserRepositoryHiber(em);
        try {
            return userRepository.findOneByLogin(login) != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public void registerNewUser(final String login, final char[] pass, final String name) {
        registerNewUser(login, pass, name, RoleType.ORDINARY_USER);
    }

    @Nullable
    @Override
    public User getUserById(final String userId) {
        EntityManager em = factory.createEntityManager();
        UserRepository userRepository = new UserRepositoryHiber(em);
        try {
            return userRepository.findOne(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void editUser(final String userId, final String userName) {
        EntityManager em = factory.createEntityManager();
        UserRepository userRepository = new UserRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            @Nullable final User user = userRepository.findOne(userId);
            if (user != null) {
                if (!userName.isEmpty()) user.setUserName(userName);
                userRepository.merge(user);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void registerNewUser(final String login, char[] pass, final String name, final RoleType role) {
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(MD5Generator.generate(pass));
        user.setUserName(name);
        user.setRole(role);
        EntityManager em = factory.createEntityManager();
        UserRepository userRepository = new UserRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            userRepository.persist(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean changePassword(final String userId, final char[] oldPass, final char[] newPass) {
        EntityManager em = factory.createEntityManager();
        UserRepository userRepository = new UserRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            @Nullable final User user = userRepository.findOne(userId);
            if (user == null || !user.getPasswordHash().equals(MD5Generator.generate(oldPass))) return false;
            user.setPasswordHash(MD5Generator.generate(newPass));
            userRepository.merge(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }
}
