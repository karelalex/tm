package ru.karelin.tm.entity;

import java.util.UUID;

public class AbstractEntity {
    private String id= UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
