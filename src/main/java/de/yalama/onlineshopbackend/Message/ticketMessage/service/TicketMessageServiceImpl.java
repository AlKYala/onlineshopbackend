package de.yalama.onlineshopbackend.Message.ticketMessage.service;

import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.repository.TicketMessageRepository;
import de.yalama.onlineshopbackend.Ticket.repository.TicketRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketMessageServiceImpl extends TicketMessageService {

    private TicketMessageRepository ticketMessageRepository;
    private Validator<TicketMessage, TicketMessageRepository> validator;
    private TicketRepository ticketRepository;

    public TicketMessageServiceImpl(TicketMessageRepository ticketMessageRepository,
                                    TicketRepository ticketRepository) {
        this.ticketMessageRepository = ticketMessageRepository;
        this.validator = new Validator<TicketMessage, TicketMessageRepository>
                ("TicketMessage", ticketMessageRepository);
        this.ticketRepository = ticketRepository;
    }


    @Override
    public TicketMessage findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.ticketMessageRepository.findById(id).get();
    }

    @Override
    public List<TicketMessage> findAll() {
        return this.ticketMessageRepository.findAll();
    }

    @Override
    public TicketMessage save(TicketMessage instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.ticketMessageRepository.save(instance);
    }

    @Override
    public TicketMessage update(TicketMessage instance) {
        this.validator.checkEntityExists(instance.getId());
        return this.ticketMessageRepository.save(instance);
    }

    @Override
    /**
     * NOTE: The ticket has to be deleted first before the the messages are deleted
     */
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);

        TicketMessage toDelete = this.findById(id);

        boolean ticketOfMessageExists = this.ticketRepository.existsById(toDelete.getTicketOfMessage().getId());

        if(!ticketOfMessageExists) {
            this.ticketMessageRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
