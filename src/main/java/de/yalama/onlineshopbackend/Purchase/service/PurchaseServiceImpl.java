package de.yalama.onlineshopbackend.Purchase.service;

import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.Purchase.repository.PurchaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PurchaseServiceImpl extends PurchaseService {

    private PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.purchaseRepository.findById(id).get();
    }

    @Override
    public List<Purchase> findAll() {
        return this.purchaseRepository.findAll();
    }

    @Override
    public Purchase save(Purchase instance) {
        //TODO Validator notExists, Exception notSaved
        return this.purchaseRepository.save(instance);
    }

    @Override
    public Purchase update(Purchase instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.purchaseRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
