package de.yalama.onlineshopbackend.User.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.CartItem.model.CartItem;
import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.Rating.model.Rating;
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

    private double rating;

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
    private Set<Advertisement> salesOfUser;

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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private Set<PaymentInformation> paymentInformation;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    private Set<CartItem> itemsInCartItem;
}
