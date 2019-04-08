package ru.karelin.tmserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.karelin.tmserver.entity.Session;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SessionDto extends AbstractSecuredDto {

    private static final long serialVersionUID = 1891419850323644365L;
    private String signature;
    private Date creationDate;

    public SessionDto(String id, String userId, String signature, Date creationDate) {
        super(id, userId);
        this.signature = signature;
        this.creationDate = creationDate;
    }

    public SessionDto(Session session) {
        this(session.getId(), session.getUser().getId(), session.getSignature(), session.getCreationDate());
    }
}
