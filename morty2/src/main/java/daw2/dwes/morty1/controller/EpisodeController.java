package daw2.dwes.morty1.controller;

import daw2.dwes.morty1.service.EpisodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/episodes")
public class EpisodeController {
    private final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("episodes", service.findAll());
        return "episodes/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("episode", service.findById(id));
        return "episodes/detail";
    }

    @GetMapping("/create")
    public String createEpisode() {
        service.crearEpisodioHardCoded();
        return "redirect:/episodes";
    }

    @GetMapping("/total")
    public String showTotalEpisodes(Model model) {
        model.addAttribute("total", service.getTotalEpisodios());
        return "episodes/total";
    }
}