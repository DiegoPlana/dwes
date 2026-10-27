package org.example.heroquesttracker.controller;

import org.example.heroquesttracker.Service.HeroService;
import org.example.heroquesttracker.Service.QuestService;
import org.example.heroquesttracker.model.Hero;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorldController {
    private final HeroService heroService;
    private final QuestService questService;

    public WorldController(HeroService heroService, QuestService questService) {
        this.heroService = heroService;
        this.questService = questService;
    }

    @GetMapping("/heroes/initialize")
    public String initializeHeroes() {
        heroService.initializeHeroes();
        return "redirect:/heroes";
    }

    @GetMapping("/heroes")
    public String heroes(Model model) {
        model.addAttribute("heroes", heroService.getAllHeroes());
        return "hero-list";
    }

    @GetMapping("/quests")
    public String quests(Model model) {
        model.addAttribute("quests", questService.getAllQuests());
        return "quest-list";
    }

    @GetMapping("/quests/new")
    public String createQuest() {
        questService.createRandomQuest();
        return "redirect:/quests";
    }

    @GetMapping("/heroes/{name}")
    public String heroDetail(@PathVariable String name, Model model) {
        Hero hero = heroService.getHero(name)
                .orElseThrow(() -> new IllegalArgumentException("No existe el héroe: " + name));
        model.addAttribute("hero", hero);
        model.addAttribute("quests", hero.getQuests());
        return "hero-detail";
    }

    @GetMapping("/quests/solve/{id}")
    public String solveQuest(@PathVariable Long id) {
        questService.markQuestAsSolved(id);
        return "redirect:/quests";
    }

    @GetMapping("/quests/fail/{id}")
    public String failQuest(@PathVariable Long id) {
        questService.failQuest(id);
        return "redirect:/quests";
    }
}
