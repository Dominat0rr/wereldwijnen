package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.WijnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("/")
public class IndexController {
    private final WijnService wijnService;

    IndexController(WijnService wijnService) {
        this.wijnService = wijnService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("aantalWijnen", wijnService.findAantalWijnen());
        return modelAndView;
    }
}
