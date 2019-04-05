package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.entity.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SessionRepositoryJdbc implements SessionRepository {
    private Connection connection;
    private static final String TABLE_NAME = "session";

    private static final String ID_FIELD = "id";
    private static final String SIGNATURE_FIELD = "signature";
    private static final String CREATION_TIME_FIELD = "creation_time";
    private static final String USER_ID_FIELD = "user_id";

    public SessionRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    @SneakyThrows
    public List<Session> findAll() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        @NotNull final List<Session> users = new ArrayList<>();
        while (resultSet.next()) {
            Session user = fetch(resultSet);
            users.add(user);
        }
        statement.close();
        return users;
    }

    @Override
    @SneakyThrows
    public Session findOne(String id) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable Session session = null;
        if (resultSet.next()) {
            session = fetch(resultSet);
        }
        statement.close();
        return session;
    }

    @Override
    @SneakyThrows
    public void persist(Session session) {
        if (session==null) return;
        @NotNull final String query = "INSERT INTO `" + TABLE_NAME + "` " +
                "(`" + ID_FIELD + "`, `" + SIGNATURE_FIELD + "`, `" + CREATION_TIME_FIELD + "`, `" + USER_ID_FIELD + "`)" +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, session.getId());
        statement.setString(2, session.getSignature());
        statement.setTimestamp(3, new Timestamp(session.getCreationDate().getTime()));
        statement.setString(4, session.getUserId());
        statement.executeUpdate();
        statement.close();

    }

    @Override
    @SneakyThrows
    public void merge(Session session) {
        if(session==null) return;
        if(findOne(session.getId())==null){
            persist(session);
        }
        else {
            @NotNull final String query = "UPDATE `" + TABLE_NAME + "` SET `" +
                    SIGNATURE_FIELD + "` = ?, `" +
                    CREATION_TIME_FIELD + "` = ?, `" +
                    USER_ID_FIELD + "` = ? " +
                    "WHERE `" + ID_FIELD + "` = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(4, session.getId());
            statement.setString(1, session.getSignature());
            statement.setTimestamp(2, new Timestamp(session.getCreationDate().getTime()));
            statement.setString(3, session.getUserId());
            statement.executeUpdate();
            statement.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean remove(Session session) {
        @NotNull final String query = "DELETE from `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, session.getId());
        final int i = statement.executeUpdate();
        statement.close();
        return i>0;
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query = "DELETE from `" + TABLE_NAME + "`";
        @NotNull final Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeOlder(Date date) {
        @NotNull final String query = "DELETE from `" + TABLE_NAME +"` WHERE `" + CREATION_TIME_FIELD + "` < ? ";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setTimestamp(1, new Timestamp(date.getTime()));
        statement.executeUpdate();
        statement.close();
    }

    @SneakyThrows
    private Session fetch (ResultSet resultSet) {
        if (resultSet==null) return null;
        Session session = new Session();
        session.setId(resultSet.getString(ID_FIELD));
        session.setUserId(resultSet.getString(USER_ID_FIELD));
        session.setSignature(resultSet.getString(SIGNATURE_FIELD));
        session.setCreationDate(resultSet.getTimestamp(CREATION_TIME_FIELD));
        return session;
    }
}
