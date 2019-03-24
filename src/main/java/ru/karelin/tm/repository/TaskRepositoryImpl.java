package ru.karelin.tm.repository;

import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.entity.Task;

import java.util.*;


public final class TaskRepositoryImpl extends AbstractRepository <Task> implements TaskRepository {


    @Override
    public List<Task> findAllByProjectId(final String projectId){
       final ArrayList<Task> taskArrayList = new ArrayList<>();
       for (Task t: items.values()
            ) {
           if (t.getProjectId().equals(projectId)) taskArrayList.add(t);
       }
       return taskArrayList;
   }




    @Override
    public List<Task> findAllByUserId(final String userId) {
        final ArrayList<Task> list = new ArrayList<>();
        for (Task t: items.values()) {
            if (t.getUserId().equals(userId)) list.add(t);
        }
        return list;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(final String projectId, final String userId){
        final ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task t: items.values()
        ) {
            if (t.getProjectId().equals(projectId) && t.getUserId().equals(userId)) taskArrayList.add(t);
        }
        return taskArrayList;
    }


    @Override
    public Task findOneByIdAndUserId(final String id, final String userId) {
        final Task task = findOne(id);
        if(task.getUserId().equals(userId)) return task;
        return null;
    }



}
