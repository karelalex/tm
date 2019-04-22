package ru.karelin.tmserver.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.entity.Session;

import javax.persistence.QueryHint;
import java.util.Date;

@Repository
public interface SessionRepositoryDelta extends CrudRepository<Session, String>, SessionRepository {
    @Override
    @Modifying
    @Query("Delete from Session s where s.creationDate < :date")
    void removeOlder(@Param("date") Date date);

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    @Query("select s from Session s where s.id=:sid")
    Session findOne(@Param("sid") String id);





}
