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
/**
 * E represents an entity, subclass of BaseEntity
 * T a class that implements JpaRepository - a Repository to handle instances of E
 */
public class Validator<E extends BaseEntity, T extends JpaRepository> {

    private String entityName;
    private T repository;

    public Validator(String entityName, T repository) {
        this.entityName = entityName;
        this.repository = repository;
    }

    /**
     * Takes a Long (Wrapper)-Object and checks if it is null
     * throws an exception if is null
     */
    public void checkIdNotNull(Long id) {
        if(id == null) {
            String message = "IDs cannot be null!";
            log.info(message);
            throw new IdInvalidException(message);
        }
    }

    /**
     * Uses member T repository to check if an entity by ID id can be found
     * throws exception if entity by id is found
     * Used when entites have to be saved (NOT UPDATED) in the database (POST-Request)
     */
    public void checkEntityNotExists(Long id) {
        if(this.repository.existsById(id)) {
            String message = String.format("Entity of class %s with ID %d still exists",
                    this.entityName, id);
            log.info(message);
            throw new NotDeletedException(message);
        }
    }

    /**
     * Uses member T repository to check if an entity by ID id can be found
     * throws exception if entity by id is NOT found
     * Used when entites have to be deleted or updated (NOT SAVED) in the database (DELETE-Request or PUT-Request)
     */
    public void checkEntityExists(Long id) {
        if(!this.repository.existsById(id)) {
            String message = String.format("Entity of class %d with ID %D cannot be found", this.entityName, id);
            log.info(message);
            throw new NotFoundException(message);
        }
    }

    /**
     * Checks if both parameters are identical
     * Used in updates (PUT-REQUEST)
     * @param id
     * @param id2
     */
    public void checkIDsAreIdentical(Long id, Long id2) {
        if(id.longValue() != id2.longValue()) {
            String message = String.format("IDs %d and %d do not match", id, id2);
            log.info(message);
            throw new IdInvalidException(message);
        }
    }

    /**
     * Written for updates in services. combines checkIDsAreIdentical and checkEntity exists for cleaner code
     */
    public void checkCanUpdate(Long id, Long id2) {
        this.checkIDsAreIdentical(id, id2);
        this.checkEntityExists(id2);
    }

    public boolean checkStringIsNullOrBlank(String s) {
        return s == null || s.isBlank();
    }
}
