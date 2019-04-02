package ru.karelin.tmserver.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.enumeration.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
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
    public List<Task> findAllByProjectId(String projectId) {
        @NotNull final String query = "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = ?";
        @NotNull final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, projectId);
        @NotNull final ResultSet resultSet = statement.executeQuery();
        statement.close();
        return formList(resultSet);
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByProjectId(String projectId, String sortField, boolean isStraight) {
        return null;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId, String sortField, boolean isStraight) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdAndKeyword(String userId, String key) {
        return null;
    }

    @Override
    public List<Task> findAllOrderByStartDate() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByStartDateDesc() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByStatus() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByStatusDesc() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByFinishDate() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByFinishDateDesc() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByCreationDate() {
        return null;
    }

    @Override
    public List<Task> findAllOrderByCreationDateDesc() {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDate(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStartDateDesc(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatus(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByStatusDesc(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDate(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByFinishDateDesc(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDate(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserIdOrderByCreationDateDesc(String userId) {
        return null;
    }

    @Override
    public List<Task> findAllByUserId(String userId) {
        return null;
    }

    @Override
    public Task findOneByIdAndUserId(String id, String userId) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task findOne(String id) {
        return null;
    }

    @Override
    public void persist(Task task) {

    }

    @Override
    public void merge(Task task) {

    }

    @Override
    public boolean remove(Task task) {
        return false;
    }

    @Override
    public void removeAll(Collection<Task> tasks) {

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
    private List<Task> formList(ResultSet resultSet){
        @NotNull final List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            Task task = fetch(resultSet);
            tasks.add(task);
        }
        return tasks;
    }
}
