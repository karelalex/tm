package ru.karelin.tmserver.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;
import ru.karelin.tmserver.exception.ObjectAlreadyExistsException;
import ru.karelin.tmserver.util.MD5Generator;


public final class UserServiceImpl implements UserService {
    private final MD5Generator md5Generator;
    private final UserRepository userRepository;
    private final static String SERIALIZE_FILE_NAME = "users.ser";
    private final static String JAX_XLM_FILE_NAME = "usersJax.xml";
    private final static String JAX_JSON_FILE_NAME = "usersJax.json";

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
    public User getUserById(final String userId){
        return userRepository.findOne(userId);
    }

    @Override
    public void editUser(final String userId, final String userName) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user != null) {
            if (!userName.isEmpty()) user.setUserName(userName);
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


}
