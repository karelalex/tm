package ru.karelin.tmserver.endpoint;

import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SessionEndpoint {
    private SessionService sessionService;

    public SessionEndpoint(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @WebMethod
    public Session login(@WebParam(name = "login") final String login,
                         @WebParam(name = "password") final String password) {
        return sessionService.getNewSession(login, password);
    }

    @WebMethod
    public void logout(@WebParam(name = "session") @Nullable final Session session) throws WrongSessionException {
        if (sessionService.isSessionExists(session)){
            sessionService.removeSession(session.getId());
        }

    }
}
