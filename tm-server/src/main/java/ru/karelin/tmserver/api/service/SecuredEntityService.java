package ru.karelin.tmserver.api.service;

import java.util.List;

public interface SecuredEntityService<T>{

    List<T> getList(String userId);

    void remove(String userId, String id);

    boolean checkId(String userId, String id);

    T getOne(String userId, String id);
}
