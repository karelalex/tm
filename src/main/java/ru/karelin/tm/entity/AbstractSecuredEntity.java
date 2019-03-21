package ru.karelin.tm.entity;

import lombok.Data;

@Data
public class AbstractSecuredEntity extends AbstractEntity {
    private String userId;

}
