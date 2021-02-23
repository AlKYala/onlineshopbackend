package de.yalama.onlineshopbackend.PaymentMethod.service;

import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.PaymentInformation.repository.PaymentInformationRepository;
import de.yalama.onlineshopbackend.PaymentInformation.service.PaymentInformationService;
import de.yalama.onlineshopbackend.PaymentInformation.service.PaymentInformationServiceImpl;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.PaymentMethod.repository.PaymentMethodRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PaymentMethodServiceImpl extends PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;
    private PaymentInformationService paymentInformationService;
    private Validator<PaymentMethod, PaymentMethodRepository> validator;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository,
                                    PaymentInformationService paymentInformationService) {
        this.paymentInformationService = paymentInformationService;
        this.paymentMethodRepository = paymentMethodRepository;
        this.validator =
                new Validator<PaymentMethod, PaymentMethodRepository>("PaymentMethod", this.paymentMethodRepository);
    }

    @Override
    public PaymentMethod findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.paymentMethodRepository.findById(id).get();
    }

    @Override
    public List<PaymentMethod> findAll() {
        return this.paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod save(PaymentMethod instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.paymentMethodRepository.save(instance);
    }

    @Override
    public PaymentMethod update(Long id, PaymentMethod instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.paymentMethodRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        PaymentMethod paymentMethodToDelete = this.paymentMethodRepository.findById(id).get();
        this.deletePaymentInformationOfPaymentMethod(paymentMethodToDelete);
        this.paymentMethodRepository.deleteById(id);
        return id;
    }

    private void deletePaymentInformationOfPaymentMethod(PaymentMethod toDelete) {
        toDelete.getPaymentInformation().forEach(paymentInformation1 -> {
            this.paymentInformationService.deleteById(paymentInformation1.getId());
        });
    }
}
