package de.yalama.onlineshopbackend.Message.ticketMessage.repository;

import de.yalama.onlineshopbackend.Message.ticketMessage.model.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketMessageRepository extends JpaRepository<TicketMessage, Long> {
}
