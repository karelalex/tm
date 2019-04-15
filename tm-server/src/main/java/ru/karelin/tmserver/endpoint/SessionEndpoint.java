package ru.karelin.tmserver.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

@WebService
@NoArgsConstructor
@Singleton
public class SessionEndpoint {

    @Inject
    private SessionService sessionService;

    @WebMethod
    public SessionDto login(@WebParam(name = "login") final String login,
                         @WebParam(name = "password") final String password) {
        return convert(sessionService.getNewSession(login, password));
    }

    @WebMethod
    public void logout(@WebParam(name = "session") @Nullable final SessionDto session) throws WrongSessionException {
        if (sessionService.isSessionExists(session)){
            sessionService.removeSession(session.getId());
        }

    }

    @Contract("null -> null")
    private SessionDto convert (@Nullable Session session) {
        if (session==null) return null;
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setUserId( session.getUser().getId());
        sessionDto.setSignature( session.getSignature());
        sessionDto.setCreationDate(session.getCreationDate());
        return sessionDto;
    }
}
