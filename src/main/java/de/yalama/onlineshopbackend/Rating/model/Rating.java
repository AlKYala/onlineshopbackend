package de.yalama.onlineshopbackend.Rating.model;

import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Rating extends BaseEntity {

    private String text;

    private Integer rating;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn
    private User user;
}
