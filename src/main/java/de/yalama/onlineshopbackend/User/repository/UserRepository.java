package de.yalama.onlineshopbackend.User.repository;

import de.yalama.onlineshopbackend.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
