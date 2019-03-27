package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmserver.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractSortableEntity extends AbstractSecuredEntity implements Serializable {

    private static final long serialVersionUID = 6445791045664636396L;
    private Date startDate;
    private Date finishDate;
    private Date creationDate = new Date();
    private Status status;
}
