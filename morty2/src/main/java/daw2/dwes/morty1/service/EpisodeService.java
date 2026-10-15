package daw2.dwes.morty1.service;

import daw2.dwes.morty1.model.Episode;
import daw2.dwes.morty1.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EpisodeService {
    private final EpisodeRepository repository;

    public EpisodeService(EpisodeRepository repository) {
        this.repository = repository;
    }

    public List<Episode> findAll() {
        LocalDate fechaLimite = LocalDate.of(2015, 12, 31);
        return repository.findEpisodiosPosterioresA(fechaLimite);
    }

    public Episode findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public void crearEpisodioHardCoded() {
        Episode episodio = new Episode();
        episodio.setName("Episodio Inventado");
        episodio.setAirDate(LocalDate.of(2026, 10, 13));
        episodio.setEpisodeCode("S99E99");
        repository.save(episodio);
    }
    public Long getTotalEpisodios() {
        return repository.contarTotalEpisodios();
    }
}