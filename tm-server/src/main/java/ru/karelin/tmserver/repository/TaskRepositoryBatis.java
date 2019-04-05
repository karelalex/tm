package ru.karelin.tmserver.repository;

import org.apache.ibatis.annotations.*;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import java.util.Collection;
import java.util.List;

public interface TaskRepositoryBatis extends TaskRepository {
    String TABLE_NAME = "task";
    String ID_FIELD = "id";
    String NAME_FIELD = "name";
    String DESCRIPTION_FIELD = "description";
    String CREATION_DATE_FIELD = "creation_date";
    String START_DATE_FIELD = "starting_date";
    String FINISH_DATE_FIELD = "finish_date";
    String STATUS_FIELD = "status";
    String USER_ID_FIELD = "user_id";
    String PROJECT_ID_FIELD = "project_id";

    @Override
    @Select("<script>" +
            "<bind name=\"pattern\" value=\"'%' + key + '%'\" />" +
            "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} AND (`" + NAME_FIELD + "` LIKE #{pattern} OR `" + DESCRIPTION_FIELD + "` LIKE #{pattern})" +
            "</script>")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndKeyword(@Param("uid") String userId, @Param("key") String key);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllOrderByStartDate();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllOrderByStartDateDesc();

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "`")
    List<Task> findAllOrderByStatus();

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "` DESC")
    List<Task> findAllOrderByStatusDesc();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllOrderByFinishDate();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllOrderByFinishDateDesc();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllOrderByCreationDate();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllOrderByCreationDateDesc();

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + START_DATE_FIELD + "`")
    List<Task> findAllByUserIdOrderByStartDate(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + START_DATE_FIELD + "` DESC")
    List<Task> findAllByUserIdOrderByStartDateDesc(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + STATUS_FIELD + "`")
    List<Task> findAllByUserIdOrderByStatus(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + STATUS_FIELD + "` DESC")
    List<Task> findAllByUserIdOrderByStatusDesc(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + FINISH_DATE_FIELD + "`")
    List<Task> findAllByUserIdOrderByFinishDate(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + FINISH_DATE_FIELD + "` DESC")
    List<Task> findAllByUserIdOrderByFinishDateDesc(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + CREATION_DATE_FIELD + "`")
    List<Task> findAllByUserIdOrderByCreationDate(@Param("uid") String userId);

    @Override
    @ResultMap("TaskMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + CREATION_DATE_FIELD + "`")
    List<Task> findAllByUserIdOrderByCreationDateDesc(@Param("uid") String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid}")
    @ResultMap("TaskMap")
    List<Task> findAllByUserId(@Param("uid") String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} AND `" + ID_FIELD + "` = #{id}")
    @ResultMap("TaskMap")
    Task findOneByIdAndUserId(@Param("id") String id, @Param("uid") String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "`")
    @ResultMap("TaskMap")
    List<Task> findAll();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = #{id}")
    @ResultMap("TaskMap")
    Task findOne(@Param("id") String id);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid}")
    @ResultMap("TaskMap")
    List<Task> findAllByProjectId(@Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid}")
    @ResultMap("TaskMap")
    List<Task> findAllByProjectIdAndUserId(@Param("uid") String projectId, @Param("pid") String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + START_DATE_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByStartDate(@Param("uid") String userId,@Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + START_DATE_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + STATUS_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByStatus(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + STATUS_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(@Param("uid") String userId,@Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + FINISH_DATE_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + FINISH_DATE_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(@Param("uid") String userId,@Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + CREATION_DATE_FIELD + "`")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + PROJECT_ID_FIELD + "` = #{pid} AND `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + CREATION_DATE_FIELD + "` DESC")
    @ResultMap("TaskMap")
    List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Insert("INSERT INTO `" + TABLE_NAME + "` " +
            "(`" + ID_FIELD + "`, `" +
            NAME_FIELD + "`, `" +
            DESCRIPTION_FIELD + "`, `" +
            START_DATE_FIELD + "`, `" +
            FINISH_DATE_FIELD + "`, `" +
            CREATION_DATE_FIELD + "`, `" +
            STATUS_FIELD + "`, `" +
            USER_ID_FIELD + "`, `" +
            PROJECT_ID_FIELD  + "`) VALUES (#{id}, #{name}, #{description}, #{startDate}, #{finishDate}, #{creationDate}, #{status}, #{userId}, #{projectId)")
    void persist(Task task);

    @Override
    @Update("UPDATE `" + TABLE_NAME + "` SET `" +
            NAME_FIELD + "` = #{name}, `" +
            DESCRIPTION_FIELD + "` = #{description}, `" +
            START_DATE_FIELD + "` = #{startDate}, `" +
            FINISH_DATE_FIELD + "` = #{finishDate}, `" +
            CREATION_DATE_FIELD + "` = #{creationDate}, `" +
            STATUS_FIELD + "` = #{status}, `" +
            USER_ID_FIELD + "` = #{userId}, `" +
            PROJECT_ID_FIELD + "` = #{projectId} WHERE `" + ID_FIELD + "` = #{id} ")
    void merge(Task task);

    @Override
    @Delete("Delete from `Task` where id = #{id}")
    boolean remove(Task Task);

    @Override
    @Delete("<script>" +
            "DELETE FROM `project` WHERE   `id` IN " +
            "<foreach item='item' index = 'index' collection='tasks' open='(' separator=',' close = ')'>" +
            "#{item.id}" +
            "</foreach>" +
            "</script>")
    void removeAllInList(Collection<Task> tasks);

    @Override
    @Delete("DELETE from `" + TABLE_NAME + "`")
    void removeAll();
}
