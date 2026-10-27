package org.example.heroquesttracker.Service;

import org.example.heroquesttracker.Repository.HeroRepository;
import org.example.heroquesttracker.Repository.QuestRepository;
import org.example.heroquesttracker.model.Hero;
import org.example.heroquesttracker.model.Quest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class QuestService {
    private static final List<String> ADJECTIVES = List.of(
            "Lost", "Forbidden", "Ancient", "Cursed", "Hidden", "Glorious", "Frozen", "Burning");
    private static final List<String> NOUNS = List.of(
            "Temple", "Blade", "Forest", "Scroll", "Beast", "Crown", "Crypt", "Dragon");
    private static final List<String> ACTIONS = List.of(
            "Rescue", "Defeat", "Recover", "Explore", "Protect", "Destroy", "Uncover", "Seal");

    private final QuestRepository questRepository;
    private final HeroRepository heroRepository;
    private final HeroService heroService;

    public QuestService(QuestRepository questRepository, HeroRepository heroRepository, HeroService heroService) {
        this.questRepository = questRepository;
        this.heroRepository = heroRepository;
        this.heroService = heroService;
    }

    @Transactional
    public Quest createRandomQuest() {
        List<Hero> heroes = heroRepository.findAll();
        if (heroes.isEmpty()) {
            throw new IllegalStateException("No hay héroes. Inicializa los héroes antes de crear misiones.");
        }

        ThreadLocalRandom random = ThreadLocalRandom.current();
        String title = pick(random, ACTIONS) + " the " + pick(random, ADJECTIVES) + " " + pick(random, NOUNS);
        float experience = random.nextInt(101) / 10.0f; // Valores de 0.0 a 10.0, en pasos de una décima.
        Hero hero = heroes.get(random.nextInt(heroes.size()));

        Quest quest = new Quest();
        quest.setTitle(title);
        quest.setSolved(false);
        quest.setExperience(experience);
        quest.setHero(hero);
        return questRepository.save(quest);
    }

    private String pick(ThreadLocalRandom random, List<String> values) {
        return values.get(random.nextInt(values.size()));
    }

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    /** Optional evita devolver null si no existe la misión solicitada. */
    @Transactional
    public Optional<Quest> markQuestAsSolved(Long id) {
        return questRepository.findById(id).map(quest -> {
            // Hacer la operación idempotente evita conceder XP más de una vez.
            if (!Boolean.TRUE.equals(quest.getSolved())) {
                quest.setSolved(true);
                questRepository.save(quest);
                heroService.addExperience(quest.getHero(), quest.getExperience());
            }
            return quest;
        });
    }

    @Transactional
    public boolean failQuest(Long id) {
        return questRepository.findById(id).map(quest -> {
            questRepository.delete(quest);
            return true;
        }).orElse(false);
    }
}
