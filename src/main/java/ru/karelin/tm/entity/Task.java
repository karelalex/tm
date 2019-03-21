package ru.karelin.tm.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Task extends AbstractSecuredEntity {

    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;
    private String projectID;
}
