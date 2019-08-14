package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Soort;
import be.vdab.wereldwijnen.repositories.SoortRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultSoortService implements SoortService {
    private final SoortRepository repository;

    DefaultSoortService(SoortRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Soort> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Soort> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Soort> findAllByLandId(long id) {
        return repository.findAllByLandId(id);
    }

    @Override
    public void create(Soort soort) {
        repository.create(soort);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }
}
