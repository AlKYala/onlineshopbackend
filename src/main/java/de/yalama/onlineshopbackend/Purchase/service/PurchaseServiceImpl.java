package de.yalama.onlineshopbackend.Purchase.service;

import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.Purchase.repository.PurchaseRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PurchaseServiceImpl extends PurchaseService {

    private PurchaseRepository purchaseRepository;
    private Validator<Purchase, PurchaseRepository> validator;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.validator = new Validator<Purchase, PurchaseRepository>("Purchase", this.purchaseRepository);
    }

    @Override
    public Purchase findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.purchaseRepository.findById(id).get();
    }

    @Override
    public List<Purchase> findAll() {
        return this.purchaseRepository.findAll();
    }

    @Override
    public Purchase save(Purchase instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.purchaseRepository.save(instance);
    }

    @Override
    public Purchase update(Purchase instance) {
        this.validator.checkEntityExists(instance.getId());
        return this.purchaseRepository.save(instance);
    }

    @Override
    /**
     * NOTE: Purchases are not deleted
     */
    public Long deleteById(Long id) {
        return null;
    }
}
