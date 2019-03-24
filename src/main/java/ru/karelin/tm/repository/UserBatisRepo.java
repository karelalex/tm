package ru.karelin.tm.repository;

import org.apache.ibatis.annotations.*;
import ru.karelin.tm.api.repository.UserRepository;
import ru.karelin.tm.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserBatisRepo extends UserRepository {
    @Override
    @Select("select * from `users` where `login` = #{login} and `pass_hash` = #{passHash} limit 1")
    @Results({
            @Result(property = "passwordHash", column = "pass_hash"),
            @Result(property = "role", column = "user_role"),
            @Result(property = "userName", column = "user_name")
    })
    User findOneByLoginAndPassword(@Param("login") String login, @Param("passHash") String passHash);

    @Override
    @Select("select * from `users` where `login` = #{login} limit 1")
    @Results({
            @Result(property = "passwordHash", column = "pass_hash"),
            @Result(property = "role", column = "user_role"),
            @Result(property = "userName", column = "user_name")
    })
    User findOneByLogin(@Param("login") String login);

    @Override
    @Select("select * from `users`")
    @Results({
            @Result(property = "passwordHash", column = "pass_hash"),
            @Result(property = "role", column = "user_role"),
            @Result(property = "userName", column = "user_name")
    })
    List<User> findAll();

    @Override
    @Select("select * from `users` where `id` = #{id} limit 1")
    @Results({
            @Result(property = "passwordHash", column = "pass_hash"),
            @Result(property = "role", column = "user_role"),
            @Result(property = "userName", column = "user_name")
    })
    User findOne(@Param("id") String id);

    @Override
    @Insert("insert into `users` (`id`, `login`, `pass_hash`, `user_name`, `user_role`) " +
            "values (#{id}, #{login}, #{passwordHash}, #{userName}, #{role})")
    void persist(User user);

    @Override
    @Update("UPDATE `users` set `login` = #{login}, `pass_hash` = #{passwordHash}, `user_name` = #{userName}, `user_role` = #{role} where `id` = #{id}")
    void merge(User user);

    @Override
    @Delete("Delete from `user` where id = #{id}")
    boolean remove(User user);

    @Override
    @Delete("<script>" +
                "delete from user where id in " +
                    "<foreach item='item' index = 'index' collection='users' open='(' separator=',' close = ')'>" +
                        "#{item.id}" +
                    "</foreach>" +
            "</script>")
    void removeAll(@Param("users") Collection<User> users);
}
