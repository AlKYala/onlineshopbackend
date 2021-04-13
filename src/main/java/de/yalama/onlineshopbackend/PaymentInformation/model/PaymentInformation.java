package de.yalama.onlineshopbackend.PaymentInformation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.AcceptedPaymentMethods.model.AcceptedPaymentMethod;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
/**
 * A user (seller) gives information about the payment methods for his products
 * This entity holds information about his payment information
 * all his offers can be bought with one of his payment methods
 */
public class PaymentInformation extends BaseEntity {

    @NotNull
    private String address;

    private String additionalInformation1;

    private String additionalInformation2;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private User seller;

    @OneToMany(mappedBy = "paymentInformation")
    @JsonIgnore
    private Set<Purchase> purchases;

    @OneToOne
    @JoinColumn
    private AcceptedPaymentMethod acceptedPaymentMethod;
}
