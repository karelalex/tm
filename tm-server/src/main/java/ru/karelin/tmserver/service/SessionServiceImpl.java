package ru.karelin.tmserver.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.dto.SessionDto;
import ru.karelin.tmserver.entity.Session;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.WrongSessionException;
import ru.karelin.tmserver.api.service.SessionService;
import ru.karelin.tmserver.repository.SessionRepositoryHiber;
import ru.karelin.tmserver.repository.UserRepositoryHiber;
import ru.karelin.tmserver.util.MD5Generator;
import ru.karelin.tmserver.util.SignatureUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@ApplicationScoped
public class SessionServiceImpl implements SessionService {
    @NotNull
    @Inject
    private EntityManagerFactory factory;
    private static final String SALT = "keramic";
    private static final int CIRCLE = 251;


    @Override
    @Nullable
    public Session getNewSession(final String login, final String password) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        UserRepository userRepository = new UserRepositoryHiber(em);
        SessionRepository sessionRepository = new SessionRepositoryHiber(em);
        try {
            transaction.begin();
            @Nullable final User user = userRepository.findOneByLoginAndPassword(login, MD5Generator.generate(password));
            if (user != null) {
                Session session = new Session();
                session.setUser(user);
                session.setSignature(SignatureUtil.sign(session.getId() + session.getUser().getId(), SALT, CIRCLE));
                sessionRepository.persist(session);
                transaction.commit();
                return session;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public void removeOldSessions(int minutes) {
        Date date = new Date();
        date.setTime(date.getTime() - (minutes * 60L * 1000L));
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        SessionRepository sessionRepository = new SessionRepositoryHiber(em);
        try {
            transaction.begin();
            sessionRepository.removeOlder(date);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }

    }

    @Override
    public void removeSession(@Nullable String sessionId) throws WrongSessionException {
        EntityManager em = factory.createEntityManager();
        SessionRepository sessionRepository = new SessionRepositoryHiber(em);
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            final Session session = sessionRepository.findOne(sessionId);
            if (session != null) {
                sessionRepository.remove(session);
                transaction.commit();
            } else throw new WrongSessionException("No such session found");
        } catch (WrongSessionException e) {
            transaction.rollback();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean isSessionExists(@Nullable SessionDto session) throws WrongSessionException {
        if (session == null) throw new WrongSessionException("No such session found");
        String signature = session.getSignature();
        session.setSignature(null);
        if (signature.equals(SignatureUtil.sign(session.getId() + session.getUserId(), SALT, CIRCLE))) {
            EntityManager em = factory.createEntityManager();
            SessionRepository sessionRepository = new SessionRepositoryHiber(em);
            try {
                Session tempSession = sessionRepository.findOne(session.getId());
                if (tempSession == null || !tempSession.getUser().getId().equals(session.getUserId()))
                    throw new WrongSessionException("No such session found");
                session.setSignature(signature);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }
        } else throw new WrongSessionException("No such session found");
        return false;
    }
}
