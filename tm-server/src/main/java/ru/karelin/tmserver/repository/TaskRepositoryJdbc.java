package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskRepositoryJdbc implements TaskRepository {

    private Connection connection;
    private static final String TABLE_NAME = "task";
    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String CREATION_DATE_FIELD = "creation_date";
    private static final String START_DATE_FIELD = "starting_date";
    private static final String FINISH_DATE_FIELD = "finish_date";
    private static final String STATUS_FIELD = "status";
    private static final String USER_ID_FIELD = "user_id";
    private static final String PROJECT_ID_FIELD = "project_id";

    public TaskRepositoryJdbc(Connection connection) {
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
    public List<Task> findAllByProjectId(String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDate(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByStatus(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(String userId, String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ? AND `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        statement.setString(2, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdAndKeyword(String userId, String key) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? AND (`" + NAME_FIELD + "` LIKE ? OR `" + DESCRIPTION_FIELD + "` LIKE ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, "%" + key + "%");
        statement.setString(3, "%" + key + "%");
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByStartDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByStartDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByStatus() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByStatusDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByFinishDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByFinishDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByCreationDate() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllOrderByCreationDateDesc() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "` DESC";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByStartDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByStartDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + START_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByStatus(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByStatusDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + STATUS_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByFinishDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByFinishDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + FINISH_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByCreationDate(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + CREATION_DATE_FIELD + "`";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
         final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserIdOrderByCreationDateDesc(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? ORDER BY `" + CREATION_DATE_FIELD + "` DESC";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
         final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public List<Task> findAllByUserId(String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
         final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public Task findOneByIdAndUserId(String id, String userId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = ? AND `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        @Nullable Task task = null;
        if (resultSet.next()) {
            task = fetch(resultSet);
        }
        return task;
    }

    @Override
    @SneakyThrows
    public List<Task> findAll() {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "`";
        @NotNull final Statement statement = connection.createStatement();
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
         final List<Task> tasks = formList(resultSet);
        statement.close();
        return tasks;
    }

    @Override
    @SneakyThrows
    public Task findOne(String id) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        @Nullable Task task = null;
        if (resultSet.next()) {
            task = fetch(resultSet);
        }
        statement.close();
        return task;
    }

    @Override
    @SneakyThrows
    public void persist(Task task) {
        if (task == null) return;
        @NotNull final String query = "INSERT INTO `" + TABLE_NAME + "` " +
                "(`" + ID_FIELD + "`, `" +
                NAME_FIELD + "`, `" +
                DESCRIPTION_FIELD + "`, `" +
                START_DATE_FIELD + "`, `" +
                FINISH_DATE_FIELD + "`, `" +
                CREATION_DATE_FIELD + "`, `" +
                STATUS_FIELD + "`, `" +
                USER_ID_FIELD + "`, `" +
                PROJECT_ID_FIELD + "`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getId());
        statement.setString(2, task.getName());
        statement.setString(3, task.getDescription());
        statement.setDate(4, new Date(task.getStartDate().getTime()));
        statement.setDate(5, new Date(task.getFinishDate().getTime()));
        statement.setDate(6, new Date(task.getCreationDate().getTime()));
        statement.setString(7, task.getStatus().name());
        statement.setString(8, task.getUserId());
        statement.setString(9, task.getProjectID());
        statement.executeUpdate();
        statement.close();

    }

    @Override
    @SneakyThrows
    public void merge(Task task) {
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
                    USER_ID_FIELD + "` = ?, `" +
                    PROJECT_ID_FIELD + "` = ? WHERE `" + ID_FIELD + "` = ? `";
            @NotNull final PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(9, task.getId());
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new Date(task.getStartDate().getTime()));
            statement.setDate(4, new Date(task.getFinishDate().getTime()));
            statement.setDate(5, new Date(task.getCreationDate().getTime()));
            statement.setString(6, task.getStatus().name());
            statement.setString(7, task.getUserId());
            statement.setString(8, task.getProjectID());
            statement.executeUpdate();
            statement.close();
        }
    }

    @Override
    @SneakyThrows
    public boolean remove(Task task) {
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getId());
        final int i = statement.executeUpdate();
        statement.close();
        return i > 0;
    }

    @Override
    @SneakyThrows
    public void removeAll(Collection<Task> tasks) {
        StringBuilder insertions = new StringBuilder();
        for (Task task : tasks) {
            insertions.append("'").append(task.getId()).append("'").append(", ");
        }
        @NotNull final String query = "DELETE FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` IN (" + insertions.substring(0, insertions.length()-2) + ")";
        System.out.println(query);
        @NotNull final Statement statement = connection.createStatement();
        final int i = statement.executeUpdate(query);
        statement.close();
    }

    @SneakyThrows
    private Task fetch(ResultSet resultSet) {
        if (resultSet == null) return null;
        Task task = new Task();
        task.setId(resultSet.getString(ID_FIELD));
        task.setName(resultSet.getString(NAME_FIELD));
        task.setDescription(resultSet.getString(DESCRIPTION_FIELD));
        task.setProjectID(resultSet.getString(PROJECT_ID_FIELD));
        task.setStartDate(resultSet.getDate(START_DATE_FIELD));
        task.setFinishDate(resultSet.getDate(FINISH_DATE_FIELD));
        task.setCreationDate(resultSet.getDate(CREATION_DATE_FIELD));
        task.setStatus(Status.valueOf(resultSet.getString(STATUS_FIELD)));
        task.setUserId(resultSet.getString(USER_ID_FIELD));
        return task;
    }

    @SneakyThrows
    private List<Task> formList(ResultSet resultSet) {
        if (resultSet==null) return Collections.emptyList();
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            Task task = fetch(resultSet);
            tasks.add(task);
        }
        return tasks;
    }
}
