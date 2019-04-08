package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto extends AbstractSortableDto {
    private static final long serialVersionUID = 6828350195497460877L;
    private String name;
    private String description;
}
