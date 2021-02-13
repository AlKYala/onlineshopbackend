package de.yalama.onlineshopbackend.Ticket.controller;

import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import de.yalama.onlineshopbackend.Ticket.service.TicketService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController implements BaseController<Ticket, Long> {

    @Autowired
    private TicketService ticketService;

    @Override
    @GetMapping
    public List<Ticket> findAll() {
        return this.ticketService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Long id) {
        return this.ticketService.findById(id);
    }

    @Override
    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        return this.ticketService.save(ticket);
    }

    @Override
    @PutMapping("/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return this.ticketService.update(id, ticket);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.ticketService.deleteById(id);
    }
}
