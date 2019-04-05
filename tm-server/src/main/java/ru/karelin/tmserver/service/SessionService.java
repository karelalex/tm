package ru.karelin.tmserver.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.util.MD5Generator;
import ru.karelin.tmserver.util.SignatureUtil;

import java.util.Date;

public final class SessionService {
    @NotNull private SqlSessionFactory factory;
    private static final String SALT = "keramic";
    private static final int CIRCLE = 251;

    public SessionService(@NotNull SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Nullable
    public Session getNewSession(final String login, final String password) {
        @Nullable final  User user = userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
        if (user!=null){
            Session session = new Session();
            session.setUserId(user.getId());
            session.setSignature(SignatureUtil.sign(session, SALT, CIRCLE));
            sessionRepository.persist(session);
            return session;
        }
        return null;
    }

    public void removeOldSessions(int minutes){
        Date date = new Date();
        date.setTime(date.getTime() - (minutes * 60L * 1000L));
        sessionRepository.removeOlder(date);
    }
    public void removeSession(@Nullable String sessionId) throws WrongSessionException {
        final Session session = sessionRepository.findOne(sessionId);
        if(session!=null) {
            sessionRepository.remove(session);
        }
        else throw new WrongSessionException("No such session found");
    }

    public boolean isSessionExists(@Nullable Session session) throws WrongSessionException {
        if(session==null) throw new WrongSessionException("No such session found");
        String signature = session.getSignature();
        session.setSignature(null);
        if(signature.equals(SignatureUtil.sign(session, SALT, CIRCLE))) {
            Session tempSession = sessionRepository.findOne(session.getId());
            if (tempSession==null) throw new WrongSessionException("No such session found");
            session.setSignature(signature);
            return true;
        }
        else throw new WrongSessionException("No such session found");
    }
}
