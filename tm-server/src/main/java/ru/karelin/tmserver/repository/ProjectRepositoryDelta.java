package ru.karelin.tmserver.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepositoryDelta extends CrudRepository<Project, String>, ProjectRepository {
    @Override
    @Query("select p from Project p where p.user.id = :uid AND (p.name like :key or p.description like :key)")
    List<Project> findAllByUserIdAndKeyword(@Param("uid") String userId, @Param("key") String key);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.startDate")
    List<Project> findAllByUserIdOrderByStartDate(@Param("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.startDate desc")
    List<Project> findAllByUserIdOrderByStartDateDesc(@Param("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.status")
    List<Project> findAllByUserIdOrderByStatus(@Param("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.status desc")
    List<Project> findAllByUserIdOrderByStatusDesc(@Param("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.finishDate")
    List<Project> findAllByUserIdOrderByFinishDate(@Param("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.finishDate desc")
    List<Project> findAllByUserIdOrderByFinishDateDesc(@Param("uid") String userId);

    @Override
    List<Project> findAllByUserIdOrderByCreationDate(String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.creationDate desc")
    List<Project> findAllByUserIdOrderByCreationDateDesc(@Param("uid") String userId);

    @Override
    @Query("Select p from Project p where p.user.id = :uid")
    List<Project> findAllByUserId(@Param("uid") String userId);

    @Override
    @Query(value = "select p from Project p where p.user.id = :uid and p.id = :pid")
    Project findOneByIdAndUserId(@Param("pid") String id, @Param("uid") String userId);


    @Override
    @Query("select p from Project p where p.id = :id")
    Project findOne(@Param("id") String id);




}
