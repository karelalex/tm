package ru.karelin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public abstract class AbstractSecuredEntity extends AbstractEntity implements Serializable {
    private String userId;

}
