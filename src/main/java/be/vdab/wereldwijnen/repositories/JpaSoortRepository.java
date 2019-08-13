package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Repository
public class JpaSoortRepository implements SoortRepository {
    private final EntityManager manager;

    JpaSoortRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Soort> findById(long id) {
        //return Optional.ofNullable(manager.find(Soort.class, id, LockModeType.PESSIMISTIC_WRITE));
        return Optional.ofNullable(manager.find(Soort.class, id));
    }

    @Override
    public List<Soort> findAll() {
        return manager.createNamedQuery(Soort.FIND_ALL, Soort.class).getResultList();
    }

    @Override
    public List<Soort> findAllByLandId(long id) {
        return manager.createNamedQuery(Soort.FIND_BY_LAND, Soort.class)
                .setParameter("landid", id)
                .getResultList();
    }
}
