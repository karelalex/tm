package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TaskDto extends AbstractSortableDto {
    private static final long serialVersionUID = -2715868699303825001L;
    private String name;
    private String description;
    private String projectId;
}
