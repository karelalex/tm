package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Task;

import java.util.*;


public class TaskRepositoryImpl implements TaskRepository {
    private static Map<String, Task> tasks = new LinkedHashMap<>();

    @Override
    public List<Task> findAllByProjectId(String projectId){
       ArrayList<Task> taskArrayList = new ArrayList<>();
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
    public List<Task> findAllByUserId(String userId) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task t: tasks.values()) {
            if (t.getUserId().equals(userId)) list.add(t);
        }
        return list;
    }

    @Override
    public List<Task> findAllByProjectIdAndUserId(String projectId, String userId){
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task t: tasks.values()
        ) {
            if (t.getProjectID().equals(projectId) && t.getUserId().equals(userId)) taskArrayList.add(t);
        }
        return taskArrayList;
    }

    @Override
    public Task findOne(String id) {
        return tasks.get(id);
    }

    @Override
    public Task findOneByIdAndUserId(String id, String userId) {
        Task task = findOne(id);
        if(task.getUserId().equals(userId)) return task;
        return null;
    }

    @Override
    public void persist(Task task) {
        if(tasks.containsKey(task.getId())) throw new ObjectAlreadyExistsException("Task with ID="+task.getId()+" is already stored in database");
        tasks.put(task.getId(), task);
    }

    @Override
    public Task merge(Task task) {
        tasks.put(task.getId(), task);
        return tasks.get(task.getId());
    }

    @Override
    public boolean remove(Task task) {
        return tasks.remove(task.getId())!=null;
    }

    @Override
    public void removeAll(Collection<Task> taskCollection) {
        for (Task t : taskCollection) {
            tasks.remove(t.getId());
        }
    }
}
