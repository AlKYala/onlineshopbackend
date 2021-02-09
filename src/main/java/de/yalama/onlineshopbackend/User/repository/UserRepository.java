package de.yalama.onlineshopbackend.User.repository;

import de.yalama.onlineshopbackend.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
