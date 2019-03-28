package ru.karelin.tmserver.endpoint;

import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class UserEndpoint {

    private UserService userService;

    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @WebMethod public boolean isUserExistsByLogin (@WebParam(name = "login") String login){
        return userService.isUserExistByLogin(login);
    }

    @WebMethod public void registerNewUser(@WebParam String login, @WebParam String password, @WebParam String name){
        userService.registerNewUser(login, password.toCharArray(), name);
    }

    @WebMethod
    public User getUser(@WebParam String login, @WebParam String password){
        return userService.getUserByLoginAndPassword(login, password.toCharArray());
    }
}
