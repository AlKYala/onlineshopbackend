package de.yalama.onlineshopbackend.Message.purchaseMessage.service;

import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.purchaseMessage.repository.PurchaseMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PurchaseMessageServiceImpl extends PurchaseMessageService {

    private PurchaseMessageRepository purchaseMessageRepository;

    public PurchaseMessageServiceImpl(PurchaseMessageRepository purchaseMessageRepository) {
        this.purchaseMessageRepository = purchaseMessageRepository;
    }

    @Override
    public PurchaseMessage findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.purchaseMessageRepository.findById(id).get();
    }

    @Override
    public List<PurchaseMessage> findAll() {
        return this.purchaseMessageRepository.findAll();
    }

    @Override
    public PurchaseMessage save(PurchaseMessage instance) {
        //TODO Validator notExists, Exception notSaved
        return this.purchaseMessageRepository.save(instance);
    }

    @Override
    public PurchaseMessage update(PurchaseMessage instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.purchaseMessageRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        return null;
    }
}
