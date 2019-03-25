package ru.karelin.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractSortableEntity {
    private String name;
    private String description;

}
