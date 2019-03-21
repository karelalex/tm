package ru.karelin.tm.entity;

import lombok.Data;
import ru.karelin.tm.RoleType;

import java.util.Objects;
import java.util.UUID;

@Data
public class User extends AbstractEntity {
    private String login;
    private String passwordHash;
    private String userName;
    private RoleType role;

}
