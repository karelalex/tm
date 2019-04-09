package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.karelin.tmserver.enumeration.Status;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractSortableEntity extends AbstractSecuredEntity  {


    @Temporal(value = TemporalType.DATE)
    private Date startDate;
    @Temporal(value = TemporalType.DATE)
    private Date finishDate;
    @CreationTimestamp
    @Temporal(value = TemporalType.DATE)
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}
