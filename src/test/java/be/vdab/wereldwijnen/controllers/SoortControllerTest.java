package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
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
public class SoortControllerTest {
    private SoortController soortController;
    @Mock
    private SoortService soortService;

    @Before
    public void before() {
        when(soortService.findById(1))
                .thenReturn(Optional.of(new Soort(1,"test", new Land("test"))));
        soortController = new SoortController(soortService);
    }

    // soorten
    @Test
    public void soortenGebruiktDeThymeleafPaginaSoorten() {
        assertThat(soortController.soorten().getViewName()).isEqualTo("soorten");
    }

    @Test
    public void soortenGeeftSoortenDoorAanDeThymeleafPagina() {
        assertThat(soortController.soorten().getModel().get("soorten")).isInstanceOf(List.class);
    }

    @Test
    public void soortGebruiktDeThymeleafPaginaSoort() {
        assertThat(soortController.soort(1).getViewName()).isEqualTo("soort");
    }
}
