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

    @WebMethod public boolean isUserExistsByLogin (@WebParam(name = "login") final String login){
        return userService.isUserExistByLogin(login);
    }

    @WebMethod public void registerNewUser(@WebParam(name = "login") final String login,
                                           @WebParam(name = "password") final String password,
                                           @WebParam(name = "userName") final String name){
        userService.registerNewUser(login, password.toCharArray(), name);
    }

    @WebMethod
    public User getUser(@WebParam(name = "login") final String login,
                        @WebParam(name = "password") final String password){
        return userService.getUserByLoginAndPassword(login, password.toCharArray());
    }

    @WebMethod
    public void editUser (@WebParam(name = "userId") final String userId,
                          @WebParam (name = "userName") final String userName){
        userService.editUser(userId, userName);
    }

    @WebMethod
    public boolean changePassword(@WebParam (name = "userId") final String userId,
                                  @WebParam (name = "oldPassword") final String password,
                                  @WebParam (name = "newPassword") final String newPassword){
        return userService.changePassword(userId, password.toCharArray(), newPassword.toCharArray());
    }
}
