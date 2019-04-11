package ru.karelin.tmserver.endpoint;

import lombok.NoArgsConstructor;
import ru.karelin.tmserver.api.service.UserService;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@Singleton
public class UserEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private SessionService sessionServiceImpl;

    @WebMethod public boolean isUserExistsByLogin (@WebParam(name = "login") final String login){
        return userService.isUserExistByLogin(login);
    }

    @WebMethod public void registerNewUser(@WebParam(name = "login") final String login,
                                           @WebParam(name = "password") final String password,
                                           @WebParam(name = "userName") final String name){
        userService.registerNewUser(login, password.toCharArray(), name);
    }

    @WebMethod
    public User getCurrentUser(@WebParam(name = "session") final SessionDto session) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) {
            return userService.getUserById(session.getUserId());
        }
        return null;
    }

    @WebMethod
    public void editUser (@WebParam(name = "session") final SessionDto session,
                          @WebParam (name = "userName") final String userName) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) {
            userService.editUser(session.getUserId(), userName);
        }
    }

    @WebMethod
    public boolean changePassword(@WebParam (name = "session") final SessionDto session,
                                  @WebParam (name = "oldPassword") final String password,
                                  @WebParam (name = "newPassword") final String newPassword) throws WrongSessionException {
        if(sessionServiceImpl.isSessionExists(session)) {
            return userService.changePassword(session.getUserId(), password.toCharArray(), newPassword.toCharArray());
        }
        return false;
    }

}
