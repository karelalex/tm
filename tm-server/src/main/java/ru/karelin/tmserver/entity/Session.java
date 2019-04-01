package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Session extends AbstractSecuredEntity {

    private static final long serialVersionUID = 8253722416556636231L;
    private String signature;
    private Date creationDate = new Date();

}
