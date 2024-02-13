package org.generation.italy.newEnteSportivo2.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PartecipazionePK implements Serializable {

	private Gara gara;
	private Velocista velocista;

	public PartecipazionePK() {
		super();
	}

	public PartecipazionePK(Gara gara, Velocista velocista) {
		super();
		this.gara = gara;
		this.velocista = velocista;
	}

	@ManyToOne
	public Gara getGara() {
		return gara;
	}

	public void setGara(Gara gara) {
		this.gara = gara;
	}

	@ManyToOne
	public Velocista getVelocista() {
		return velocista;
	}

	public void setVelocista(Velocista velocista) {
		this.velocista = velocista;
	}

//	@Basic(optional = false)
//    @Column(nullable = false, columnDefinition ="char(16)", name = "codice_fiscale")
//    private String codiceFiscale;
//    @Basic(optional = false)
//    @Column(name = "id_gara")
//    private Short idGara;

}
