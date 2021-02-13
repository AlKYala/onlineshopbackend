package de.yalama.onlineshopbackend.Message.purchaseMessage.service;

import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.purchaseMessage.repository.PurchaseMessageRepository;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PurchaseMessageServiceImpl extends PurchaseMessageService {

    private PurchaseMessageRepository purchaseMessageRepository;
    private Validator<PurchaseMessage, PurchaseMessageRepository> validator;
    private UserRepository userRepository;

    public PurchaseMessageServiceImpl(PurchaseMessageRepository purchaseMessageRepository,
                                      UserRepository userRepository) {
        this.purchaseMessageRepository = purchaseMessageRepository;
        this.validator = new Validator<PurchaseMessage, PurchaseMessageRepository>
                ("PurchaseMessage", purchaseMessageRepository);
    }

    @Override
    public PurchaseMessage findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.purchaseMessageRepository.findById(id).get();
    }

    @Override
    public List<PurchaseMessage> findAll() {
        return this.purchaseMessageRepository.findAll();
    }

    @Override
    public PurchaseMessage save(PurchaseMessage instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.purchaseMessageRepository.save(instance);
    }

    @Override
    public PurchaseMessage update(Long id, PurchaseMessage instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.purchaseMessageRepository.save(instance);
    }

    @Override
    /**
     * NOTE: Purchase Messages are the product itself and are therefore not deleted
     */
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);

        PurchaseMessage toDelete = this.findById(id);
        toDelete.getPurchaseOfMessage().getMessagesInPurchase()
                .removeIf(purchaseMessage -> purchaseMessage.getId() == id);

        this.deleteById(id);

        return id;
    }
}
