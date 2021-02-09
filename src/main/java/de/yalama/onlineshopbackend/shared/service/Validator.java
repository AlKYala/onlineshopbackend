package de.yalama.onlineshopbackend.shared.service;

import de.yalama.onlineshopbackend.shared.models.BaseEntity;
import de.yalama.onlineshopbackend.shared.models.exceptions.IdInvalidException;
import de.yalama.onlineshopbackend.shared.models.exceptions.NotDeletedException;
import de.yalama.onlineshopbackend.shared.models.exceptions.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
@Setter
@Slf4j
public class Validator<E extends BaseEntity, T extends JpaRepository> {

    private String entityName;
    private T repository;

    public Validator(String entityName, T repository) {
        this.entityName = entityName;
        this.repository = repository;
    }

    public void checkIdNotNull(Long id) {
        if(id == null) {
            String message = "IDs cannot be null!";
            log.info(message);
            throw new IdInvalidException(message);
        }
    }

    public void checkEntityNotExists(Long id) {
        if(this.repository.existsById(id)) {
            String message = String.format("Entity of class %s with ID %d still exists",
                    this.entityName, id);
            log.info(message);
            throw new NotDeletedException(message);
        }
    }

    public void checkEntityExists(Long id) {
        if(!this.repository.existsById(id)) {
            String message = String.format("Entity of class %d with ID %D cannot be found", this.entityName, id);
            log.info(message);
            throw new NotFoundException(message);
        }
    }

    public void checkIDsAreIdentical(Long id, Long id2) {
        if(id.longValue() != id2.longValue()) {
            String message = String.format("IDs %d and %d do not match", id, id2);
            log.info(message);
            throw new IdInvalidException(message);
        }
    }

    public boolean checkStringIsNullOrBlank(String s) {
        return s == null || s.isBlank();
    }
}
