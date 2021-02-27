package de.yalama.onlineshopbackend.Message.ticketMessage.controller;

import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import de.yalama.onlineshopbackend.Message.ticketMessage.service.TicketMessageService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketMessage")
public class TicketMessageController implements BaseController<TicketMessage, Long> {

    @Autowired
    private TicketMessageService ticketMessageService;

    @Override
    @GetMapping
    public List<TicketMessage> findAll() {
        return this.ticketMessageService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public TicketMessage findById(@PathVariable Long id) {
        return this.ticketMessageService.findById(id);
    }

    @Override
    @PostMapping
    public TicketMessage create(@RequestBody TicketMessage ticketMessage) {
        return this.ticketMessageService.save(ticketMessage);
    }

    @Override
    @PutMapping("/{id}")
    public TicketMessage update(@PathVariable Long id, @RequestBody TicketMessage ticketMessage) {
        return this.ticketMessageService.update(id, ticketMessage);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.ticketMessageService.deleteById(id);
    }
}
