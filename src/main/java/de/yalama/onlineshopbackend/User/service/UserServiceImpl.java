package de.yalama.onlineshopbackend.User.service;

import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import de.yalama.onlineshopbackend.Message.privateMessage.service.PrivateMessageService;
import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.purchaseMessage.repository.PurchaseMessageRepository;
import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.repository.TicketMessageRepository;
import de.yalama.onlineshopbackend.Purchase.repository.PurchaseRepository;
import de.yalama.onlineshopbackend.Rating.repository.RatingRepository;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.models.exceptions.NotFoundException;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl extends UserService {

    private UserRepository userRepository;
    private Validator<User, UserRepository> validator;
    private PurchaseRepository purchaseRepository;
    private PurchaseMessageRepository purchaseMessageRepository;
    private TicketMessageRepository ticketMessageRepository;
    private PrivateMessageService privateMessageService;
    private AdvertisementRepository advertisementRepository;
    private RatingRepository ratingRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PurchaseRepository purchaseRepository,
                           PurchaseMessageRepository purchaseMessageRepository,
                           TicketMessageRepository ticketMessageRepository,
                           PrivateMessageService privateMessageService,
                           AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.validator = new Validator<User, UserRepository>("User", this.userRepository);
        this.purchaseRepository = purchaseRepository;
        this.purchaseMessageRepository = purchaseMessageRepository;
        this.ticketMessageRepository = ticketMessageRepository;
        this.privateMessageService = privateMessageService;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public User findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        User userWithEmail = this.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
        if(userWithEmail == null) {
            String errorMessage = String.format("No user with Email %s found", email);
            log.error(errorMessage);
            throw new NotFoundException(errorMessage);
        }
        return userWithEmail;
    }

    @Override
    public User save(User instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.userRepository.save(instance);
    }

    @Override
    public User update(Long id, User instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.userRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        User toDelete = this.findById(id);
        this.deleteAdvertisements(toDelete);
        this.deletePurchaseMessages(toDelete);
        this.deleteReceivedPrivateMessages(toDelete);
        this.deleteSentPrivateMessages(toDelete);
        this.deleteSentTicketMessages(toDelete);
        this.removeUserFromPurchaseMessagesAsSender(toDelete);
        this.removeUserFromTickets(toDelete);
        this.removeUserFromPurchasesAsBuyer(toDelete);
        this.deleteRatings(toDelete);
        this.deleteById(id);
        return id;
    }

    private void deleteAdvertisements(User toDelete) {
        toDelete.getSalesOfUser()
                .forEach(advertisement -> this.advertisementRepository.deleteById(advertisement.getId()));
    }

    private void removeUserFromPurchasesAsBuyer(User toDelete) {
        toDelete.getPurchasesOfUser()
                .forEach(purchase -> this.purchaseRepository.findById(purchase.getId()).get().setBuyer(null));
    }

    private void removeUserFromPurchaseMessagesAsSender(User toDelete) {
        toDelete.getMessagesInPurchases().forEach(purchaseMessage -> purchaseMessage.setSender(null));
    }

    private void removeUserFromTickets(User toDelete) {
        toDelete.getMessagesInTickets().forEach(ticketMessage -> ticketMessage.setWriter(null));
    }

    private void deleteSentTicketMessages(User toDelete) {
        Set<TicketMessage> sentTicketMessages = toDelete.getMessagesInTickets();

        sentTicketMessages.forEach(ticketMessage -> {
            this.ticketMessageRepository.deleteById(ticketMessage.getId());
        });
    }

    private void deletePurchaseMessages(User toDelete) {
        Set<PurchaseMessage> purchaseMessages = toDelete.getMessagesInPurchases();

        purchaseMessages.forEach(purchaseMessage -> {
            this.purchaseMessageRepository.findById(toDelete.getId()).get().setSender(null);
            this.purchaseRepository.deleteById(purchaseMessage.getId());
        });
    }

    private void deleteReceivedPrivateMessages(User toDelete) {
        Set<PrivateMessage> receivedPrivateMessages = toDelete.getReceivedPrivateMessages();

        receivedPrivateMessages.forEach(privateMessage -> {
            this.privateMessageService.findById(privateMessage.getId()).setReceiver(null);
            this.privateMessageService.deleteIfBothUsersDeleted(privateMessage.getId());
        });
    }

    private void deleteSentPrivateMessages(User toDelete) {
        Set<PrivateMessage> sentPrivateMessages = toDelete.getSentPrivateMessages();

        sentPrivateMessages.forEach(privateMessage -> {
            this.privateMessageService.findById(privateMessage.getId()).setSender(null);
            this.privateMessageService.deleteIfBothUsersDeleted(privateMessage.getId());
        });
    }

    private void deleteRatings(User toDelete) {
        toDelete.getRatings().forEach(rating -> this.ratingRepository.deleteById(rating.getId()));
    }
}
