package org.generation.italy.newEnteSportivo2.repository;

import org.generation.italy.newEnteSportivo2.model.Velocista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VelocistiRepository extends JpaRepository<Velocista, String>{

}
