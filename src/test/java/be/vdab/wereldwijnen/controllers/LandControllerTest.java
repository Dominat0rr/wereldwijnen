package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.services.LandService;
import be.vdab.wereldwijnen.services.SoortService;
import be.vdab.wereldwijnen.services.WijnService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
public class LandControllerTest {
    private LandController landController;
    @Mock
    private LandService landService;
    @Mock
    private SoortService soortService;
    @Mock
    private WijnService wijnService;

    @Before
    public void before() {
        when(landService.findById(1))
                .thenReturn(Optional.of(new Land(1,"test")));
        landController = new LandController(landService, soortService, wijnService);
    }

    // landen
    @Test
    public void landenGebruiktDeThymeleafPaginaLanden() {
        assertThat(landController.landen().getViewName()).isEqualTo("landen");
    }

    @Test
    public void landenGeeftLandenDoorAanDeThymeleafPagina() {
        assertThat(landController.landen().getModel().get("landen")).isInstanceOf(List.class);
    }

    @Test
    public void landGebruiktDeThymeleafPaginaLand() {
        assertThat(landController.land(1).getViewName()).isEqualTo("land");
    }

    // land
    @Test
    public void landGeeftGevondenLandDoorAanDeThymeleafPagina() {
        Land land = (Land) landController.land(1).getModel().get("land");
        assertThat(land.getId()).isEqualTo(1);
    }

    @Test
    public void landGeeftOnbestaandLandNietDoorAanDeThymeleafPagina() {
        assertThat(landController.land(-1).getModel()).doesNotContainKeys("land");
    }
}
