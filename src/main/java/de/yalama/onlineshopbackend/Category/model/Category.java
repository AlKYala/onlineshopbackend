package de.yalama.onlineshopbackend.Category.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Marke.model.Marke;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Marke> productsOfCategory;
}
