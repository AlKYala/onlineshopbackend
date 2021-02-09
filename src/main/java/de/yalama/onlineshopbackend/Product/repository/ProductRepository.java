package de.yalama.onlineshopbackend.Product.repository;

import de.yalama.onlineshopbackend.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
