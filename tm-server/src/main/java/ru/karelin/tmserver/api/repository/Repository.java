package ru.karelin.tmserver.api.repository;


import java.util.List;

public interface  Repository<T> {
    List<T> findAll();
    T findOne(String id);
    void persist(T t);
    void merge(T t);
    void remove(T t);
    void removeAll();

}
