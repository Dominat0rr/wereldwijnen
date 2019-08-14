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
        return repository.findAll();
    }

    @Override
    public Optional<Wijn> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Wijn> findAllBySoortId(long id) {
        return repository.findAllBySoortId(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Wijn wijn) {
        repository.create(wijn);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void updateBesteldAantal(long id, int aantal) {
        repository.updateBesteldAantal(id, aantal);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public long findAantalWijnen() {
        return repository.findAantalWijnen();
    }
}
