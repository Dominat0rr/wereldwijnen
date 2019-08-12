package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;
import org.springframework.stereotype.Repository;

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
        return manager.createQuery("select w from Wijn w order by w.id", Wijn.class).getResultList();
    }

    @Override
    public Optional<Wijn> findById(long id) {
        return Optional.ofNullable(manager.find(Wijn.class, id));
    }

    @Override
    public List<Wijn> findByIds(Set<Long> ids) {
        return null;
    }

    @Override
    public List<Wijn> findAllWijnenBySoortId(long id) {
        return null;
    }

    @Override
    public List<Wijn> findAllWijnenByLandId(long id) {
        //return manager.createQuery("select count(*) from Wijn w", Wijn.class);
        return null;
    }

    @Override
    public long create(Wijn wijn) {
        return 0;
    }

    @Override
    public void update(Wijn wijn) {

    }

    @Override
    public void updateBesteldAantal(long id, int aantal) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public long findAantalWijnen() {
        return manager.createNamedQuery(Wijn.FIND_AANTAL_WIJNEN, Wijn.class).getFirstResult();
    }

    @Override
    public void bestelBier(long id, int aantal) {

    }
}
