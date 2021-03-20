package de.yalama.onlineshopbackend.PaymentMethod.service;

import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.shared.service.BaseService;

import java.util.List;

public abstract class PaymentMethodService implements BaseService<PaymentMethod> {
    public abstract List<PaymentMethod> findPaymentMethodsByUserId(Long userId);
}
