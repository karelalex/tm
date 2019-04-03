package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.SessionRepository;
import ru.karelin.tmserver.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SessionRepositoryJdbc implements SessionRepository {
    private Connection connection;
    private static final String TABLE_NAME = "session";

    private static final String ID_FIELD = "id";
    private static final String SIGNATURE_FIELD = "signature";
    private static final String CREATION_DATE_FIELD = "creation_date";
    private static final String USER_ID_FIELD = "user_id";

    public SessionRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    @SneakyThrows
    public List<Session> findAll() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @NotNull final List<Session> users = new ArrayList<>();
        while (resultSet.next()) {
            Session user = fetch(resultSet);
            users.add(user);
        }
        statement.close();
        return users;
    }

    @Override
    public Session findOne(String id) {
        return null;
    }

    @Override
    public void persist(Session session) {

    }

    @Override
    public void merge(Session session) {

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
    public void removeAll(Collection<Session> sessions) {
        @NotNull final String query = "DELETE from `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` IN (?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        List<String> idList = new ArrayList<>();
        for (Session u: sessions) {
            idList.add(u.getId());
        }
        String[] ids = idList.toArray(new String[]{});
        statement.setArray(1, connection.createArrayOf("CHAR", ids));
        final int i = statement.executeUpdate();
        statement.close();
    }

    @Override
    @SneakyThrows
    public void removeAll() {
        @NotNull final String query = "DELETE from `" + TABLE_NAME + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
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
        session.setCreationDate(resultSet.getDate(CREATION_DATE_FIELD));
        return session;
    }
}
