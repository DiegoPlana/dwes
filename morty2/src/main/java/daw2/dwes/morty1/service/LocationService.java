package daw2.dwes.morty1.service;

import daw2.dwes.morty1.model.Location;
import daw2.dwes.morty1.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> findAll() {
        return repository.findUbicacionesConHumanos();
    }

    public Location findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Location update(Location location, String Nombre ) {
        location.setName(Nombre);
        return repository.save(location);
    }
    public Location saveLocName(Location location) {
        return repository.save(location);
    }

}

