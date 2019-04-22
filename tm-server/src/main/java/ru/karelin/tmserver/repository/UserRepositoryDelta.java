package ru.karelin.tmserver.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;

@Repository
public interface UserRepositoryDelta extends CrudRepository<User, String>, UserRepository  {
    @Override
    @Query(value = "select u from User u where u.login = :login and u.passwordHash = :pass")
    User findOneByLoginAndPassword(@Param("login") String login, @Param("pass") String passHash);


    User findOneByLogin( String login);

    @Override
    @Query("select u from User u where u.id= :id")
    User findOne(@Param("id") String id);



}
