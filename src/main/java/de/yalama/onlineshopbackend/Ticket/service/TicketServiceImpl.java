package de.yalama.onlineshopbackend.Ticket.service;

import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.Ticket.repository.TicketRepository;

import java.util.List;

public class TicketServiceImpl extends TicketService{

    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket save(Ticket instance) {
        //TODO Validator notExists, Exception notSaved
        return this.ticketRepository.save(instance);
    }

    @Override
    public Ticket update(Ticket instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.ticketRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
