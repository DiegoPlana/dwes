package daw2.dwes.morty1.controller;

import daw2.dwes.morty1.service.CharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("characters", service.findAll());
        return "characters/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        service.borrarPersonaje(id);
        return "redirect:/episodes";
    }

    @GetMapping("/create")
    public String createCharacter() {
        service.crearPersonajeYLocalizacionHardCoded();
        return "redirect:/characters";
    }

    @GetMapping("/summary")
    public String summary(Model model) {
        model.addAttribute("resumen", service.getPersonajesConUbicacionYTotalEpisodios());
        return "characters/summary";
    }
}

