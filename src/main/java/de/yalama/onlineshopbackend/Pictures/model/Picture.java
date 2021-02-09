package de.yalama.onlineshopbackend.Pictures.model;

import com.sun.istack.NotNull;
import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
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
public class Picture extends BaseEntity {

    @NotNull
    private String pictureUrl;

    @ManyToOne
    @JoinColumn
    @EqualsAndHashCode.Exclude
    private Advertisement advertisementOfImage;
}
