package ru.karelin.tmserver.endpoint;

import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class UserEndpoint {

    private UserService userService;
    private SessionService sessionService;

    public UserEndpoint(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
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
    public User getCurrentUser(@WebParam(name = "session") final Session session) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) {
            return userService.getUserById(session.getUserId());
        }
        return null;
    }

    @WebMethod
    public void editUser (@WebParam(name = "session") final Session session,
                          @WebParam (name = "userName") final String userName) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) {
            userService.editUser(session.getUserId(), userName);
        }
    }

    @WebMethod
    public boolean changePassword(@WebParam (name = "session") final Session session,
                                  @WebParam (name = "oldPassword") final String password,
                                  @WebParam (name = "newPassword") final String newPassword) throws WrongSessionException {
        if(sessionService.isSessionExists(session)) {
            return userService.changePassword(session.getUserId(), password.toCharArray(), newPassword.toCharArray());
        }
        return false;
    }

}
