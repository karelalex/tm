package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Session extends AbstractSecuredEntity {

    private String signature;
    @CreationTimestamp
    //@Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

}
