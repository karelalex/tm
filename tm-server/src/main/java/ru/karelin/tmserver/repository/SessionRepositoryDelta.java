package ru.karelin.tmserver.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.entity.Session;

import java.util.Date;

public interface SessionRepositoryDelta extends FullEntityRepository<Session, String>, SessionRepository {
    @Override
    void removeOlder(Date date);

    @Override
    Session findOne(String id);

    @Override
    @Query()
    void removeAll();
}
