package de.yalama.onlineshopbackend.Product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Category.model.Category;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Setter
@Getter
public class Product extends BaseEntity {

    @NotNull
    public String name;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Advertisement> advertisementsOfProduct;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    @NotNull
    private Category category;
}
