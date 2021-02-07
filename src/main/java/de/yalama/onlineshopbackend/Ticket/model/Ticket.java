package de.yalama.onlineshopbackend.Ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Message.model.ticketMessage.TicketMessage;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Ticket extends BaseEntity {

    private String title;

    private Date opened;

    private boolean isOpen;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    @NotNull
    private User ticketOpener;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    @NotNull
    private Purchase purchaseOfTicket;

    @OneToMany(mappedBy = "ticketOfMessage", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TicketMessage> messagesOfTicket;
}
