package de.yalama.onlineshopbackend.AcceptedPaymentMethods.service;

import de.yalama.onlineshopbackend.AcceptedPaymentMethods.model.AcceptedPaymentMethod;
import de.yalama.onlineshopbackend.AcceptedPaymentMethods.repository.AcceptedPaymentMethodRepository;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AcceptedPaymentMethodServiceImpl extends AcceptedPaymentMethodService {

    private AcceptedPaymentMethodRepository acceptedPaymentMethodRepository;
    private Validator<AcceptedPaymentMethod, AcceptedPaymentMethodRepository> validator;

    public AcceptedPaymentMethodServiceImpl(AcceptedPaymentMethodRepository acceptedPaymentMethodRepository) {
        this.acceptedPaymentMethodRepository = acceptedPaymentMethodRepository;
        this.validator =
                new Validator<AcceptedPaymentMethod, AcceptedPaymentMethodRepository>
                        ("acceptedPaymentMethod", acceptedPaymentMethodRepository);
    }

    @Override
    public AcceptedPaymentMethod findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.acceptedPaymentMethodRepository.findById(id).get();
    }

    @Override
    public List<AcceptedPaymentMethod> findAll() {
        return this.acceptedPaymentMethodRepository.findAll();
    }

    @Override
    public AcceptedPaymentMethod save(AcceptedPaymentMethod instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.acceptedPaymentMethodRepository.save(instance);
    }

    @Override
    public AcceptedPaymentMethod update(Long id, AcceptedPaymentMethod instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.acceptedPaymentMethodRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        AcceptedPaymentMethod toDelete = this.acceptedPaymentMethodRepository.getOne(id);
        toDelete
                .getSeller()
                .getAcceptedPaymentMethods()
                .removeIf(acceptedPaymentMethod -> acceptedPaymentMethod.getId() == id);
        toDelete
                .getPaymentMethod()
                .getAcceptedPaymentMethods()
                .removeIf(acceptedPaymentMethod -> acceptedPaymentMethod.getId() == id);
        toDelete.getPaymentInformation().setAcceptedPaymentMethod(null);
        this.acceptedPaymentMethodRepository.deleteById(id);
        return id;
    }

    @Override
    public List<PaymentMethod> findPaymentMethodsBySellerId(Long userId) {
        List<PaymentMethod> paymentMethodsofUser = new ArrayList<PaymentMethod>();
        List<AcceptedPaymentMethod> acceptedPaymentMethodsOfUser =
                this.findAll()
                        .stream()
                        .filter(acceptedPaymentMethod -> acceptedPaymentMethod.getSeller().getId() == userId)
                        .collect(Collectors.toList());
        for(AcceptedPaymentMethod acceptedPaymentMethod: acceptedPaymentMethodsOfUser) {
            paymentMethodsofUser.add(acceptedPaymentMethod.getPaymentMethod());
        }
        return paymentMethodsofUser;
    }

    @Override
    public Long deleteByInstance(AcceptedPaymentMethod acceptedPaymentMethod) {
        Long id = this.findIdOfAcceptedPaymentMethod(acceptedPaymentMethod);
        if(id > 0) {
            this.deleteById(id);
        }
        return id;
    }

    @Override
    public AcceptedPaymentMethod createOrUpdateInstance(AcceptedPaymentMethod acceptedPaymentMethod) {
        Long id = this.findIdOfAcceptedPaymentMethod(acceptedPaymentMethod);
        if(id > 0) {
            acceptedPaymentMethod.setId(id);
            return this.update(id, acceptedPaymentMethod);
        }
        return this.save(acceptedPaymentMethod);
    }

    private Long findIdOfAcceptedPaymentMethod(AcceptedPaymentMethod acceptedPaymentMethod) {
        List<AcceptedPaymentMethod> acceptedPaymentMethods =  this.findAll();
        for(AcceptedPaymentMethod savedPaymentMethod: acceptedPaymentMethods) {
            if(savedPaymentMethod.getSeller().getId() == acceptedPaymentMethod.getSeller().getId() &&
                    acceptedPaymentMethod.getPaymentMethod().getId() == savedPaymentMethod.getPaymentMethod().getId()) {
                return savedPaymentMethod.getId();
            }
        }
        return -1L;
    }

    @Override
    public AcceptedPaymentMethod findByPaymentInformationId(Long paymentInformationId) {
        return this.findAll()
                .stream()
                .filter(acceptedPaymentMethod -> acceptedPaymentMethod.getPaymentInformation().getId() == paymentInformationId)
                .findFirst().get();
    }
}
