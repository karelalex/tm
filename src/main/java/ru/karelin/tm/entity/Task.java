package ru.karelin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Task extends AbstractSecuredEntity {

    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;
    private String projectID;
}
