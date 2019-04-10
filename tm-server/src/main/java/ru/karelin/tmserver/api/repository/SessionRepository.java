package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.Session;

import java.util.Date;

public interface SessionRepository extends Repository<Session> {
    void removeOlder(Date date);
}
