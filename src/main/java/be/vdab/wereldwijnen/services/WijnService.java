package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Wijn;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface WijnService {
    List<Wijn> findAll();
    Optional<Wijn> findById(long id);
    List<Wijn> findAllBySoortId(long id);
    long findAantalWijnen();
}
