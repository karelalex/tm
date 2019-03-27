package ru.karelin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 2699580275687182107L;
    private String id= UUID.randomUUID().toString();

}
