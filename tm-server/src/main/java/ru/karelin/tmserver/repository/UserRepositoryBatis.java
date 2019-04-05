package ru.karelin.tmserver.repository;

import org.apache.ibatis.annotations.*;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;

import java.util.List;

public interface UserRepositoryBatis extends UserRepository {

    String TABLE_NAME = "user_table";
    String ID_FIELD = "id";
    String LOGIN_FIELD = "login";
    String PASS_HASH_FIELD = "password_hash";
    String USER_NAME_FIELD = "name";
    String ROLE_FIELD = "role";

    @Override
    @ResultMap("UserMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + LOGIN_FIELD + "` = #{login} AND `" + PASS_HASH_FIELD + "` = #{pass}")
    User findOneByLoginAndPassword(@Param("login") String login, @Param("pass") String passHash);

    @Override
    @ResultMap("UserMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + LOGIN_FIELD + "` = #{login}")
    User findOneByLogin(@Param("login") String login);

    @Override
    @ResultMap("UserMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "`")
    List<User> findAll();

    @Override
    @ResultMap("UserMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = #{id}")
    User findOne(@Param("id") String id);

    @Override
    @Insert("INSERT INTO `" + TABLE_NAME + "` " +
            "(`" + ID_FIELD + "`, `" + LOGIN_FIELD + "`, `" + PASS_HASH_FIELD + "`, `" + USER_NAME_FIELD + "`, `" + ROLE_FIELD + "`) " +
            "VALUES (#{id}, #{login}, #{passwordHash}, #{userName}, #{role})")
    void persist(User user);

    @Override
    @Update("UPDATE `" + TABLE_NAME + "` SET `" +
            LOGIN_FIELD + "` = #{login}, `" +
            PASS_HASH_FIELD + "` = #{passwordHash}, `" +
            USER_NAME_FIELD + "` = #{userName}, `" +
            ROLE_FIELD + "` = #{role}" +
            "WHERE `" + ID_FIELD + "` = #{id}")
    void merge(User user);

    @Override
    @Delete("DELETE from `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = #{id}")
    boolean remove(User user);

    @Override
    @Delete("DELETE from `" + TABLE_NAME + "`")
    void removeAll();
}
