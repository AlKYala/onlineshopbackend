package de.yalama.onlineshopbackend.AcceptedPaymentMethods.repository;

import de.yalama.onlineshopbackend.AcceptedPaymentMethods.model.AcceptedPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptedPaymentMethodRepository extends JpaRepository<AcceptedPaymentMethod, Long> {
}
