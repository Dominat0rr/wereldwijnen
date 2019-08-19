package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface SoortRepository {
    Optional<Soort> findById(long id);
    List<Soort> findAll();
    List<Soort> findAllByLandId(long id);
}
