package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

public class UserRepositoryJdbc implements UserRepository {

    private Connection connection;
    private static final String ID_FIELD = "id";
	private static final String LOGIN_FIELD = "login";
	private static final String PASS_HASH_FIELD = "password_hash";
	private static final String USER_NAME_FIELD = "name";
	private static final String ROLE_FIELD = "role";

    public UserRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    @SneakyThrows
    public User findOneByLoginAndPassword(String login, String passHash) {
        @NotNull final String query = "SELECT * FROM `user_table` WHERE `login` = ? AND `password_hash` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, passHash);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable User user = null;
        if (resultSet.next()){
            user = fetch(resultSet);
        }
        statement.close();
        return user;
    }

    @Override
    public User findOneByLogin(String login) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(String id) {
        return null;
    }

    @Override
    public void persist(User user) {

    }

    @Override
    public void merge(User user) {

    }

    @Override
    public boolean remove(User user) {
        return false;
    }

    @Override
    public void removeAll(Collection<User> users) {

    }

    @SneakyThrows
    private User fetch (ResultSet resultSet){
        if(resultSet ==null) return null;
        User user = new User();
        user.setId(resultSet.getString(ID_FIELD));
        user.setLogin(resultSet.getString(LOGIN_FIELD));
        user.setPasswordHash(resultSet.getString(PASS_HASH_FIELD));
        user.setUserName(resultSet.getString(USER_NAME_FIELD));
        user.setRole(RoleType.valueOf(resultSet.getString(ROLE_FIELD)));
        return user;
    }
}
