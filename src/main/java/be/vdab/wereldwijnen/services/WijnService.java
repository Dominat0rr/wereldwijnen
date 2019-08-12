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
    List<Wijn> findByIds(Set<Long> ids);
    List<Wijn> findAllWijnenBySoortId(long id);
    List<Wijn> findAllWijnenByLandId(long id);
    long create(Wijn wijn);
    void update(Wijn wijn);
    void updateBesteldAantal(long id, int aantal);
    void delete(long id);
    long findAantalWijnen();
    void bestelBier(long id, int aantal);
}
