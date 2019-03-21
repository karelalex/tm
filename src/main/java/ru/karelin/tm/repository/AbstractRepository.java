package ru.karelin.tm.repository;

import ru.karelin.tm.ObjectAlreadyExistsException;
import ru.karelin.tm.entity.AbstractEntity;

import java.util.*;

public class AbstractRepository<T extends AbstractEntity> implements Repository<T>{
    protected Map<String, T> items = new LinkedHashMap<>();

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public T findOne(String id) {
        return items.get(id);
    }

    @Override
    public void persist(T t) {
        if (items.containsKey(t.getId())) throw new ObjectAlreadyExistsException(
                t.getClass().getSimpleName()+ " with id=" + t.getId() +" is already saved in database"
        );
        items.put(t.getId(), t);
    }

    @Override
    public T merge(T t) {
        items.put(t.getId(), t);
        return items.get(t.getId());
    }

    @Override
    public boolean remove(T t) {
        T removedItem = items.remove(t.getId());
        return !(removedItem == null);
    }

    @Override
    public void removeAll(Collection<T> tCollection) {
        for (T t: tCollection
             ) {
            items.remove(t.getId());
        }
    }
}
