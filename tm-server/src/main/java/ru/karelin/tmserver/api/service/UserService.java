package ru.karelin.tmserver.api.service;

import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;




public interface UserService{


    User getUserByLoginAndPassword( String login, char[] password);

    boolean isUserExistByLogin(String login);

    void registerNewUser(String login, char[] pass, String name);

    User getUserById(String userId);

    void editUser(String userId, String userName);

    void registerNewUser(String login, char[] pass, String name, RoleType role);

    boolean changePassword(String userId, char[] oldPass, char[] newPass);
}
