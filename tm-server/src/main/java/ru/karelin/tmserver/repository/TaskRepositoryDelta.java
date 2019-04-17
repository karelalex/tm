package ru.karelin.tmserver.repository;

import org.apache.deltaspike.data.api.*;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import java.util.List;

@Repository
public interface TaskRepositoryDelta extends FullEntityRepository<Task, String>, TaskRepository {
    @Override
    @Query("SELECT t from Task t where t.project.id = :pid")
    List<Task> findAllByProjectId(@QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.project.id = :pid and t.user.id = :uid")
    List<Task> findAllByProjectIdAndUserId(@QueryParam("pid") String projectId, @QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate")
    List<Task> findAllByUserIdAndProjectIdOrderByStartDate(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.startDate desc")
    List<Task> findAllByUserIdAndProjectIdOrderByStartDateDesc(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status")
    List<Task> findAllByUserIdAndProjectIdOrderByStatus(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.status desc")
    List<Task> findAllByUserIdAndProjectIdOrderByStatusDesc(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate")
    List<Task> findAllByUserIdAndProjectIdOrderByFinishDate(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.finishDate desc")
    List<Task> findAllByUserIdAndProjectIdOrderByFinishDateDesc(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate")
    List<Task> findAllByUserIdAndProjectIdOrderByCreationDate(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and t.project.id = :pid order by  t.creationDate desc")
    List<Task> findAllByUserIdAndProjectIdOrderByCreationDateDesc(@QueryParam("uid") String userId, @QueryParam("pid") String projectId);

    @Override
    @Query("select t from Task t where t.user.id = :uid and (t.description like :key or t.name like :key)")
    List<Task> findAllByUserIdAndKeyword(@QueryParam("uid") String userId, String key);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.startDate")
    List<Task> findAllByUserIdOrderByStartDate(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.startDate desc")
    List<Task> findAllByUserIdOrderByStartDateDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.status")
    List<Task> findAllByUserIdOrderByStatus(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.status desc")
    List<Task> findAllByUserIdOrderByStatusDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.finishDate")
    List<Task> findAllByUserIdOrderByFinishDate(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.finishDate desc")
    List<Task> findAllByUserIdOrderByFinishDateDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.creationDate")
    List<Task> findAllByUserIdOrderByCreationDate(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid order by t.creationDate desc")
    List<Task> findAllByUserIdOrderByCreationDateDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select t from Task t where t.user.id = :uid ")
    List<Task> findAllByUserId(@QueryParam("uid") String userId);

    @Override
    @Query(value = "select t from Task t where t.id = :tid and t.user.id = :uid", singleResult = SingleResultType.OPTIONAL)
    Task findOneByIdAndUserId(@QueryParam("tid") String id, @QueryParam("uid") String userId); //does not work


    @Query(value = "select t from Task t where t.id = :tid and t.user.id = :uid", singleResult = SingleResultType.OPTIONAL)
    Task obtainOneByIdAndUserId(@QueryParam("tid") String id, @QueryParam("uid") String userId);

    @Override
    Task findOne(String id); //does not work

    @Override
    @Modifying
    @Query("delete from Task")
    void removeAll();

}
