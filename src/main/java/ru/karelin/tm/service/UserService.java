package ru.karelin.tm.service;

import ru.karelin.tm.MD5Generator;
import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.RoleType;
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

    public User getUserByLoginAndPassword(String login, char[] password) {
        return userRepository.findOneByLoginAndPassword(login, md5Generator.generate(password));
    }
    public boolean isUserExistByLogin(String login){
        return userRepository.findOneByLogin(login) != null;
    }

   public void registerNewUser(String login, char[] pass, String name){
        registerNewUser(login, pass, name, RoleType.ORDINARY_USER);
   }

   public void editUser(User currentUser, String username){
        User user = userRepository.findOne(currentUser.getId());
        if(!username.isEmpty()) user.setUserName(username);
        userRepository.merge(user);
   }

    public void registerNewUser(String login, char[] pass, String name, RoleType role) {
        if(isUserExistByLogin(login)) throw new ObjectAlreadyExistsException("User with login " +login+ " exists");
        User user = new User();
        user.setLogin(login);
        user.setPasswordHash(md5Generator.generate(pass));
        user.setUserName(name);
        user.setRole(role);
        userRepository.persist(user);
    }

    public boolean changePassword(User currentUser, char[] oldPass, char[] newPass) {
        User user = userRepository.findOneByLoginAndPassword(currentUser.getLogin(), md5Generator.generate(oldPass));
        if (user==null) return false;
        user.setPasswordHash(md5Generator.generate(newPass));
        userRepository.merge(user);
        return true;

    }
}
