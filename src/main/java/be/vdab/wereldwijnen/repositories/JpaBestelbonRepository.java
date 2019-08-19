package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.*;
import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Repository
public class JpaBestelbonRepository implements BestelbonRepository {
    private final EntityManager manager;
    private final WijnService wijnService;

    JpaBestelbonRepository(EntityManager manager, WijnService wijnService) {
        this.manager = manager;
        this.wijnService = wijnService;
    }

    @Override
    public long create(BestelBon bestelBon) {
        manager.persist(bestelBon);
        manager.flush();
        long bestelbonId = bestelBon.getId();
        return bestelbonId;
    }

//    private void createBestelbonLijn(Wijn wijn, int aantal, BigDecimal prijs) {
//        BestelbonLijn bestelbonLijn = new BestelbonLijn(wijn, aantal, prijs);
//        manager.persist(bestelbonLijn);
//        manager.flush();
//        wijnService.updateBesteldAantal(wijn.getId(), aantal);
//    }
}
