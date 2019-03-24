package ru.karelin.tm.repository;

import org.apache.ibatis.annotations.*;
import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.entity.Project;

import java.util.Collection;
import java.util.List;

public interface ProjectBatisRepo extends ProjectRepository {
    @Override
    @Select("select * from project where user_id = #{userId}")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id")
    })
    List<Project> findAllByUserId(@Param("userId") String userId);

    @Override
    @Select("select * from project where user_id = #{userId} and id = #{id}")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id")
    })
    Project findOneByIdAndUserId(@Param("id") String id, @Param("userId") String userId);

    @Override
    @Select("select * from project")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id")
    })
    List<Project> findAll();

    @Override
    @Select("select * from project where id = #{id} limit 1")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id")
    })
    Project findOne(String id);

    @Override
    @Insert("insert into `project` (`id`, `name`, `description`, `date_begin`, `date_end`, `user_id`) " +
            "values (#{id}, #{name}, #{description}, #{startDate}, #{finishDate}, #{userId})")
    void persist(Project project);

    @Override
    @Update("Update project set name = #{name}, description = #{description},  `date_begin` = #{startDate},  `date_end` = #{finishDate}, `user_id` = #{userId} where id = #{id}")
    void merge(Project project);

    @Override
    @Delete("Delete from `project` where id = #{id}")
    boolean remove(Project project);

    @Override
    @Delete("<script>" +
            "delete from project where id in " +
            "<foreach item='item' index = 'index' collection='projects' open='(' separator=',' close = ')'>" +
            "#{item.id}" +
            "</foreach>" +
            "</script>")
    void removeAll(@Param("projects") Collection<Project> projects);
}
