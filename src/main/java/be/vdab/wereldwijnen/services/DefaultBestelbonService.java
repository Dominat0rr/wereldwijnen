package be.vdab.wereldwijnen.services;

import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.repositories.BestelbonRepository;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBestelbonService implements BestelbonService {
    private final BestelbonRepository repository;

    DefaultBestelbonService(BestelbonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public long create(BestelbonForm bestelbonForm, Mandje mandje) {
        return repository.create(bestelbonForm, mandje);
    }
}

