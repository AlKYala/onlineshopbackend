package de.yalama.onlineshopbackend.Marke.repository;

import de.yalama.onlineshopbackend.Marke.model.Marke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkeRepository extends JpaRepository<Marke, Long> {
}
