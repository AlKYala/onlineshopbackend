package de.yalama.onlineshopbackend.Pictures.repository;

import de.yalama.onlineshopbackend.Pictures.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
