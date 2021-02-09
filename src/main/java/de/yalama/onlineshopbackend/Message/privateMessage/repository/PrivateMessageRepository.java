package de.yalama.onlineshopbackend.Message.privateMessage.repository;

import de.yalama.onlineshopbackend.Message.privateMessage.model.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
}
