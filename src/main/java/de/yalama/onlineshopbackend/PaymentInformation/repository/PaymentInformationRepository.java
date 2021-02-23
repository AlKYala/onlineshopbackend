package de.yalama.onlineshopbackend.PaymentInformation.repository;

import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInformationRepository extends JpaRepository<PaymentInformation, Long> {
}
