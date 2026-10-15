package daw2.dwes.morty1.service;

import daw2.dwes.morty1.repository.CharacterRepository;
import daw2.dwes.morty1.model.Character;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public List<Character> findAll() {
        return repository.findPersonajesMasAltosQue(1.70);    }


    public Character findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void borrarPersonaje(Long id) {
        repository.deleteById(id);
    }

    // Autowired sirve para usar otro repositorio, en este caso location
    @org.springframework.beans.factory.annotation.Autowired
    private daw2.dwes.morty1.repository.LocationRepository locationRepository;

    public void crearPersonajeYLocalizacionHardCoded() {

        daw2.dwes.morty1.model.Location nuevaUbicacion = new daw2.dwes.morty1.model.Location();
        nuevaUbicacion.setName("Planeta Tierra 2.0");
        nuevaUbicacion.setDimension("Dimensión C-138");
        locationRepository.save(nuevaUbicacion);


        daw2.dwes.morty1.model.Character nuevoPersonaje = new daw2.dwes.morty1.model.Character();
        nuevoPersonaje.setName("Morty Malvado Clon");
        nuevoPersonaje.setStatus("Alive");
        nuevoPersonaje.setSpecies("Human");

        nuevoPersonaje.setLocation(nuevaUbicacion);

        repository.save(nuevoPersonaje);
    }

    public List<Object[]> getPersonajesConUbicacionYTotalEpisodios() {
        return repository.findPersonajesConUbicacionYTotalEpisodios();
    }


}


