package com.dib.demo.repository;

import com.dib.demo.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for model class {@link Beer}
 */

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
