package ru.karelin.tm.entity;

public class AbstractSecuredEntity extends AbstractEntity {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
