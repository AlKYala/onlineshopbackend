package de.yalama.onlineshopbackend.Purchase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Message.model.purchaseMessage.PurchaseMessage;
import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import de.yalama.onlineshopbackend.shared.models.PaymentMethod;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Purchase extends BaseEntity {

    private Integer quantity;

    private double price;

    private PaymentMethod paymentMethod;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    @NotNull
    private Advertisement advertisementOfPurchase;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    @NotNull
    private User buyer;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    private User seller;

    @OneToMany(mappedBy = "purchaseOfMessage")
    @JsonIgnore
    private Set<PurchaseMessage> messagesInPurchase;

    @OneToMany(mappedBy = "purchaseOfTicket", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ticket> ticketsOfTicket;
}
