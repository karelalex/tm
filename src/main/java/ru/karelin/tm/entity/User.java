package ru.karelin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tm.enumeration.RoleType;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class User extends AbstractEntity implements Serializable {
    private String login;
    private String passwordHash;
    private String userName;
    private RoleType role;

}
