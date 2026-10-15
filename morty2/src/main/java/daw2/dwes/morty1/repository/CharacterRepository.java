package daw2.dwes.morty1.repository;

import daw2.dwes.morty1.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("SELECT c FROM Character c WHERE c.height > :alturaMinima")
    List<Character> findPersonajesMasAltosQue(@Param("alturaMinima") Double alturaMinima);

    @Query("SELECT c.name, c.location.name, SIZE(c.episodes) FROM Character c")
    List<Object[]> findPersonajesConUbicacionYTotalEpisodios();

}

