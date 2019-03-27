package ru.karelin.tmserver.repository;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.entity.Task;

import java.util.ArrayList;
import java.util.List;


public final class TaskRepositoryImpl extends AbstractSortableEntityRepository<Task> implements TaskRepository {

    @Override
    public List<Task> findAllByProjectId(final String projectId, final String sortField, boolean isStraight) {
        return sortItems(findAllByProjectId(projectId), sortField, isStraight);
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId, String sortField, boolean isStraight) {
        return sortItems(findAllByProjectIdAndUserId(projectId, userId), sortField, isStraight);
    }

    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        @NotNull final List<Task> taskArrayList = new ArrayList<>();
        for (Task t : items.values()
        ) {
            if (t.getProjectID().equals(projectId)) taskArrayList.add(t);
        }
        return taskArrayList;
    }


    @Override
    public List<Task> findAllByUserIdAndKeyword(String userId, String key) {
        List<Task> out = new ArrayList<>();
        List<Task> in = findAllByUserId(userId);
        for (Task t : in) {
            if(t.getName().contains(key) || (t.getDescription().contains(key))) out.add(t);
        }
        return out;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(final String projectId, final String userId) {
        @NotNull final ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task t : items.values()
        ) {
            if (t.getProjectID().equals(projectId) && t.getUserId().equals(userId)) taskArrayList.add(t);
        }
        return taskArrayList;
    }


}
