package de.yalama.onlineshopbackend.Ticket.service;

import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.repository.TicketMessageRepository;
import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.Ticket.repository.TicketRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class TicketServiceImpl extends TicketService{

    private TicketRepository ticketRepository;
    private Validator<Ticket, TicketRepository> validator;
    private TicketMessageRepository ticketMessageRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketMessageRepository ticketMessageRepository) {
        this.ticketRepository = ticketRepository;
        this.validator = new Validator<Ticket, TicketRepository>("Ticket", this.ticketRepository);
        this.ticketMessageRepository = ticketMessageRepository;
    }

    @Override
    public Ticket findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket save(Ticket instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.ticketRepository.save(instance);
    }

    @Override
    public Ticket update(Long id, Ticket instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.ticketRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);

        Ticket toDelete = this.findById(id);

        Set<TicketMessage> messagesOfTicketToDelete = toDelete.getMessagesOfTicket();

        this.ticketRepository.deleteById(id);

        messagesOfTicketToDelete
                .forEach(ticketMessage -> this.ticketMessageRepository.deleteById(ticketMessage.getId()));

        return id;
    }
}
