package de.yalama.onlineshopbackend.PaymentInformation.service;

import de.yalama.onlineshopbackend.AcceptedPaymentMethods.model.AcceptedPaymentMethod;
import de.yalama.onlineshopbackend.AcceptedPaymentMethods.service.AcceptedPaymentMethodService;
import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.PaymentInformation.repository.PaymentInformationRepository;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.PaymentMethod.repository.PaymentMethodRepository;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentInformationServiceImpl extends PaymentInformationService {

    private PaymentInformationRepository paymentInformationRepository;
    private UserRepository userRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private Validator<PaymentInformation, PaymentInformationRepository> validator;
    private AcceptedPaymentMethodService acceptedPaymentMethodService;

    PaymentInformationServiceImpl(PaymentInformationRepository paymentInformationRepository,
                                  UserRepository userRepository,
                                  PaymentMethodRepository paymentMethodRepository,
                                  AcceptedPaymentMethodService  acceptedPaymentMethodService) {
        this.acceptedPaymentMethodService = acceptedPaymentMethodService;
        this.userRepository = userRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentInformationRepository = paymentInformationRepository;
        this.validator = new Validator<PaymentInformation, PaymentInformationRepository>
                ("PaymentInformation", this.paymentInformationRepository);
    }

    @Override
    public PaymentInformation findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.paymentInformationRepository.findById(id).get();
    }

    @Override
    public List<PaymentInformation> findAll() {
        return this.paymentInformationRepository.findAll();
    }



    @Override
    public PaymentInformation save(PaymentInformation instance) {
        /*this.validator.checkEntityNotExists(instance.getId());
        PaymentInformation instanceSaved = this.paymentInformationRepository.save(instance);
        this.createAccpetedPaymentMethod(instanceSaved);
        return instanceSaved;
         */
        return this.createOrUpdatePaymentInformation(instance);
    }

    private AcceptedPaymentMethod createAccpetedPaymentMethod(PaymentInformation basis) {
        AcceptedPaymentMethod apm = new AcceptedPaymentMethod();
        apm.setPaymentMethod(basis.getPaymentMethod());
        apm.setPaymentInformation(basis);
        apm.setSeller(basis.getSeller());
        apm.setId(0L);
        return this.acceptedPaymentMethodService.createOrUpdateInstance(apm);
    }

    public PaymentInformation getPaymentInformationBySellerIdAndPaymentMethodId(Long userId, Long paymentMethodId) {
        for(PaymentInformation paymentInformation : this.findAll()) {
            if(paymentInformation.getSeller().getId() == userId &&
                    paymentInformation.getAcceptedPaymentMethod().getId() == paymentMethodId) {
                return paymentInformation;
            }
        }
        return null;
    }

    @Override
    public PaymentInformation update(Long id, PaymentInformation instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.paymentInformationRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        PaymentInformation toDelete = this.paymentInformationRepository.findById(id).get();
        this.acceptedPaymentMethodService.deleteById(toDelete.getAcceptedPaymentMethod().getId());
        this.deletePaymentInfoFromUser(toDelete);
        this.deletePaymentInfoFromPaymentMethods(toDelete);
        this.paymentInformationRepository.deleteById(id);
        return id;
    }

    private void deletePaymentInfoFromUser(PaymentInformation toDelete) {
        User userOfPaymentInformation = this.userRepository.findById(toDelete.getSeller().getId()).get();
        userOfPaymentInformation.getPaymentInformation().remove(toDelete);
    }

    private void deletePaymentInfoFromPaymentMethods(PaymentInformation toDelete) {
        PaymentMethod paymentMethodOfInformation =
                this.paymentMethodRepository.findById(toDelete.getPaymentMethod().getId()).get();
        paymentMethodOfInformation.getPaymentInformation().remove(toDelete);
    }

    @Override
    public List<PaymentInformation> getPaymentInformationByUserId(Long id) {
        return this.findAll()
                .stream()
                .filter(paymentInformation -> paymentInformation.getSeller().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentInformation getPaymentInformationByUserIdAndPaymentMethodID(Long userId, Long paymentMethodId) {
        return this.findAll()
                .stream()
                .filter(paymentInformation ->
                        this.informationBySellerIdAndPaymentMethodId(paymentInformation, userId, paymentMethodId))
                .findFirst().get();
    }

    @Override
    public PaymentInformation createOrUpdatePaymentInformation(PaymentInformation instance) {
        PaymentInformation pi =
                this.getPaymentInformationBySellerIdAndPaymentMethodId(instance.getSeller().getId(), instance.getPaymentMethod().getId());
        if (pi == null) {
            return this.paymentInformationRepository.save(instance);
        }
        return this.update(instance.getId(), instance);
    }

    private boolean informationBySellerIdAndPaymentMethodId(PaymentInformation pi,
                                                                         Long sellerId, Long paymentMethodId) {
        return pi.getSeller().getId() == sellerId && paymentMethodId == pi.getPaymentMethod().getId();
    }
}
