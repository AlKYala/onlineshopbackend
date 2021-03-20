package de.yalama.onlineshopbackend.PaymentMethod.service;

import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.PaymentInformation.service.PaymentInformationService;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.PaymentMethod.repository.PaymentMethodRepository;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.User.service.UserService;
import de.yalama.onlineshopbackend.shared.service.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodServiceImpl extends PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;
    private PaymentInformationService paymentInformationService;
    private UserRepository userRepository;
    private Validator<PaymentMethod, PaymentMethodRepository> validator;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository,
                                    PaymentInformationService paymentInformationService,
                                    UserRepository userRepository) {
        this.paymentInformationService = paymentInformationService;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
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

    @Override
    public List<PaymentMethod> findPaymentMethodsByUserId(Long userId) {
        List<PaymentMethod> paymentMethodsOfUser = new ArrayList<>();
        User userOfId = this.userRepository.findById(userId).get();
        for(PaymentInformation paymentInformation : userOfId.getPaymentInformation()) {
            paymentMethodsOfUser.add(paymentInformation.getPaymentMethod());
        }
        return paymentMethodsOfUser;
    }
}