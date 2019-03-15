package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.Task;

import java.util.*;


public class TaskRepository implements Repository<Task> {
    private static Map<String, Task> tasks = new LinkedHashMap<>();

    public List<Task> findAllByProjectId (String projectId){
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
    public Task findOne(String id) {
        return tasks.get(id);
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
