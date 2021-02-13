package de.yalama.onlineshopbackend.User.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import de.yalama.onlineshopbackend.Message.privateMessage.repository.PrivateMessageRepository;
import de.yalama.onlineshopbackend.Message.privateMessage.service.PrivateMessageService;
import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.purchaseMessage.repository.PurchaseMessageRepository;
import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.repository.TicketMessageRepository;
import de.yalama.onlineshopbackend.Pictures.repository.PictureRepository;
import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.Purchase.repository.PurchaseRepository;
import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.Ticket.repository.TicketRepository;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;

import java.util.List;
import java.util.Set;

public class UserServiceImpl extends UserService {

    private UserRepository userRepository;
    private Validator<User, UserRepository> validator;
    private PurchaseRepository purchaseRepository;
    private PurchaseMessageRepository purchaseMessageRepository;
    private TicketMessageRepository ticketMessageRepository;
    private PrivateMessageService privateMessageService;
    private AdvertisementRepository advertisementRepository;

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

        toDelete.getSalesOfUser()
                .forEach(advertisement -> this.advertisementRepository.deleteById(advertisement.getId()));

        toDelete.getPurchasesOfUser()
                .forEach(purchase -> this.purchaseRepository.findById(purchase.getId()).get().setBuyer(null));

        toDelete.getMessagesInPurchases().forEach(purchaseMessage -> purchaseMessage.setSender(null));

        toDelete.getMessagesInTickets().forEach(ticketMessage -> ticketMessage.setWriter(null));

        Set<PrivateMessage> receivedPrivateMessages = toDelete.getReceivedPrivateMessages();

        Set<PrivateMessage> sentPrivateMessages = toDelete.getSentPrivateMessages();

        Set<TicketMessage> sentTicketMessages = toDelete.getMessagesInTickets();

        Set<PurchaseMessage> purchaseMessages = toDelete.getMessagesInPurchases();

        this.deleteById(id);

        purchaseMessages.forEach(purchaseMessage -> {
            this.purchaseMessageRepository.findById(id).get().setSender(null);
            this.purchaseRepository.deleteById(purchaseMessage.getId());
        });

        sentTicketMessages.forEach(ticketMessage -> {
            this.ticketMessageRepository.deleteById(ticketMessage.getId());
        });

        sentPrivateMessages.forEach(privateMessage -> {
            this.privateMessageService.findById(privateMessage.getId()).setSender(null);
            this.privateMessageService.deleteIfBothUsersDeleted(privateMessage.getId());
        });

        receivedPrivateMessages.forEach(privateMessage -> {
            this.privateMessageService.findById(privateMessage.getId()).setReceiver(null);
            this.privateMessageService.deleteIfBothUsersDeleted(privateMessage.getId());
        });

        return id;
    }



}
