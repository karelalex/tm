package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Task extends AbstractSortableEntity  {
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn
    private String Project;
}
