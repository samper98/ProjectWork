package org.generation.italy.newEnteSportivo2.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ruolo {
	
	@Id
	private Integer id ;

	@NotNull
	private String name;    // nome del ruolo (es: admin,user,...)

	public Ruolo() {
		super();
	}

	public Ruolo(Integer id, @NotNull String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	
}
