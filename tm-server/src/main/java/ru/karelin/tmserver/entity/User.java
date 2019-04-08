package ru.karelin.tmserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmserver.enumeration.RoleType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class User extends AbstractEntity  {
    private String login;
    private String passwordHash;
    private String userName;
    private RoleType role;

}
