package org.generation.italy.newEnteSportivo2.repository;

import java.util.List;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GaraRepository extends JpaRepository<Gara, Short>{
	@Query("select g from Gara g order by luogo ")
    List<Gara>findByLuogo (String luogo);
//	List<Gara> findAllByOrderByLuogoAsc(String luogo);

}
