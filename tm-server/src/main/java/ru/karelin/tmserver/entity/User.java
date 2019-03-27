package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmserver.enumeration.RoleType;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class User extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 5232078222342421203L;
    private String login;
    private String passwordHash;
    private String userName;
    private RoleType role;

}
