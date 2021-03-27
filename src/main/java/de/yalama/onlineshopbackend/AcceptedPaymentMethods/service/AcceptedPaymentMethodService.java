package de.yalama.onlineshopbackend.AcceptedPaymentMethods.service;

import de.yalama.onlineshopbackend.AcceptedPaymentMethods.model.AcceptedPaymentMethod;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.shared.service.BaseService;

import java.util.List;

public abstract class AcceptedPaymentMethodService implements BaseService<AcceptedPaymentMethod> {
    public abstract List<PaymentMethod> findPaymentMethodsBySellerId(Long userId);
    public abstract Long deleteByInstance(AcceptedPaymentMethod acceptedPaymentMethod);
    public abstract AcceptedPaymentMethod createOrUpdateInstance(AcceptedPaymentMethod acceptedPaymentMethod);
}
