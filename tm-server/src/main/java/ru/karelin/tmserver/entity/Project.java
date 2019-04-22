package ru.karelin.tmserver.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project extends AbstractSortableEntity{
    private String name;
    private String description;
    @OneToMany (mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Task> tasks;
}
