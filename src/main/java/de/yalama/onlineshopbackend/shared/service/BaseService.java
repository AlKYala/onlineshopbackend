package de.yalama.onlineshopbackend.shared.service;

import java.util.List;

/**
 * The interface implemented by all services for data models
 * @param <T> The data type the service handles
 */
public interface BaseService<T> {

    /**
     * Looks up an instance of type T by passed id
     * throws an Exception if not found
     * @param id The id of the instance to find
     * @return The instance with given id
     */
    T findById(Long id);

    /**
     * Returns all instances of type T
     * @return All instances of type for service arranged in a list
     */
    List<T> findAll();

    /**
     * Persists an instance of type T if appropriate else exceptions thrown
     * @param instance The instance to persist
     * @return The instance
     */
    T save(T instance);

    /**
     * Replaces an instance of type T with matching id - if id not found exception thrown
     * @param instance The instance to persist
     * @return The new persisted instance
     */
    T update(T instance);

    /**
     * Deletes an instance of type T with matching id
     * @param id the id of the instance to delete
     * @return the id of the deleted type
     */
    Long deleteById(Long id);
}
