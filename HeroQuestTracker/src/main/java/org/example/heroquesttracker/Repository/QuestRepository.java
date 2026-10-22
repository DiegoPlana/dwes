package org.example.heroquesttracker.Repository;

import jakarta.persistence.Id;
import jakarta.persistence.Query;
import org.example.heroquesttracker.model.Hero;
import org.example.heroquesttracker.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {

}
