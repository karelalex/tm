package ru.karelin.tm.repository;

import ru.karelin.tm.entity.AbstractSecuredEntity;

import java.util.ArrayList;
import java.util.List;

public class AbstractSecuredEntityRepository<T extends AbstractSecuredEntity> extends AbstractRepository<T> implements SecuredEntityRepository<T> {
    @Override
    public List<T> findAllByUserId(String userId) {

        List<T> list = new ArrayList<>();
        for (T t: items.values()) {
            if(t.getUserId().equals(userId)) list.add(t);
        }
        return list;
    }

    @Override
    public T findOneByIdAndUserId(String id, String userId) {
        T t = findOne(id);
        if (t.getUserId().equals(userId)) {
            return t;
        }
        return null;
    }
}
