package ru.karelin.tm.service;

import ru.karelin.tm.MD5Generator;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.repository.UserRepository;

public class UserService {
    private MD5Generator md5Generator;
    private UserRepository userRepository;

    public UserService(MD5Generator md5Generator, UserRepository userRepository) {
        this.md5Generator = md5Generator;
        this.userRepository = userRepository;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userRepository.findOneByLoginAndPassword(login, md5Generator.generate(password));
    }

    public boolean isUserExistByLogin(String login){
        return userRepository.findOneByLogin(login) != null;
    }

}
