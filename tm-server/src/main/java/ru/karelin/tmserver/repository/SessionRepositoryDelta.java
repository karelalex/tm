package ru.karelin.tmserver.repository;

import org.apache.deltaspike.data.api.*;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.entity.Session;

import java.util.Date;

@Repository
public interface SessionRepositoryDelta extends FullEntityRepository<Session, String>, SessionRepository {
    @Override
    @Modifying
    @Query("Delete from Session s where s.creationDate < :date")
    void removeOlder(@QueryParam("date") Date date);

    @Query(value = "SELECT s FROM Session s WHERE s.id = :sid", singleResult = SingleResultType.OPTIONAL)
    Session findOne(@QueryParam("sid") String id);


    @Query(singleResult = SingleResultType.OPTIONAL)
    Session findById(String id);

    @Override
    @Query("Delete from Session")
    @Modifying
    void removeAll();
}
