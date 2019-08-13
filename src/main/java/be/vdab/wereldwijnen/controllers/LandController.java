package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.LandService;
import be.vdab.wereldwijnen.services.SoortService;
import be.vdab.wereldwijnen.services.WijnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("landen")
public class LandController {
    private final LandService landService;
    private final SoortService soortService;
    private final WijnService wijnService;

    LandController(LandService landService, SoortService soortService, WijnService wijnService) {
        this.landService = landService;
        this.soortService = soortService;
        this.wijnService = wijnService;
    }

    @GetMapping
    public ModelAndView landen() {
        return new ModelAndView("landen", "landen", landService.findAll());
    }

    @GetMapping("{id}")
    ModelAndView land(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("land");
        landService.findById(id).ifPresent(land -> {
            modelAndView.addObject(land);
            modelAndView.addObject("soorten", land.getSoorten());
        });
        return modelAndView;
    }

    @GetMapping("{landId}/soorten/{soortId}")
    ModelAndView soort(@PathVariable long landId, @PathVariable long soortId) {
        ModelAndView modelAndView = new ModelAndView("land");
        landService.findById(landId).ifPresent(land -> {
            modelAndView.addObject(land);
            modelAndView.addObject("soorten", land.getSoorten());
        });
        soortService.findById(soortId).ifPresent(soort -> {
            modelAndView.addObject(soort);
            modelAndView.addObject("wijnen", soort.getWijnen());
        });
        return modelAndView;
    }
}
