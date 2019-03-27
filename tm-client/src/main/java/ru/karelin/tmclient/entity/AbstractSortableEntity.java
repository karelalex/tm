package ru.karelin.tmclient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmclient.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractSortableEntity extends AbstractSecuredEntity implements Serializable {

    private static final long serialVersionUID = -8597157050956055822L;
    private Date startDate;
    private Date finishDate;
    private Date creationDate = new Date();
    private Status status;
}
