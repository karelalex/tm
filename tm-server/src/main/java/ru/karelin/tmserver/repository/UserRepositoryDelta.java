package ru.karelin.tmserver.repository;

import org.apache.deltaspike.data.api.*;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;

@Repository
@Transactional
public interface UserRepositoryDelta extends FullEntityRepository<User, Long>, UserRepository  {
    @Override
    @Query(value = "select u from User u where u.login = :login and u.passwordHash = :pass", singleResult = SingleResultType.OPTIONAL)
    User findOneByLoginAndPassword(@QueryParam("login") String login, @QueryParam("pass") String passHash);

    @Override
    @Query(value = "select u from User u where u.login = :login", singleResult = SingleResultType.OPTIONAL)
    User findOneByLogin(@QueryParam("login") String login);

    @Override
    @Query("select u from User u where u.id = :id")
    User findOne(@QueryParam("id") String id);

    @Override
    @Query("delete from User")
    void removeAll();

    @Query(singleResult = SingleResultType.OPTIONAL)
    User findById(String userId);
}
