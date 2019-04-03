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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserRepositoryJdbc implements UserRepository {

    private Connection connection;
    private static final String TABLE_NAME = "user_table";
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
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + LOGIN_FIELD + "` = ? AND `" + PASS_HASH_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        statement.setString(2, passHash);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable User user = null;
        if (resultSet.next()) {
            user = fetch(resultSet);
        }
        statement.close();
        return user;
    }

    @Override
    @SneakyThrows
    public User findOneByLogin(String login) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + LOGIN_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable User user = null;
        if (resultSet.next()) {
            user = fetch(resultSet);
        }
        statement.close();
        return user;
    }

    @Override
    @SneakyThrows
    public List<User> findAll() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        @NotNull final List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = fetch(resultSet);
            users.add(user);
        }
        statement.close();
        return users;
    }

    @Override
    @SneakyThrows
    public User findOne(String id) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable User user = null;
        if (resultSet.next()) {
            user = fetch(resultSet);
        }
        statement.close();
        return user;
    }

    @Override
    @SneakyThrows
    public void persist(User user) {
        if (user==null) return;
        @NotNull final String query = "INSERT INTO `" + TABLE_NAME + "` " +
                "(`" + ID_FIELD + "`, `" + LOGIN_FIELD + "`, `" + PASS_HASH_FIELD + "`, `" + USER_NAME_FIELD + "`, `" + ROLE_FIELD + "`) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getId());
        statement.setString(2, user.getLogin());
        statement.setString(3, user.getPasswordHash());
        statement.setString(4, user.getUserName());
        statement.setString(5, user.getRole().name());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void merge(User user) {
        if(user==null) return;
        if (findOne(user.getId()) == null) {
            persist(user);
        } else {
            @NotNull final String query = "UPDATE `" + TABLE_NAME + "` SET `" +
                    LOGIN_FIELD + "` = ?, `" +
                    PASS_HASH_FIELD + "` = ?, `" +
                    USER_NAME_FIELD + "` = ?, `" +
                    ROLE_FIELD + "` = ? " +
                    "WHERE `" + ID_FIELD + "` = ?";
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getId());
            statement.executeUpdate();
            statement.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean remove(User user) {
        @NotNull final String query = "DELETE from `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getId());
        final int i = statement.executeUpdate();
        statement.close();
        return i>0;
    }

    @Override
    @SneakyThrows
    public void removeAll(Collection<User> users) {
        StringBuilder insertions = new StringBuilder();
        for (User u : users) {
            insertions.append("'").append(u.getId()).append("'").append(", ");
        }
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` IN (" + insertions.substring(0, insertions.length()-2) + ")";
        System.out.println(query);
        @NotNull final Statement statement = connection.createStatement();
        final int i = statement.executeUpdate(query);
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query = "DELETE from `" + TABLE_NAME + "`";
        @NotNull final Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @SneakyThrows
    private User fetch(ResultSet resultSet) {
        if (resultSet == null) return null;
        User user = new User();
        user.setId(resultSet.getString(ID_FIELD));
        user.setLogin(resultSet.getString(LOGIN_FIELD));
        user.setPasswordHash(resultSet.getString(PASS_HASH_FIELD));
        user.setUserName(resultSet.getString(USER_NAME_FIELD));
        user.setRole(RoleType.valueOf(resultSet.getString(ROLE_FIELD)));
        return user;
    }
}
