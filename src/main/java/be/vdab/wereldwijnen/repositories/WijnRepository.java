package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface WijnRepository {
    List<Wijn> findAll();
    Optional<Wijn> findById(long id);
    List<Wijn> findAllBySoortId(long id);
    void create(Wijn wijn);
    void updateBesteldAantal(long id, int aantal);
    void delete(long id);
    long findAantalWijnen();
}
