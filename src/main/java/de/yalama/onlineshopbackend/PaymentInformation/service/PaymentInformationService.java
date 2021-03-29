package de.yalama.onlineshopbackend.PaymentInformation.service;

import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.shared.service.BaseService;

import java.util.List;

public abstract class PaymentInformationService implements BaseService<PaymentInformation> {
    public abstract List<PaymentInformation> getPaymentInformationByUserId(Long id);
}
