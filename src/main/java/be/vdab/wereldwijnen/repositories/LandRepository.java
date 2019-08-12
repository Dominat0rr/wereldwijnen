package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface LandRepository {
    Optional<Land> findById(long id);
    List<Land> findAll();
}
