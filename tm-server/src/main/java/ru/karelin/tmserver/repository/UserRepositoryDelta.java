package ru.karelin.tmserver.repository;

import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;

@Repository
//@Transactional
public interface UserRepositoryDelta extends FullEntityRepository<User, Long>, UserRepository  {
    @Override
    @Query("select u from User u where u.login = :login and u.passwordHash = :pass")
    User findOneByLoginAndPassword(@QueryParam("login") String login, @QueryParam("pass") String passHash);

    @Override
    @Query("select u from User u where u.login = :login")
    User findOneByLogin(@QueryParam("login") String login);

    @Override
    @Query("select u from User u where u.id = :id")
    User findOne(@QueryParam("id") String id);

    @Override
    void persist(User user);

    @Override
    User merge(User user);

    @Override
    @Query("delete from User")
    void removeAll();
}
