package org.example.heroquesttracker.Repository;

import org.example.heroquesttracker.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, String> {
}
