package org.example.heroquesttracker.Service;

import org.example.heroquesttracker.Repository.HeroRepository;
import org.example.heroquesttracker.model.Hero;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroService {
    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    /** Crea el grupo inicial solo cuando la tabla todavía está vacía. */
    @Transactional
    public void initializeHeroes() {
        if (heroRepository.count() > 0) return;

        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero("Aria", "Mage", 1, 0.0f, new ArrayList<>()));
        heroes.add(new Hero("Borin", "Warrior", 1, 0.0f, new ArrayList<>()));
        heroes.add(new Hero("Lira", "Ranger", 1, 0.0f, new ArrayList<>()));
        heroRepository.saveAll(heroes);
    }

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    /** Optional permite al controlador distinguir entre encontrado y ausente. */
    public Optional<Hero> getHero(String name) {
        return heroRepository.findById(name);
    }

    /** Suma experiencia total y sube un nivel por cada nuevo bloque de 10 XP. */
    public void addExperience(Hero hero, float xp) {
        int oldLevelThreshold = (int) Math.floor(hero.getExperience() / 10.0f);
        float totalExperience = hero.getExperience() + xp;
        int newLevelThreshold = (int) Math.floor(totalExperience / 10.0f);

        hero.setExperience(totalExperience);
        hero.setNivel(hero.getNivel() + newLevelThreshold - oldLevelThreshold);
        heroRepository.save(hero);
    }
}
