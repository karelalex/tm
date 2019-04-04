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
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid} AND (`" + NAME_FIELD + "` LIKE ? OR `" + DESCRIPTION_FIELD + "` LIKE #{key})")
   // @ResultMap("ru.karelin.mappers.ProjectMapper.ProjectResult")
    List<Project> findAllByUserIdAndKeyword(@Param("uid") String userId, @Param("key") String key);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` ORDER BY `" + START_DATE_FIELD + "`")
    //@ResultMap("ru.karelin.mappers.ProjectMapper.ProjectResult")
    List<Project> findAllOrderByStartDate();

    @Override
    List<Project> findAllOrderByStartDateDesc();

    @Override
    List<Project> findAllOrderByStatus();

    @Override
    List<Project> findAllOrderByStatusDesc();

    @Override
    List<Project> findAllOrderByFinishDate();

    @Override
    List<Project> findAllOrderByFinishDateDesc();

    @Override
    List<Project> findAllOrderByCreationDate();

    @Override
    List<Project> findAllOrderByCreationDateDesc();

    @Override
    List<Project> findAllByUserIdOrderByStartDate(String userId);

    @Override
    List<Project> findAllByUserIdOrderByStartDateDesc(String userId);

    @Override
    List<Project> findAllByUserIdOrderByStatus(String userId);

    @Override
    List<Project> findAllByUserIdOrderByStatusDesc(String userId);

    @Override
    List<Project> findAllByUserIdOrderByFinishDate(String userId);

    @Override
    List<Project> findAllByUserIdOrderByFinishDateDesc(String userId);

    @Override
    List<Project> findAllByUserIdOrderByCreationDate(String userId);

    @Override
    List<Project> findAllByUserIdOrderByCreationDateDesc(String userId);

    @Override
    @Select("SELECT * FROM `" + TABLE_NAME + "` WHERE `" + USER_ID_FIELD + "` = #{uid}")
    /*@Results({
            @Result(property = "startDate", column = "starting_date"),
            @Result(property = "finishDate", column = "finish_date"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "creationDate", column = "creation_date")
    })*/
    @ResultMap("ProjectMap")
    List<Project> findAllByUserId(@Param("uid") String userId);

    @Override
    Project findOneByIdAndUserId(String id, String userId);

    @Override
    List<Project> findAll();

    @Override
    Project findOne(String id);

    @Override
    void persist(Project project);

    @Override
    void merge(Project project);

    @Override
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
