package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Task;

import java.util.*;


public final class TaskRepositoryImpl implements TaskRepository {
    private final static Map<String, Task> tasks = new LinkedHashMap<>();

    @Override
    public List<Task> findAllByProjectId(final String projectId){
       final ArrayList<Task> taskArrayList = new ArrayList<>();
       for (Task t: tasks.values()
            ) {
           if (t.getProjectID().equals(projectId)) taskArrayList.add(t);
       }
       return taskArrayList;
   }


    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> findAllByUserId(final String userId) {
        final ArrayList<Task> list = new ArrayList<>();
        for (Task t: tasks.values()) {
            if (t.getUserId().equals(userId)) list.add(t);
        }
        return list;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(final String projectId, final String userId){
        final ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task t: tasks.values()
        ) {
            if (t.getProjectID().equals(projectId) && t.getUserId().equals(userId)) taskArrayList.add(t);
        }
        return taskArrayList;
    }

    @Override
    public Task findOne(final String id) {
        return tasks.get(id);
    }

    @Override
    public Task findOneByIdAndUserId(final String id, final String userId) {
        final Task task = findOne(id);
        if(task.getUserId().equals(userId)) return task;
        return null;
    }

    @Override
    public void persist(final Task task) {
        if(tasks.containsKey(task.getId())) throw new ObjectAlreadyExistsException("Task with ID="+task.getId()+" is already stored in database");
        tasks.put(task.getId(), task);
    }

    @Override
    public Task merge(final Task task) {
        tasks.put(task.getId(), task);
        return tasks.get(task.getId());
    }

    @Override
    public boolean remove(final Task task) {
        return tasks.remove(task.getId())!=null;
    }

    @Override
    public void removeAll(final Collection<Task> taskCollection) {
        for (Task t : taskCollection) {
            tasks.remove(t.getId());
        }
    }
}
