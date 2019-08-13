package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.services.WijnService;
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
@RequestMapping("wijnen")
public class WijnController {
    private final WijnService wijnService;

    WijnController(WijnService wijnService) {
        this.wijnService = wijnService;
    }

    @GetMapping
    public ModelAndView wijnen() {
        return new ModelAndView("wijnen", "wijnen", wijnService.findAll());
    }

    @GetMapping("{id}")
    ModelAndView wijn(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("wijn");
        wijnService.findById(id).ifPresent(wijn -> {
            modelAndView.addObject(wijn);
        });
        return modelAndView;
    }
}
