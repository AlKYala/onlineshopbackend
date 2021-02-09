package de.yalama.onlineshopbackend.pictures.repository;

import de.yalama.onlineshopbackend.pictures.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
