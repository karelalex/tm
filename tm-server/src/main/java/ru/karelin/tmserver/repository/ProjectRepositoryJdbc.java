package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.enumeration.Status;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
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
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
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
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStartDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStartDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStatus() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByStatusDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByFinishDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByFinishDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByCreationDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllOrderByCreationDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStartDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStartDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStatus(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByStatusDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByFinishDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByFinishDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByCreationDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + CREATION_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserIdOrderByCreationDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + CREATION_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public List<Project> findAllByUserId(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    @SneakyThrows
    public Project findOneByIdAndUserId(String id, String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? AND `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        @Nullable Project task = null;
        if (resultSet.next()) {
            task = fetch(resultSet);
        }
        return task;
    }

    @Override
    @SneakyThrows
    public List<Project> findAll() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
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
    public void persist(Project task) {
        if (task == null) return;
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
        statement.setString(1, task.getId());
        statement.setString(2, task.getName());
        statement.setString(3, task.getDescription());
        statement.setDate(4, new Date(task.getStartDate().getTime()));
        statement.setDate(5, new Date(task.getFinishDate().getTime()));
        statement.setDate(6, new Date(task.getCreationDate().getTime()));
        statement.setString(7, task.getStatus().name());
        statement.setString(8, task.getUserId());
        statement.executeUpdate();
        statement.close();

    }

    @Override
    @SneakyThrows
    public void merge(Project task) {
        if (task == null) return;
        if (findOne(task.getId()) == null) {
            persist(task);
        }
        else {
            @NotNull final String query = "UPDATE `" + TABLE_NAME + "` SET `" +
                    NAME_FIELD + "` = ?, `" +
                    DESCRIPTION_FIELD + "` = ?, `" +
                    START_DATE_FIELD + "` = ?, `" +
                    FINISH_DATE_FIELD + "` = ?, `" +
                    CREATION_DATE_FIELD + "` = ?, `" +
                    STATUS_FIELD + "` = ?, `" +
                    USER_ID_FIELD + "` = ? WHERE `" + ID_FIELD + "` = ? `";
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(8, task.getId());
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new Date(task.getStartDate().getTime()));
            statement.setDate(4, new Date(task.getFinishDate().getTime()));
            statement.setDate(5, new Date(task.getCreationDate().getTime()));
            statement.setString(6, task.getStatus().name());
            statement.setString(7, task.getUserId());
            statement.executeUpdate();
            statement.close(); 
        }
    }

    @Override
    @SneakyThrows
    public boolean remove(Project task) {
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getId());
        final int i = statement.executeUpdate();
        statement.close();
        return i > 0;
    }

    @Override
    @SneakyThrows
    public void removeAll(Collection<Project> tasks) {
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` IN (?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        List<String> idList = new ArrayList<>();
        for (Project task : tasks) {
            idList.add(task.getId());
        }
        String[] ids = idList.toArray(new String[]{});
        statement.setArray(1, connection.createArrayOf("CHAR", ids));
        final int i = statement.executeUpdate();
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
        @NotNull final List<Project> projects = new ArrayList<>();
        while (resultSet.next()) {
            Project project = fetch(resultSet);
            projects.add(project);
        }
        return projects;
    }
}
