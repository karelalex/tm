package ru.karelin.tm.service;

import java.util.List;

public interface SecuredEntityService<T> {

    List<T> getList(String userId);

    void remove(String userId, String ID);

    boolean checkID(String userId, String id);

    T getOne(String userId, String id);
}
