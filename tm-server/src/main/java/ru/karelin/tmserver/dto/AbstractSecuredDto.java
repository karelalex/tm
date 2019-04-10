package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractSecuredDto implements Serializable {

    private static final long serialVersionUID = -5448089918940295571L;
    private String id;
    private String userId;


}
