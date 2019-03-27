package ru.karelin.tmclient.api.service;

import ru.karelin.tm.entity.User;
import ru.karelin.tm.enumeration.RoleType;

public interface UserService{

    User getUserByLoginAndPassword(String login, char[] password);

    boolean isUserExistByLogin(String login);

    void registerNewUser(String login, char[] pass, String name);

    void editUser(String UserId, String username);

    void registerNewUser(String login, char[] pass, String name, RoleType role);

    boolean changePassword(String id, char[] oldPass, char[] newPass);
}
