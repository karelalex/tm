package ru.karelin.tm.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class AbstractEntity {
    private String id= UUID.randomUUID().toString();

}
