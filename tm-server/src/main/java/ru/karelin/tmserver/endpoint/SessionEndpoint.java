package ru.karelin.tmserver.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SessionEndpoint {
    private SessionService sessionServiceImpl;

    public SessionEndpoint(SessionService sessionServiceImpl) {
        this.sessionServiceImpl = sessionServiceImpl;
    }

    @WebMethod
    public Session login(@WebParam(name = "login") final String login,
                         @WebParam(name = "password") final String password) {
        return sessionServiceImpl.getNewSession(login, password);
    }

    @WebMethod
    public void logout(@WebParam(name = "session") @Nullable final SessionDto session) throws WrongSessionException {
        if (sessionServiceImpl.isSessionExists(session)){
            sessionServiceImpl.removeSession(session.getId());
        }

    }
}
