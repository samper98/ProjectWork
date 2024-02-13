package org.generation.italy.newEnteSportivo2.repository;

import org.generation.italy.newEnteSportivo2.model.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IscrizioneRepository extends JpaRepository<Iscrizione, Integer>{

}
