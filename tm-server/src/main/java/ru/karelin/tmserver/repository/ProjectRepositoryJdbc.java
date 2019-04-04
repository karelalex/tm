package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.enumeration.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProjectRepositoryJdbc implements ProjectRepository {

    private Connection connection;
    private static final String TABLE_NAME = "project";
    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String CREATION_DATE_FIELD = "creation_date";
    private static final String START_DATE_FIELD = "starting_date";
    private static final String FINISH_DATE_FIELD = "finish_date";
    private static final String STATUS_FIELD = "status";
    private static final String USER_ID_FIELD = "user_id";

    public ProjectRepositoryJdbc(Connection connection) {
        this.connection = connection;
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
    public List<Project> findAllByUserIdAndKeyword(String userId, String key) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? AND (`" + NAME_FIELD + "` LIKE ? OR `" + DESCRIPTION_FIELD + "` LIKE ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, "%" + key + "%");
        statement.setString(3, "%" + key + "%");
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStartDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStartDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStatus() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStatusDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByFinishDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByFinishDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByCreationDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByCreationDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStartDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStartDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStatus(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStatusDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByFinishDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByFinishDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByCreationDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + CREATION_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByCreationDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + CREATION_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserId(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public Project findOneByIdAndUserId(String id, String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? AND `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();

        @Nullable Project task = null;
        if (resultSet.next()) {
            task = fetch(resultSet);
        }
        statement.close();
        return task;
    }

    @Override
    @SneakyThrows
    public List<Project> findAll() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Project> projects = formList(resultSet);
        statement.close();
        return projects;
    }

    @Override
    @SneakyThrows
    public Project findOne(String id) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable Project project = null;
        if (resultSet.next()) {
            project = fetch(resultSet);
        }
        statement.close();
        return project;
    }

    @Override
    @SneakyThrows
    public void persist(Project project) {
        if (project == null) return;
        @NotNull final String query = "INSERT INTO `" + TABLE_NAME + "` " +
                "(`" + ID_FIELD + "`, `" +
                NAME_FIELD + "`, `" +
                DESCRIPTION_FIELD + "`, `" +
                START_DATE_FIELD + "`, `" +
                FINISH_DATE_FIELD + "`, `" +
                CREATION_DATE_FIELD + "`, `" +
                STATUS_FIELD + "`, `" +
                USER_ID_FIELD + "`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, project.getId());
        statement.setString(2, project.getName());
        statement.setString(3, project.getDescription());
        statement.setDate(4, new Date(project.getStartDate().getTime()));
        statement.setDate(5, new Date(project.getFinishDate().getTime()));
        statement.setDate(6, new Date(project.getCreationDate().getTime()));
        statement.setString(7, project.getStatus().name());
        statement.setString(8, project.getUserId());
        statement.executeUpdate();
        statement.close();

    }

    @Override
    @SneakyThrows
    public void merge(Project project) {
        if (project == null) return;
        if (findOne(project.getId()) == null) {
            persist(project);
        }
        else {
            @NotNull final String query = "UPDATE `" + TABLE_NAME + "` SET `" +
                    NAME_FIELD + "` = ?, `" +
                    DESCRIPTION_FIELD + "` = ?, `" +
                    START_DATE_FIELD + "` = ?, `" +
                    FINISH_DATE_FIELD + "` = ?, `" +
                    CREATION_DATE_FIELD + "` = ?, `" +
                    STATUS_FIELD + "` = ?, `" +
                    USER_ID_FIELD + "` = ? WHERE `" + ID_FIELD + "` = ? ";
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(8, project.getId());
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getStartDate().getTime()));
            statement.setDate(4, new Date(project.getFinishDate().getTime()));
            statement.setDate(5, new Date(project.getCreationDate().getTime()));
            statement.setString(6, project.getStatus().name());
            statement.setString(7, project.getUserId());
            statement.executeUpdate();
            statement.close(); 
        }
    }

    @Override
    @SneakyThrows
    public boolean remove(Project project) {
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, project.getId());
        final int i = statement.executeUpdate();
        statement.close();
        return i > 0;
    }

    @Override
    @SneakyThrows
    public void removeAllInList(Collection<Project> projects) {
        StringBuilder insertions = new StringBuilder();
        for (Project p : projects) {
            insertions.append("'").append(p.getId()).append("'").append(", ");
        }
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` IN (" + insertions.substring(0, insertions.length()-2) + ")";
        System.out.println(query);
        @NotNull final Statement statement = connection.createStatement();
        final int i = statement.executeUpdate(query);
        statement.close();
    }

    @SneakyThrows
    private Project fetch(ResultSet resultSet) {
        if (resultSet == null) return null;
        Project project = new Project();
        project.setId(resultSet.getString(ID_FIELD));
        project.setName(resultSet.getString(NAME_FIELD));
        project.setDescription(resultSet.getString(DESCRIPTION_FIELD));
        project.setStartDate(resultSet.getDate(START_DATE_FIELD));
        project.setFinishDate(resultSet.getDate(FINISH_DATE_FIELD));
        project.setCreationDate(resultSet.getDate(CREATION_DATE_FIELD));
        project.setStatus(Status.valueOf(resultSet.getString(STATUS_FIELD)));
        project.setUserId(resultSet.getString(USER_ID_FIELD));
        return project;
    }

    @SneakyThrows
    private List<Project> formList(ResultSet resultSet) {
        if (resultSet==null) return Collections.emptyList();
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) {
            Project project = fetch(resultSet);
            projects.add(project);
        }
        return projects;
    }
}
