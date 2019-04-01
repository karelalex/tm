package ru.karelin.tmserver.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.util.SignatureUtil;

public class SessionService {
    @NotNull private SessionRepository sessionRepository;
    @NotNull private UserRepository userRepository;
    private static final String SALT = "keramic";
    private static final int CIRCLE = 251;

    public SessionService(@NotNull SessionRepository sessionRepository, @NotNull UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Nullable
    public Session getNewSession(final String login, final String password) {
        @Nullable final  User user = userRepository.findOneByLoginAndPassword(login, password);
        if (user!=null){
            Session session = new Session();
            session.setUserId(user.getId());
            session.setSignature(SignatureUtil.sign(session, SALT, CIRCLE));
            sessionRepository.persist(session);
            return session;
        }
        return null;
    }

    public void removeSession(@Nullable String sessionId){
        final Session session = sessionRepository.findOne(sessionId);
        if(session!=null) {
            sessionRepository.remove(session);
        }
        else throw new WrongSessionException("No such session found");
    }

    public boolean isSessionExists(@Nullable Session session){
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
