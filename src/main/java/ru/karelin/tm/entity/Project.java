package ru.karelin.tm.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Project extends AbstractSecuredEntity {
    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;

}
