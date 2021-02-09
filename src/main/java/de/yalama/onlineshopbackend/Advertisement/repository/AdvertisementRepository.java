package de.yalama.onlineshopbackend.Advertisement.repository;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
