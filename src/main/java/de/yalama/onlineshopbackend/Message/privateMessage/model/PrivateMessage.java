package de.yalama.onlineshopbackend.Message.privateMessage.model;

import de.yalama.onlineshopbackend.Message.model.Message;
import de.yalama.onlineshopbackend.User.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PrivateMessage extends Message {

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private User sender;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private User receiver;
}
