package ru.karelin.tmclient.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractSecuredEntity extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -4934536461890031112L;
    private String userId;

}
