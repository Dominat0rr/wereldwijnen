package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class WijnControllerTest {
    private WijnController wijnController;
    @Mock
    private WijnService wijnService;
    @Mock
    private Mandje mandje;

    @Before
    public void before() {
        when(wijnService.findById(1))
                .thenReturn(Optional.of(new Wijn(1, new Soort(1, "test", new Land(1, "test")),
                        (short) 1986, (byte) 3, BigDecimal.TEN)));
        wijnController = new WijnController(wijnService, mandje);
    }

    // wijnen
    @Test
    public void wijnenGebruiktDeThymeleafPaginaWijnen() {
        assertThat(wijnController.wijnen().getViewName()).isEqualTo("wijnen");
    }

    @Test
    public void wijnenGeeftWijnenDoorAanDeThymeleafPagina() {
        assertThat(wijnController.wijnen().getModel().get("wijnen")).isInstanceOf(List.class);
    }

    // wijn
    @Test
    public void wijnGebruiktDeThymeleafPaginWijn() {
        assertThat(wijnController.wijn(1).getViewName()).isEqualTo("wijn");
    }

    @Test
    public void wijnGeeftGevondenWijnDoorAanDeThymeleafPagina() {
        Wijn wijn = (Wijn) wijnController.wijn(1).getModel().get("wijn");
        assertThat(wijn.getId()).isEqualTo(1);
    }

    @Test
    public void wijnGeeftOnbestaandeWijnNietDoorAanDeThymeleafPagina() {
        assertThat(wijnController.wijn(-1).getModel()).doesNotContainKeys("wijn");
    }
}
