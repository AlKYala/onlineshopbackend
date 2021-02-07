package de.yalama.onlineshopbackend.Message.model.ticketMessage;

import de.yalama.onlineshopbackend.Message.model.Message;
import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.User.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class TicketMessage extends Message {

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private User writer;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private Ticket ticketOfMessage;
}
