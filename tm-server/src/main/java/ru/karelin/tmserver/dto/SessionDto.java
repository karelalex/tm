package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.entity.Session;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SessionDto extends AbstractSecuredDto {

    private static final long serialVersionUID = 1891419850323644365L;
    private String signature;
    private Date creationDate;


}
