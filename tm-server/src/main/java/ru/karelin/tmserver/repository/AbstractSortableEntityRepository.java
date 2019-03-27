package ru.karelin.tmserver.repository;

import ru.karelin.tmserver.api.repository.SortableEntityRepository;
import ru.karelin.tmserver.entity.AbstractSortableEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ru.karelin.tmserver.enumeration.Status;

public abstract class AbstractSortableEntityRepository<T extends AbstractSortableEntity> extends AbstractSecuredEntityRepository<T> implements SortableEntityRepository<T> {
    @Override
    public List<T> findAllByUserId(String userId, String sortField, boolean isStraight) {
        return sortItems(findAllByUserId(userId), sortField, isStraight);
    }

    @Override
    public List<T> findAll(String sortField, boolean isStraight) {
        return sortItems(findAll(), sortField, isStraight);
    }

    protected List<T> sortItems(List<T> initialList, String sortField, boolean isStraight) {
        Comparator<T> comp = null;
        switch (sortField) {
            case "fin":
                comp = new SortByFinishDate();
                break;
            case "start":
                comp = new SortByStartDate();
                break;
            case "create":
                comp = new SortByCreationDate();
                break;
            case "stat":
                comp = new SortByStatus();
                break;
        }
        if (comp != null) {
            if(isStraight)
                Collections.sort(initialList, comp);
            else Collections.sort(initialList, Collections.reverseOrder(comp));
        }
        return initialList;

    }


    class SortByFinishDate implements Comparator<T> {
        @Override
        public int compare(AbstractSortableEntity o1, AbstractSortableEntity o2) {
            return o1.getFinishDate().compareTo(o2.getFinishDate());
        }
    }

    class SortByStartDate implements Comparator<T> {
        @Override
        public int compare(AbstractSortableEntity o1, AbstractSortableEntity o2) {
            return o1.getStartDate().compareTo(o2.getStartDate());
        }
    }

    class SortByCreationDate implements Comparator<T> {
        @Override
        public int compare(AbstractSortableEntity o1, AbstractSortableEntity o2) {
            return o1.getCreationDate().compareTo(o2.getCreationDate());
        }
    }

    class SortByStatus implements Comparator<T>{
        @Override
        public int compare(T o1, T o2) {
            return o1.getStatus().compareTo(o2.getStatus());
        }
    }
}
