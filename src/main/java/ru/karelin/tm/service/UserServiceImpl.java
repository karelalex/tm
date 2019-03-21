package ru.karelin.tm.service;

import ru.karelin.tm.MD5Generator;
import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.RoleType;
import ru.karelin.tm.entity.User;
import ru.karelin.tm.repository.UserRepository;

public final class UserServiceImpl implements UserService {
    private final MD5Generator md5Generator;
    private final UserRepository userRepository;

    public UserServiceImpl(final MD5Generator md5Generator, final UserRepository userRepository) {
        this.md5Generator = md5Generator;
        this.userRepository = userRepository;
    }


    @Override
    public User getUserByLoginAndPassword(final String login, final char[] password) {
        return userRepository.findOneByLoginAndPassword(login, md5Generator.generate(password));
    }
    @Override
    public boolean isUserExistByLogin(final String login){
        return userRepository.findOneByLogin(login) != null;
    }

   @Override
   public void registerNewUser(final String login, final char[] pass, final String name){
        registerNewUser(login, pass, name, RoleType.ORDINARY_USER);
   }

   @Override
   public void editUser(final String userId, final String username){
        final User user = userRepository.findOne(userId);
        if(!username.isEmpty()) user.setUserName(username);
        userRepository.merge(user);
   }

    @Override
    public void registerNewUser(final String login, char[] pass, final String name, final RoleType role) {
        if(isUserExistByLogin(login)) throw new ObjectAlreadyExistsException("User with login " +login+ " exists");
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(md5Generator.generate(pass));
        user.setUserName(name);
        user.setRole(role);
        userRepository.persist(user);
    }

    @Override
    public boolean changePassword(final String userId, final char[] oldPass, final char[] newPass) {
        final User user = userRepository.findOne(userId);
        if (user==null || !user.getPasswordHash().equals(md5Generator.generate(oldPass))) return false;
        user.setPasswordHash(md5Generator.generate(newPass));
        userRepository.merge(user);
        return true;
    }
}
