package ru.karelin.tmserver.api.repository;

import ru.karelin.tmserver.entity.AbstractSecuredEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface SecuredEntityRepository<T extends AbstractSecuredEntity> extends Repository <T> {
    List<T> findAllByUserId(String userId, EntityManager em);

    T findOneByIdAndUserId(String id, String userId, EntityManager em);
}
