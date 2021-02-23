package de.yalama.onlineshopbackend.PaymentMethod.repository;

import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
