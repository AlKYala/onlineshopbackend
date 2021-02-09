package de.yalama.onlineshopbackend.User.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Message.model.privateMessage.PrivateMessage;
import de.yalama.onlineshopbackend.Message.model.purchaseMessage.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.model.ticketMessage.TicketMessage;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Setter
@Getter
public class User extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String username;

    private boolean isEmailConfirmed;

    private boolean isBanned;

    @OneToMany(mappedBy = "buyer")
    @JsonIgnore
    private Set<Purchase> purchasesOfUser;

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private Set<Purchase> salesOfUser;

    @OneToMany(mappedBy = "ticketOpener")
    @JsonIgnore
    private Set<Ticket> openedTickets;

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    private Set<TicketMessage> messagesInTickets;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private Set<PurchaseMessage> messagesInPurchases;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private Set<PrivateMessage> receivedPrivateMessages;

    @OneToMany(mappedBy= "sender")
    @JsonIgnore
    private Set<PrivateMessage> sentPrivateMessages;
}
