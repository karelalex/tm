package ru.karelin.tmserver.repository;

import org.apache.ibatis.annotations.*;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.entity.Session;

import java.util.Date;
import java.util.List;

public interface SessionRepositoryBatis extends SessionRepository {

    String TABLE_NAME = "session";
    String ID_FIELD = "id";
    String SIGNATURE_FIELD = "signature";
    String CREATION_TIME_FIELD = "creation_time";
    String USER_ID_FIELD = "user_id";

    @Override
    @Delete("DELETE from `" + TABLE_NAME +"` WHERE `" + CREATION_TIME_FIELD + "` < #{date}")
    void removeOlder(@Param("date") Date date);

    @Override
    @Results({
            @Result(column = "creation_time", property = "creationDate"),
            @Result(column = "user_id", property = "userId")
    })
    @Select("SELECT * FROM `" + TABLE_NAME + "`")
    List<Session> findAll();

    @Override
    @Results({
            @Result(column = "creation_time", property = "creationDate"),
            @Result(column = "user_id", property = "userId")
    })
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = #{id}")
    Session findOne(@Param("id") String id);

    @Override
    @Insert("INSERT INTO `" + TABLE_NAME + "` " +
            "(`" + ID_FIELD + "`, `" + SIGNATURE_FIELD + "`, `" + CREATION_TIME_FIELD + "`, `" + USER_ID_FIELD + "`)" +
            "VALUES (#{id}, #{signature}, #{creationDate}, #{userId})")
    void persist(Session session);

    @Override
    @Update("UPDATE `" + TABLE_NAME + "` SET `" +
            SIGNATURE_FIELD + "` = #{signature}, `" +
            CREATION_TIME_FIELD + "` = #{creationDate}, `" +
            USER_ID_FIELD + "` = #{userId} " +
            "WHERE `" + ID_FIELD + "` = #{id}")
    void merge(Session session);

    @Override
    @Delete("DELETE from `session` WHERE `id` = #{id}")
    boolean remove(Session session);

    @Override
    @Delete("DELETE from `" + TABLE_NAME + "`")
    void removeAll();
}
