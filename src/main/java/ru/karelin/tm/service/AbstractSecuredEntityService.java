package ru.karelin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.service.SecuredEntityService;
import ru.karelin.tm.entity.AbstractEntity;
import ru.karelin.tm.api.repository.SecuredEntityRepository;

import java.util.List;

public class AbstractSecuredEntityService<T extends AbstractEntity> implements SecuredEntityService<T>
{
    @NotNull protected final SecuredEntityRepository<T> entityRepository;
    public AbstractSecuredEntityService(@NotNull final SecuredEntityRepository<T> repo) {
        entityRepository = repo;
    }

    @Override
    public List<T> getList(final String userId) {
        return entityRepository.findAllByUserId(userId);
    }

    @Override
    public void remove(final String userId, final String id) {
        @Nullable final T itemToRemove = entityRepository.findOneByIdAndUserId(id, userId);
        if(itemToRemove!=null) entityRepository.remove(itemToRemove);
    }

    @Override
    public boolean checkID(final String userId, final String id) {
        @Nullable T t = entityRepository.findOneByIdAndUserId(id, userId);
        return t!=null;
    }

    @Override
    public T getOne(final String userId, final String id) {
        return entityRepository.findOneByIdAndUserId(id, userId);
    }
}
