package ru.karelin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tm.enumeration.RoleType;

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {
    private String login;
    private String passwordHash;
    private String userName;
    private RoleType role;

}
