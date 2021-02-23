package de.yalama.onlineshopbackend.PaymentMethod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class PaymentMethod extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    @JsonIgnore
    private Set<PaymentInformation> paymentInformation;
}
