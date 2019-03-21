package ru.karelin.tm.api.repository;

import java.util.List;

public interface SecuredEntityRepository<T> extends Repository <T> {
    List<T> findAllByUserId(String userId);

    T findOneByIdAndUserId(String id, String userId);
}
