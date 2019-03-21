package ru.karelin.tm.service;

import ru.karelin.tm.RoleType;
import ru.karelin.tm.entity.User;

public interface UserService {

    User getUserByLoginAndPassword(String login, char[] password);

    boolean isUserExistByLogin(String login);

    void registerNewUser(String login, char[] pass, String name);

    void editUser(String UserId, String username);

    void registerNewUser(String login, char[] pass, String name, RoleType role);

    boolean changePassword(String id, char[] oldPass, char[] newPass);
}
