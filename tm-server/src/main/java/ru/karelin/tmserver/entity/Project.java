package ru.karelin.tmserver.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project extends AbstractSortableEntity{
    private String name;
    private String description;
    @OneToMany (mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Task> tasks;
}
