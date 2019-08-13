package be.vdab.wereldwijnen.controllers;

import be.vdab.wereldwijnen.domain.Wijn;
import be.vdab.wereldwijnen.forms.BestelbonForm;
import be.vdab.wereldwijnen.services.BestelbonService;
import be.vdab.wereldwijnen.services.WijnService;
import be.vdab.wereldwijnen.sessions.Mandje;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final WijnService wijnService;
    private final BestelbonService bestelbonService;
    private Map<Wijn, Integer> wijnen = new LinkedHashMap<>();

    MandjeController(Mandje mandje, WijnService wijnService, BestelbonService bestelbonService) {
        this.mandje = mandje;
        this.wijnService = wijnService;
        this.bestelbonService = bestelbonService;
    }

    @NumberFormat(pattern = "0.00")
    private BigDecimal getTotaalPrijs() {
        BigDecimal prijs = BigDecimal.ZERO;

        for (Map.Entry<Wijn, Integer> entry : wijnen.entrySet()) {
            Wijn key = entry.getKey();
            Integer value = entry.getValue();

            BigDecimal prijsPerSoort = key.getPrijs().multiply(BigDecimal.valueOf(value));
            prijs = prijs.add(prijsPerSoort);
        }
        return prijs;
    }

    @GetMapping
    public ModelAndView mandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");
        BestelbonForm bestelbonform = new BestelbonForm(null, null, null, null, 0);
        modelAndView.addObject("bestelbonform", bestelbonform);

        if (mandje.isLeeg()) return modelAndView;
        wijnen.clear();
        mandje.getWijnen().forEach((id, aantal) -> {
            wijnen.put(wijnService.findById(id).get(), aantal);
        });
        wijnen.forEach((wijn, aantal) -> System.out.println(wijn.getId() + " " + wijn.getPrijs()));
        modelAndView.addObject("wijnen", wijnen);
        modelAndView.addObject("totaalPrijs", getTotaalPrijs());
        return modelAndView;
    }

    @PostMapping
    public String voegToe(long id, int aantal) {
        mandje.voegToe(id, aantal);
        return "redirect:/mandje";
    }

    @PostMapping("bestellen")
    public ModelAndView toevoegen(@Valid BestelbonForm form, Errors errors, RedirectAttributes redirect) {
        if (mandje.isLeeg()) return new ModelAndView("redirect:/");
        if(errors.hasErrors()) return new ModelAndView("mandje", "mandje", mandje);
        long id = bestelbonService.create(form, mandje);
        redirect.addAttribute("id", id);
        return new ModelAndView("redirect:/mandje/besteld/{id}");
    }

    @GetMapping("besteld/{id}")
    public ModelAndView besteld(@PathVariable long id) {
        return new ModelAndView("besteld");
    }

    @GetMapping("verwijder")
    public String verwijder(@RequestParam long id){
        Optional<Wijn> wijn = wijnService.findById(id);
        wijnen.remove(wijn.get());
        mandje.verwijder(id);
        return "redirect:/mandje";
    }
}
