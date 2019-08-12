package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.LandService;
import be.vdab.wereldwijnen.services.SoortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    LandController(LandService landService, SoortService soortService) {
        this.landService = landService;
        this.soortService = soortService;
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
            modelAndView.addObject("soorten", soortService.findAllByLandId(id));
        });
        return modelAndView;
    }
}
