package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
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
public class JpaLandRepository implements LandRepository {
    private final EntityManager manager;

    JpaLandRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Land> findById(long id) {
        //return Optional.ofNullable(manager.find(Land.class, id, LockModeType.PESSIMISTIC_WRITE)); // SQL grammer error??
        return Optional.ofNullable(manager.find(Land.class, id));
    }

    @Override
    public List<Land> findAll() {
        return manager.createNamedQuery(Land.FIND_ALL, Land.class).getResultList();
    }

    @Override
    public void create(Land land) {
        manager.persist(land);
    }

    @Override
    public void delete(long id) {
        findById(id).ifPresent(land -> manager.remove(land));
    }
}
