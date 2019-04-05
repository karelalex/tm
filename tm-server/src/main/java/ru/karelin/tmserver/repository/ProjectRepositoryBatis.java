package ru.karelin.tmserver.repository;

import org.apache.ibatis.annotations.*;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.entity.Project;

import java.util.Collection;
import java.util.List;

public interface ProjectRepositoryBatis extends ProjectRepository {
    String TABLE_NAME = "project";
    String ID_FIELD = "id";
    String NAME_FIELD = "name";
    String DESCRIPTION_FIELD = "description";
    String CREATION_DATE_FIELD = "creation_date";
    String START_DATE_FIELD = "starting_date";
    String FINISH_DATE_FIELD = "finish_date";
    String STATUS_FIELD = "status";
    String USER_ID_FIELD = "user_id";

    @Override
    @Select("<script>" +
            "<bind name=\"pattern\" value=\"'%' + key + '%'\" />" +
            "SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} AND (`" + NAME_FIELD + "` LIKE #{pattern} OR `" + DESCRIPTION_FIELD + "` LIKE #{pattern})" +
            "</script>")
    @ResultMap("ProjectMap")
    List<Project> findAllByUserIdAndKeyword(@Param("uid") String userId, @Param("key") String key);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "`")
    @ResultMap("ProjectMap")
    List<Project> findAllOrderByStartDate();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "` DESC")
    @ResultMap("ProjectMap")
    List<Project> findAllOrderByStartDateDesc();

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "`")
    List<Project> findAllOrderByStatus();

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + STATUS_FIELD + "` DESC")
    List<Project> findAllOrderByStatusDesc();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "`")
    @ResultMap("ProjectMap")
    List<Project> findAllOrderByFinishDate();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + FINISH_DATE_FIELD + "` DESC")
    @ResultMap("ProjectMap")
    List<Project> findAllOrderByFinishDateDesc();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "`")
    @ResultMap("ProjectMap")
    List<Project> findAllOrderByCreationDate();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + CREATION_DATE_FIELD + "` DESC")
    @ResultMap("ProjectMap")
    List<Project> findAllOrderByCreationDateDesc();

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + START_DATE_FIELD + "`")
    List<Project> findAllByUserIdOrderByStartDate(@Param("uid") String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + START_DATE_FIELD + "` DESC")
    List<Project> findAllByUserIdOrderByStartDateDesc(@Param("uid")String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + STATUS_FIELD + "`")
    List<Project> findAllByUserIdOrderByStatus(@Param("uid")String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + STATUS_FIELD + "` DESC")
    List<Project> findAllByUserIdOrderByStatusDesc(@Param("uid")String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + FINISH_DATE_FIELD + "`")
    List<Project> findAllByUserIdOrderByFinishDate(@Param("uid")String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + FINISH_DATE_FIELD + "` DESC")
    List<Project> findAllByUserIdOrderByFinishDateDesc(@Param("uid")String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + CREATION_DATE_FIELD + "`")
    List<Project> findAllByUserIdOrderByCreationDate(@Param("uid")String userId);

    @Override
    @ResultMap("ProjectMap")
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} ORDER BY `" + CREATION_DATE_FIELD + "`")
    List<Project> findAllByUserIdOrderByCreationDateDesc(@Param("uid")String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid}")
    @ResultMap("ProjectMap")
    List<Project> findAllByUserId(@Param("uid") String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} AND `" + ID_FIELD + "` = #{id}")
    @ResultMap("ProjectMap")
    Project findOneByIdAndUserId(@Param("id") String id, @Param("uid") String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "`")
    @ResultMap("ProjectMap")
    List<Project> findAll();

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + ID_FIELD + "` = #{id}")
    @ResultMap("ProjectMap")
    Project findOne(@Param("id") String id);

    @Override
    @Insert("INSERT INTO `" + TABLE_NAME + "` " +
            "(`" + ID_FIELD + "`, `" +
            NAME_FIELD + "`, `" +
            DESCRIPTION_FIELD + "`, `" +
            START_DATE_FIELD + "`, `" +
            FINISH_DATE_FIELD + "`, `" +
            CREATION_DATE_FIELD + "`, `" +
            STATUS_FIELD + "`, `" +
            USER_ID_FIELD + "`) VALUES (#{id}, #{name}, #{description}, #{startDate}, #{finishDate}, #{creationDate}, #{status}, #{userId})")
    void persist(Project project);

    @Override
    @Update("UPDATE `" + TABLE_NAME + "` SET `" +
            NAME_FIELD + "` = #{name}, `" +
            DESCRIPTION_FIELD + "` = #{description}, `" +
            START_DATE_FIELD + "` = #{startDate}, `" +
            FINISH_DATE_FIELD + "` = #{finishDate}, `" +
            CREATION_DATE_FIELD + "` = #{creationDate}, `" +
            STATUS_FIELD + "` = #{status}, `" +
            USER_ID_FIELD + "` = #{userId} WHERE `" + ID_FIELD + "` = #{id} ")
    void merge(Project project);

    @Override
    @Delete("Delete from `project` where id = #{id}")
    boolean remove(Project project);

    @Override
    @Delete("<script>" +
            "DELETE FROM `project` WHERE   `id` IN " +
            "<foreach item='item' index = 'index' collection='projects' open='(' separator=',' close = ')'>" +
            "#{item.id}" +
            "</foreach>" +
            "</script>")
    void removeAllInList(Collection<Project> projects);

    @Override
    @Delete("DELETE from `" + TABLE_NAME + "`")
    void removeAll();
}
