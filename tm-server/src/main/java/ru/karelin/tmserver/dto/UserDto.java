package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmserver.enumeration.RoleType;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = -6389288948701954564L;
    private String id;
    private String login;
    private String passwordHash;
    private String userName;
    private RoleType role;
}
