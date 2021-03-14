package de.yalama.onlineshopbackend.Advertisement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.CartItem.model.CartItem;
import de.yalama.onlineshopbackend.Marke.model.Marke;
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

    @NotNull
    private double price;

    /**
     * Note: In Frontend Ads are filtered by category by Advertisement::getMarke.getCategory()
     */
    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    @NotNull
    private Marke marke;

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

    @OneToMany(mappedBy="advertisement", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CartItem> instancesInCartItems;

    /** ONLY USED WHEN AD IS FEATURED ON THE HOMEPAGE  */
    private boolean featured;

    private String featuredTitle;

    private String featuredDescription;
    @NotNull
    private String featuredPictureUrl;
}
