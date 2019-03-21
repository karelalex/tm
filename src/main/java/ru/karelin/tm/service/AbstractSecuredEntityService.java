package ru.karelin.tm.service;

import ru.karelin.tm.entity.AbstractEntity;
import ru.karelin.tm.repository.Repository;
import ru.karelin.tm.repository.SecuredEntityRepository;

import java.util.List;

public class AbstractSecuredEntityService<T extends AbstractEntity> implements SecuredEntitiesService<T>
{
    protected SecuredEntityRepository<T> entityRepository;
    public AbstractSecuredEntityService(SecuredEntityRepository<T> repo) {
        entityRepository = repo;
    }

    @Override
    public List<T> getList(String userId) {
        return entityRepository.findAllByUserId(userId);
    }

    @Override
    public void remove(String userId, String id) {
        T itemToRemove = entityRepository.findOneByIdAndUserId(id, userId);
        if(itemToRemove!=null) entityRepository.remove(itemToRemove);
    }

    @Override
    public boolean checkID(String userId, String id) {
        entityRepository.findOneByIdAndUserId(id, userId);
        return false;
    }

    @Override
    public T getOne(String userId, String id) {
        return entityRepository.findOneByIdAndUserId(id, userId);
    }
}
