package de.yalama.onlineshopbackend.shared.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @param <T> The class of the entity to be handled in controller requests
 * @param <ID> The class used for IDs - Long
 */
public interface BaseController<T, ID> {

    /**
     * GET-Request
     * @return all instances of T persisted in the database
     */
    List<T> findAll();

    /**
     * GET-Request calls findById(E id) of its service to find an instance of Type T with id else exception thrown
     * @param id The ID to find an instance of Type T
     * @return An instance of Type T
     */
    T findById(@PathVariable ID id);

    /**
     * POST-Request
     * Used to persist t in the database
     * @param t The instance to persist in the database
     * @return the persisted instance
     */
    T create(@RequestBody T t);

    /**
     * PUT-Request
     * Used to update an instance with the same id
     * causes exception to be thrown by handling service if no instance of type T with same id is found
     * @param id the id of the instance to update. has to match field id of t
     * @param t The instance to replace the old instance with same id
     * @return The updated instance
     */
    T update(@PathVariable ID id, @RequestBody T t);

    /**
     * DELETE-Request
     * Deletes instance of type T in the Database or throws exception if no instance of Type T with same id is found
     * @param id The id of the instane to delete
     * @return the id of the deleted instance
     */
    ID delete(@PathVariable ID id);
}
