package ru.karelin.tmclient.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractSortableEntity implements Serializable {
    private static final long serialVersionUID = 2247341224930637884L;
    private String name;
    private String description;

}
