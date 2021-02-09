package de.yalama.onlineshopbackend.Message.repository;

import de.yalama.onlineshopbackend.Message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
