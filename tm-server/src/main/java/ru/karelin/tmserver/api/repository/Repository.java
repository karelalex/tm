package ru.karelin.tmserver.api.repository;


import java.util.List;

public interface Repository<T> {
    List<T> findAll();

    T findOne(String id);

    T save(T t);

    void delete(T t);

    void deleteAll();

}
