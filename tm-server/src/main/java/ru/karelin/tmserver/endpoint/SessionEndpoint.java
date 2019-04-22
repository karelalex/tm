package ru.karelin.tmserver.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.karelin.tmserver.api.service.SessionService;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
@Component
public class SessionEndpoint {

    @Autowired
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
    @WebMethod
    public String serverInfo(){
        return sessionService.serverInfo();
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
