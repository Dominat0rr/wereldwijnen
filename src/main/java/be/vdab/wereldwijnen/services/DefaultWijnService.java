package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.repositories.WijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultWijnService implements WijnService {
    private final WijnRepository repository;

    DefaultWijnService(WijnRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Wijn> findAll() {
        return null;
    }

    @Override
    public Optional<Wijn> findById(long id) {
        return Optional.empty();
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
        return 0;
    }

    @Override
    public void bestelBier(long id, int aantal) {

    }
}
