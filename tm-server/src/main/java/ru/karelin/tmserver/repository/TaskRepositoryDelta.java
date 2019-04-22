package ru.karelin.tmserver.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import java.util.List;

@Repository
public interface TaskRepositoryDelta extends CrudRepository<Task, String>, TaskRepository {
    @Override
    @Query("SELECT t from Task t where t.project.id = :pid")
    List<Task> findAllByProjectId(@Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.project.id = :pid and t.user.id = :uid")
    List<Task> findAllByProjectIdAndUserId(@Param("pid") String projectId, @Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate")
    List<Task> findAllByUserIdAndProjectIdOrderByStartDate(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate desc")
    List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status")
    List<Task> findAllByUserIdAndProjectIdOrderByStatus(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status desc")
    List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate")
    List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate desc")
    List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate")
    List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate desc")
    List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(@Param("uid") String userId, @Param("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and (t.description like :key or t.name like :key)")
    List<Task> findAllByUserIdAndKeyword(@Param("uid") String userId, @Param("key") String key);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.startDate")
    List<Task> findAllByUserIdOrderByStartDate(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.startDate desc")
    List<Task> findAllByUserIdOrderByStartDateDesc(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.status")
    List<Task> findAllByUserIdOrderByStatus(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.status desc")
    List<Task> findAllByUserIdOrderByStatusDesc(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.finishDate")
    List<Task> findAllByUserIdOrderByFinishDate(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.finishDate desc")
    List<Task> findAllByUserIdOrderByFinishDateDesc(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.creationDate")
    List<Task> findAllByUserIdOrderByCreationDate(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.creationDate desc")
    List<Task> findAllByUserIdOrderByCreationDateDesc(@Param("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid ")
    List<Task> findAllByUserId(@Param("uid") String userId);

    @Override
    Task findOneByIdAndUserId(String id, String userId);


    @Override
    @Query("select t from Task t where t.id = :id")
    Task findOne(@Param("id") String id);



}
