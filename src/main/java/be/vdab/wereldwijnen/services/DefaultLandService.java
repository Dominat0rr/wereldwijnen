package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.repositories.LandRepository;
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
public class DefaultLandService implements LandService {
    private final LandRepository repository;

    DefaultLandService(LandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Land> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Land> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Land land) {
        repository.create(land);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void delete(long id) {
        repository.delete(id);
    }
}
