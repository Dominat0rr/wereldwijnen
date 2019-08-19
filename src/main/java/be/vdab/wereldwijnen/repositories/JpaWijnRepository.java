package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Repository
public class JpaWijnRepository implements WijnRepository {
    private final EntityManager manager;

    JpaWijnRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Wijn> findAll() {
        return manager.createNamedQuery(Wijn.FIND_ALL, Wijn.class).getResultList();
    }

    @Override
    public Optional<Wijn> findById(long id) {
        return Optional.ofNullable(manager.find(Wijn.class, id));
    }

    @Override
    public List<Wijn> findAllBySoortId(long id) {
        return manager.createNamedQuery(Wijn.FIND_BY_SOORT, Wijn.class)
                .setParameter("soortid", id)
                .getResultList();
    }

    @Override
    public long findAantalWijnen() {
        return 0;
    }
}
