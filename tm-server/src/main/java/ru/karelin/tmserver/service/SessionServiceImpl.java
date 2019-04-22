package ru.karelin.tmserver.service;


import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.SessionService;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.repository.SessionRepositoryDelta;
import ru.karelin.tmserver.util.MD5Generator;
import ru.karelin.tmserver.util.PropertyService;
import ru.karelin.tmserver.util.SignatureUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;

@ApplicationScoped
@Transactional
public class SessionServiceImpl implements SessionService {

    @Inject
    private SessionRepository sessionRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    PropertyService propertyService;

    private static final String SALT = "keramic";
    private static final int CIRCLE = 251;

    @Override
    @Nullable
    public Session getNewSession(final String login, final String password) {
        @Nullable final User user = userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
        if (user != null) {
            Session session = new Session();
            session.setUser(user);
            session.setSignature(SignatureUtil.sign(session.getId() + session.getUser().getId(), SALT, CIRCLE));
            sessionRepository.persist(session);
            return session;
        }
        return null;
    }

    @Override
    public void removeOldSessions(int minutes) {
        Date date = new Date();
        date.setTime(date.getTime() - (minutes * 60L * 1000L));
        sessionRepository.removeOlder(date);
    }

    @Override
    public void removeSession(@Nullable String sessionId) throws WrongSessionException {
        final Session session = ((SessionRepositoryDelta)sessionRepository).findById(sessionId);
        if (session != null) {
            sessionRepository.remove(session);
        } else throw new WrongSessionException("No such session found");
    }

    @Override
    public boolean isSessionExists(@Nullable SessionDto session) throws WrongSessionException {
        if (session == null) throw new WrongSessionException("No such session found");
        String signature = session.getSignature();
        session.setSignature(null);
        if (signature.equals(SignatureUtil.sign(session.getId() + session.getUserId(), SALT, CIRCLE))) {
           // Session tempSession = sessionRepository.findOne(session.getId());
           Session tempSession = ((SessionRepositoryDelta)sessionRepository).findById(session.getId());
            if (tempSession == null || !tempSession.getUser().getId().equals(session.getUserId()))
                throw new WrongSessionException("No such session found");
            session.setSignature(signature);
            return true;
        } else throw new WrongSessionException("No such session found");
    }

    @Override
    public String serverInfo() {
        return "This server is running on "+propertyService.getProperty("app.host")+":"+propertyService.getProperty("app.port");
    }
}
