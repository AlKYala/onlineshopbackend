package de.yalama.onlineshopbackend.Message.purchaseMessage.model;

import de.yalama.onlineshopbackend.Message.model.Message;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
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
public class PurchaseMessage extends Message {

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private Purchase purchaseOfMessage;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private User sender;
}
