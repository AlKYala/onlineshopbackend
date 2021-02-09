package de.yalama.onlineshopbackend.Advertisement.repository;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
