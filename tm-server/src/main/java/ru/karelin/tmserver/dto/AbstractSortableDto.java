package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmserver.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractSortableDto extends AbstractSecuredDto implements Serializable {

    private static final long serialVersionUID = 8970546767610282111L;
    private Date startDate;
    private Date finishDate;
    private Date creationDate;
    private Status status;


}
