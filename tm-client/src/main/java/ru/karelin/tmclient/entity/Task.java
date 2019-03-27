package ru.karelin.tmclient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class Task extends AbstractSortableEntity implements Serializable {

    private static final long serialVersionUID = 3636812355087287545L;
    private String name;
    private String description;
    private String projectID;
}
