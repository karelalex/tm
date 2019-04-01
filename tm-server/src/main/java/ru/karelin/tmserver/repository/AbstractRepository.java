package ru.karelin.tmserver.repository;

import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.Repository;
import ru.karelin.tmserver.entity.AbstractEntity;
import ru.karelin.tmserver.exception.ObjectAlreadyExistsException;

import java.util.*;

public class AbstractRepository<T extends AbstractEntity> implements Repository<T> {
    @NotNull protected Map<String, T> items = new LinkedHashMap<>();

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public T findOne(final String id) {
        return items.get(id);
    }

    @Override
    public void persist(final T t) {
        if (items.containsKey(t.getId())) throw new ObjectAlreadyExistsException(
                t.getClass().getSimpleName()+ " with id=" + t.getId() +" is already saved in database"
        );
        items.put(t.getId(), t);
    }

    @Override
    public void merge(final T t) {
        items.put(t.getId(), t);

    }

    @Override
    public boolean remove(final T t) {
        T removedItem = items.remove(t.getId());
        return !(removedItem == null);
    }

    @Override
    public void removeAll(final Collection<T> tCollection) {
        for (T t: tCollection
             ) {
            items.remove(t.getId());
        }
    }
}
