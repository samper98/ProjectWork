package org.generation.italy.newEnteSportivo2.security.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Utente {
	
	// i campi obbligatori per la security 


	  @Id
	   private Integer id ;

	  @NotNull
	  private String username;

	  @NotNull
	  private String password;

	  @ManyToMany (fetch = FetchType.EAGER)  // i ruoli vengono caricati contestualmente al caricamento 
	  private Set<Ruolo> ruoli;   // ogni utente può assumere più ruoli 

	public Utente() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	public Set<Ruolo> getVelocisti() {
		return ruoli;
	}
	

}
