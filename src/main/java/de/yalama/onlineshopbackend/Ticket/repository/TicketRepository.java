package de.yalama.onlineshopbackend.Ticket.repository;

import de.yalama.onlineshopbackend.Ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
