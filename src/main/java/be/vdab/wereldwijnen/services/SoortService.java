package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Soort;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface SoortService {
    Optional<Soort> findById(long id);
    List<Soort> findAll();
    List<Soort> findAllByLandId(long id);
    void create(Soort soort);
    void delete(long id);
}
