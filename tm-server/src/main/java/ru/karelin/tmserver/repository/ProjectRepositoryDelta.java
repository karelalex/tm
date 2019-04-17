package ru.karelin.tmserver.repository;

import org.apache.deltaspike.data.api.*;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepositoryDelta extends FullEntityRepository<Project, String>, ProjectRepository {
    @Override
    @Query("select p from Project p where p.user.id = :uid AND (p.name like :key or p.description like :key)")
    List<Project> findAllByUserIdAndKeyword(@QueryParam("uid") String userId, @QueryParam("key") String key);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.startDate")
    List<Project> findAllByUserIdOrderByStartDate(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.startDate desc")
    List<Project> findAllByUserIdOrderByStartDateDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.status")
    List<Project> findAllByUserIdOrderByStatus(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.status desc")
    List<Project> findAllByUserIdOrderByStatusDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.finishDate")
    List<Project> findAllByUserIdOrderByFinishDate(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.finishDate desc")
    List<Project> findAllByUserIdOrderByFinishDateDesc(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.creationDate")
    List<Project> findAllByUserIdOrderByCreationDate(@QueryParam("uid") String userId);

    @Override
    @Query("select p from Project p where p.user.id = :uid order by p.creationDate desc")
    List<Project> findAllByUserIdOrderByCreationDateDesc(@QueryParam("uid") String userId);

    @Override
    @Query("Select p from Project p where p.user.id = :uid")
    List<Project> findAllByUserId(@QueryParam("uid") String userId);

    @Override
    @Query(value = "select p from Project p where p.user.id = :uid and p.id = :pid", singleResult = SingleResultType.OPTIONAL)
    Project findOneByIdAndUserId(@QueryParam("pid") String id, @QueryParam("uid") String userId);


    @Query(value = "select p from Project p where p.user.id = :uid and p.id = :pid", singleResult = SingleResultType.OPTIONAL)
    Project obtainOneByIdAndUserId(@QueryParam("pid") String id, @QueryParam("uid") String userId);

    @Override
    Project findOne(String id); //does not work

    @Override
    @Modifying
    @Query("delete from Project")
    void removeAll();
}
