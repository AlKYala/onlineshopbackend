package de.yalama.onlineshopbackend.Advertisement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Product.model.Product;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.Pictures.model.Picture;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Advertisement extends BaseEntity {

    @NotNull
    private String title;

    private String description;

    private TypeOfAd typeOfAd;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    @NotNull
    private Product product;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    @NotNull
    private User seller;

    @OneToMany(mappedBy = "advertisementOfPurchase", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Purchase> purchasesOfAdvertisement;

    @OneToMany(mappedBy = "advertisementOfImage", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Picture> picturesOfAdvertisement;
}
