package de.yalama.onlineshopbackend.Message.model;

import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class Message extends BaseEntity {
    @NotNull
    private String content;

    private Date dateSent;
}
