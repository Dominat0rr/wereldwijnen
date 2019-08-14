package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Land;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface LandService {
    Optional<Land> findById(long id);
    List<Land> findAll();
    void create(Land land);
    void delete(long id);
}
