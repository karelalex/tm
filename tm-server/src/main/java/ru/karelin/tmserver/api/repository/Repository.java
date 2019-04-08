package ru.karelin.tmserver.api.repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public interface  Repository<T> {
    List<T> findAll(EntityManager em);
    T findOne(String id, EntityManager em);
    void persist(T t, EntityManager em);
    void merge(T t, EntityManager em);
    void remove(T t, EntityManager em);
    void removeAll(EntityManager em);

}
