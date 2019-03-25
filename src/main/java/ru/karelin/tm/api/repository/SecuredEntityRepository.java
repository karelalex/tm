package ru.karelin.tm.api.repository;

import ru.karelin.tm.entity.AbstractSecuredEntity;

import java.util.List;

public interface SecuredEntityRepository<T extends AbstractSecuredEntity> extends Repository <T> {
    List<T> findAllByUserId(String userId);

    T findOneByIdAndUserId(String id, String userId);
}
