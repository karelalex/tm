package ru.karelin.tm.enums;

public enum RoleType {
    ADMIN("administrator"), ORDINARY_USER("user");

    private String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
