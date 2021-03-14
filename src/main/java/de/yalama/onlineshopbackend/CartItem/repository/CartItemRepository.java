package de.yalama.onlineshopbackend.CartItem.repository;

import de.yalama.onlineshopbackend.CartItem.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
