package ru.karelin.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Project extends AbstractSecuredEntity {
    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;

}
