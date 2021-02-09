package de.yalama.onlineshopbackend.Message.purchaseMessage.repository;

import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseMessageRepository extends JpaRepository<PurchaseMessage, Long> {
}
