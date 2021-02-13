package de.yalama.onlineshopbackend.Rating.repository;

import de.yalama.onlineshopbackend.Rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
