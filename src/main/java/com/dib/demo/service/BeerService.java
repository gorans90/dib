package com.dib.demo.service;

import com.dib.demo.exception.NotFoundException;
import com.dib.demo.model.Beer;

import java.util.List;

/**
 * Service interface for class {@link Beer}
 */

public interface BeerService {

    /**
     * Load initial data to the database
     */
    void initialize();

    /**
     * Get all beers from database
     *
     * @return list of {@link Beer}
     */
    List<Beer> getAll() throws NotFoundException;

    /**
     * Get beer by id
     *
     * @param id
     * @return {@link Beer}
     */
    Beer getById(Long id) throws NotFoundException;

    /**
     * Delete beer by id
     *
     * @param id
     */
    void deleteById(Long id) throws NotFoundException;
}
