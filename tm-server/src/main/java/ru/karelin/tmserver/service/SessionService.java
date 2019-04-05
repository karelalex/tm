package ru.karelin.tmserver.service;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.repository.SessionRepositoryBatis;
import ru.karelin.tmserver.repository.UserRepositoryBatis;
import ru.karelin.tmserver.util.MD5Generator;
import ru.karelin.tmserver.util.SignatureUtil;

import java.util.Date;

public final class SessionService {
    @NotNull
    private SqlSessionFactory factory;
    private static final String SALT = "keramic";
    private static final int CIRCLE = 251;

    public SessionService(@NotNull SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Nullable
    public Session getNewSession(final String login, final String password) {
        SqlSession sqlSession = factory.openSession();
        try {
            UserRepository userRepository = sqlSession.getMapper(UserRepositoryBatis.class);
            @Nullable final User user = userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
            if (user != null) {
                Session session = new Session();
                session.setUserId(user.getId());
                session.setSignature(SignatureUtil.sign(session, SALT, CIRCLE));
                SessionRepository sessionRepository = sqlSession.getMapper(SessionRepositoryBatis.class);
                sessionRepository.persist(session);
                sqlSession.commit();
                return session;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    public void removeOldSessions(int minutes) {
        Date date = new Date();
        date.setTime(date.getTime() - (minutes * 60L * 1000L));
        System.out.println(date);
        SqlSession sqlSession = factory.openSession();
        try {
            SessionRepository sessionRepository = sqlSession.getMapper(SessionRepositoryBatis.class);
            sessionRepository.removeOlder(date);
            sqlSession.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }

    }

    public void removeSession(@Nullable String sessionId) throws WrongSessionException {
        SqlSession sqlSession = factory.openSession();
        try {
            SessionRepository sessionRepository = sqlSession.getMapper(SessionRepositoryBatis.class);
            final Session session = sessionRepository.findOne(sessionId);
            if (session != null) {
                sessionRepository.remove(session);
                sqlSession.commit();
            } else throw new WrongSessionException("No such session found");
        } catch (PersistenceException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public boolean isSessionExists(@Nullable Session session) throws WrongSessionException {
        if (session == null) throw new WrongSessionException("No such session found");
        String signature = session.getSignature();
        session.setSignature(null);
        if (signature.equals(SignatureUtil.sign(session, SALT, CIRCLE))) {
            try (SqlSession sqlSession = factory.openSession()) {
                SessionRepository sessionRepository = sqlSession.getMapper(SessionRepositoryBatis.class);
                Session tempSession = sessionRepository.findOne(session.getId());
                if (tempSession == null) throw new WrongSessionException("No such session found");
                session.setSignature(signature);
                return true;
            }
        } else throw new WrongSessionException("No such session found");
    }
}
