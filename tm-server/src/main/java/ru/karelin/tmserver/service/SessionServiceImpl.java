package ru.karelin.tmserver.service;


import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.SessionService;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.util.MD5Generator;
import ru.karelin.tmserver.util.SignatureUtil;

import java.util.Date;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Environment environment;

    private static final String SALT = "keramic";
    private static final int CIRCLE = 251;

    @Override
    @Nullable
    @Transactional
    public Session getNewSession(final String login, final String password) {
        @Nullable final User user = userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
        if (user != null) {
            Session session = new Session();
            session.setUser(user);
            session.setSignature(SignatureUtil.sign(session.getId() + session.getUser().getId(), SALT, CIRCLE));
            sessionRepository.save(session);
            return session;
        }
        return null;
    }

    @Override
    @Transactional
    public void removeOldSessions(int minutes) {
        Date date = new Date();
        date.setTime(date.getTime() - (minutes * 60L * 1000L));
        sessionRepository.removeOlder(date);
    }

    @Override
    @Transactional
    public void removeSession(@Nullable String sessionId) throws WrongSessionException {
        final Session session = sessionRepository.findOne(sessionId);
        if (session != null) {
            sessionRepository.delete(session);
        } else throw new WrongSessionException("No such session found");
    }

    @Override
    public boolean isSessionExists(@Nullable SessionDto session) throws WrongSessionException {
        if (session == null) throw new WrongSessionException("No such session found");
        String signature = session.getSignature();
        session.setSignature(null);
        if (signature.equals(SignatureUtil.sign(session.getId() + session.getUserId(), SALT, CIRCLE))) {
            Session tempSession = sessionRepository.findOne(session.getId());
            if (tempSession == null || !tempSession.getUser().getId().equals(session.getUserId()))
                throw new WrongSessionException("No such session found");
            session.setSignature(signature);
            return true;
        } else throw new WrongSessionException("No such session found");
    }

    @Override
    public String serverInfo() {
        return "This server is running on "+environment.getProperty("host")+":"+environment.getProperty("port");
    }
}
