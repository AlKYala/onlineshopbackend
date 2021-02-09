package de.yalama.onlineshopbackend.Message.ticketMessage.service;

import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.repository.TicketMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TicketMessageServiceImpl extends TicketMessageService {

    private TicketMessageRepository ticketMessageRepository;

    public TicketMessageServiceImpl(TicketMessageRepository ticketMessageRepository) {
        this.ticketMessageRepository = ticketMessageRepository;
    }


    @Override
    public TicketMessage findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.ticketMessageRepository.findById(id).get();
    }

    @Override
    public List<TicketMessage> findAll() {
        return this.ticketMessageRepository.findAll();
    }

    @Override
    public TicketMessage save(TicketMessage instance) {
        //TODO Validator notExists, Exception notSaved
        return this.ticketMessageRepository.save(instance);
    }

    @Override
    public TicketMessage update(TicketMessage instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.ticketMessageRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
