package ru.karelin.tm;

public enum RoleType {
    ADMIN("administrator"), ORDINARY_USER("user");

    private String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
