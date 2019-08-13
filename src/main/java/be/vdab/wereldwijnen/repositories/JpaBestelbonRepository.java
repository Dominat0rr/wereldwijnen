package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
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
    public long create(BestelbonForm form, Mandje mandje) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("naam", form.getNaam());
        kolomWaarden.put("straat", form.getStraat());
        kolomWaarden.put("huisNr", form.getHuisnummer());
        kolomWaarden.put("postcode", form.getPostcode());
        kolomWaarden.put("gemeente", form.getGemeente());
        //long bestelbonId = insertBestelbon.executeAndReturnKey(kolomWaarden).longValue();
        long bestelbonId = 1;
        for (Map.Entry<Long, Integer> entry : mandje.getWijnen().entrySet()) {
            Long id = entry.getKey();
            Integer aantal = entry.getValue();
            Optional<Wijn> wijn = wijnService.findById(id);
            BigDecimal prijs = wijn.get().getPrijs();
            createBestelbonLijn(bestelbonId, id, aantal, prijs);
        }
        return bestelbonId;
    }

    private void createBestelbonLijn(long bestelbonId, long bierId, int aantal, BigDecimal prijs) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("bestelbonid", bestelbonId);
        kolomWaarden.put("bierid", bierId);
        kolomWaarden.put("aantal", aantal);
        kolomWaarden.put("prijs", prijs);
        //insertBestelbonLijn.execute(kolomWaarden);
        wijnService.updateBesteldAantal(bierId, aantal);
    }
}
