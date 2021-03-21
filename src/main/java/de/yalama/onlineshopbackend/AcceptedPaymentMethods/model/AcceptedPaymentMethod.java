package de.yalama.onlineshopbackend.AcceptedPaymentMethods.model;

import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class AcceptedPaymentMethod extends BaseEntity {

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @NotNull
    @JoinColumn
    private User seller;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @NotNull
    @JoinColumn
    private PaymentMethod paymentMethod;
}
