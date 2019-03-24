package ru.karelin.tm.repository;


import org.apache.ibatis.annotations.*;
import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.entity.Task;

import java.util.Collection;
import java.util.List;

public interface TaskBatisRepo extends TaskRepository {
    @Override
    @Select("select * from task where user_id = #{userId}")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id")
    })
    List<Task> findAllByUserId(@Param("userId") String userId);

    @Override
    @Select("select * from task where user_id = #{userId} and id = #{id}")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id")
    })
    Task findOneByIdAndUserId(@Param("id") String id, @Param("userId") String userId);

    @Override
    @Select("select * from task")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id")
    })
    List<Task> findAll();

    @Override
    @Select("select * from task where id = #{id} limit 1")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id")
    })
    Task findOne(String id);

    @Override
    @Insert("insert into `task` (`id`, `name`, `description`, `date_begin`, `date_end`, `project_id`, `user_id`) " +
            "values (#{id}, #{name}, #{description}, #{startDate}, #{finishDate}, #{projectId}, #{userId})")
    void persist(Task task);

    @Override
    @Update("Update task set name = #{name}, description = #{description},  `date_begin` = #{startDate},  `date_end` = #{finishDate}, project_id=#{project_id}, `user_id` = #{userId} where id = #{id}")
    void merge(Task task);

    @Override
    @Delete("Delete from `task` where id = #{id}")
    boolean remove(Task task);

    @Override
    @Delete("<script>" +
            "delete from task where id in " +
            "<foreach item='item' index = 'index' collection='tasks' open='(' separator=',' close = ')'>" +
            "#{item.id}" +
            "</foreach>" +
            "</script>")
    void removeAll(@Param("tasks") Collection<Task> tasks);

    @Override
    @Select("select * from task where project_id = #{pid}")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id")
    })
    List<Task> findAllByProjectId(@Param("pid") String projectId);

    @Override
    @Select("select * from task where user_id = #{userId} and project_id = #{pid}")
    @Results({
            @Result(property = "startDate", column = "date_begin"),
            @Result(property = "finishDate", column = "date_end"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "projectId", column = "project_id")
    })
    List<Task> findAllByProjectIdAndUserId(@Param("pid") String projectId, @Param("userId") String userId);
}
