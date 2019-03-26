package ru.karelin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tm.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractSortableEntity extends AbstractSecuredEntity implements Serializable {
    private Date startDate;
    private Date finishDate;
    private Date creationDate = new Date();
    private Status status;
}
