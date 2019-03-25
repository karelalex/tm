package ru.karelin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.entity.Task;

import java.util.*;


public final class TaskRepositoryImpl extends AbstractRepository <Task> implements TaskRepository {


    @Override
    public List<Task> findAllByProjectId(final String projectId){
       @NotNull final ArrayList<Task> taskArrayList = new ArrayList<>();
       for (Task t: items.values()
            ) {
           if (t.getProjectID().equals(projectId)) taskArrayList.add(t);
       }
       return taskArrayList;
   }




    @Override
    public List<Task> findAllByUserId(final String userId) {
        @NotNull final ArrayList<Task> list = new ArrayList<>();
        for (Task t: items.values()) {
            if (t.getUserId().equals(userId)) list.add(t);
        }
        return list;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(final String projectId, final String userId){
        @NotNull final ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task t: items.values()
        ) {
            if (t.getProjectID().equals(projectId) && t.getUserId().equals(userId)) taskArrayList.add(t);
        }
        return taskArrayList;
    }


    @Override
    public Task findOneByIdAndUserId(final String id, final String userId) {
        @Nullable final Task task = findOne(id);
        if(task!=null && task.getUserId().equals(userId)) return task;
        return null;
    }



}
