package org.generation.italy.newEnteSportivo2.security.service;

import java.util.Optional;

import org.generation.italy.newEnteSportivo2.security.model.Utente;
import org.generation.italy.newEnteSportivo2.security.path.DatabaseUserDetails;
import org.generation.italy.newEnteSportivo2.security.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService{
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utente> user=utenteRepository.findByUsername(username);
		if (user.isPresent())
			return new DatabaseUserDetails(user.get());
		else
			throw new UsernameNotFoundException("Utente non trovato");		
	}
	
	
	

}