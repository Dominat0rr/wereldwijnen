package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.forms.BestelForm;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("wijnen")
public class WijnController {
    private final WijnService wijnService;
    private final Mandje mandje;

    WijnController(WijnService wijnService, Mandje mandje) {
        this.wijnService = wijnService;
        this.mandje = mandje;
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
            Optional<Integer> optionalAantal = mandje.getAantal(id);
            BestelForm form = new BestelForm(optionalAantal.isPresent() ? optionalAantal.get() : null);
            modelAndView.addObject(form);
        });
        return modelAndView;
    }

    @PostMapping("{id}/bestellen")
    ModelAndView bestellen(@PathVariable long id, @Valid BestelForm form, Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("wijn");
            wijnService.findById(id).ifPresent(bier -> modelAndView.addObject(bier));
            return modelAndView;
        }
        mandje.voegToe(id, form.getAantal());
        return new ModelAndView("redirect:/mandje");
    }
}
