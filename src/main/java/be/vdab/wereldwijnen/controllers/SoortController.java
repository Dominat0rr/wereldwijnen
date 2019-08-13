package be.vdab.wereldwijnen.controllers;

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
@RequestMapping("soorten")
public class SoortController {
    private final SoortService soortService;

    SoortController(SoortService soortService) {
        this.soortService = soortService;
    }

    @GetMapping
    public ModelAndView soorten() {
        return new ModelAndView("soorten", "soorten", soortService.findAll());
    }

    @GetMapping("{id}")
    ModelAndView soort(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("soort");
        soortService.findById(id).ifPresent(soort -> {
            modelAndView.addObject(soort);
            modelAndView.addObject("wijnen", soort.getWijnen());
        });
        return modelAndView;
    }
}
