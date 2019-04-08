package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.Session;

import javax.persistence.EntityManager;
import java.util.Date;

public interface SessionRepository extends Repository<Session> {
    void removeOlder(Date date, EntityManager em);
}
