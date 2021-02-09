package de.yalama.onlineshopbackend.Purchase.repository;

import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
