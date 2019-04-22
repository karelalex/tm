package ru.karelin.tmserver.service;


import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;
import ru.karelin.tmserver.util.MD5Generator;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Nullable
    @Override
    public User getUserByLoginAndPassword(final String login, final char[] password) {
        return userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
    }

    @Override
    public boolean isUserExistByLogin(final String login) {
        return userRepository.findOneByLogin(login) != null;
    }

    @Override
    public void registerNewUser(final String login, final char[] pass, final String name) {
        registerNewUser(login, pass, name, RoleType.ORDINARY_USER);
    }

    @Nullable
    @Override
    public User getUserById(final String userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public void editUser(final String userId, final String userName) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user != null) {
            if (!userName.isEmpty()) user.setUserName(userName);
            userRepository.save(user);
        }
    }

    @Override
    public void registerNewUser(final String login, char[] pass, final String name, final RoleType role) {
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(MD5Generator.generate(pass));
        user.setUserName(name);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public boolean changePassword(final String userId, final char[] oldPass, final char[] newPass) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null || !user.getPasswordHash().equals(MD5Generator.generate(oldPass))) return false;
        user.setPasswordHash(MD5Generator.generate(newPass));
        userRepository.save(user);
        return true;
    }
}