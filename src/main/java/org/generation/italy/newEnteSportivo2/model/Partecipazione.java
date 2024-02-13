package org.generation.italy.newEnteSportivo2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "partecipazione")
@AssociationOverrides({
		@AssociationOverride(name = "partecipazionePK.gara", joinColumns = @JoinColumn(name = "id_gara")),
		@AssociationOverride(name = "partecipazionePK.velocista", joinColumns = @JoinColumn(name = "codice_fiscale", columnDefinition = "char(16)")) })
public class Partecipazione {

//	@Id
//	@Column(nullable=false, length = 16, name="codice_fiscale")
//	private String codiceFiscale;
//	
//    @Id
//	@Column(nullable=false, name="id_gara")
//	private Short idGara;

	private PartecipazionePK partecipazionePK = new PartecipazionePK();

//	@EmbeddedId
//    protected PartecipazionePK PartecipazionePK;

	@Column(nullable = false, name = "tempo")
	private Float tempo;

	@JsonBackReference
	@ManyToOne(optional = false)
	private Velocista velocista;

	@JsonBackReference
	@ManyToOne(optional = false)
	private Gara gara;

//	public String getCodiceFiscale() {
//		return codiceFiscale;
//	}
//
//	public Short getIdGara() {
//		return idGara;
	// }

	public Partecipazione(Float tempo, Velocista velocista, Gara gara) {
		super();
		this.tempo = tempo;
		this.velocista = velocista;
		this.gara = gara;
	}

	public Partecipazione() {
		super();
	}

	@EmbeddedId
	public PartecipazionePK getPartecipazionePK() {
		return partecipazionePK;
	}

	public void setPartecipazionePK(PartecipazionePK partecipazionePK) {
		this.partecipazionePK = partecipazionePK;
	}

	@Transient
	public Velocista getVelocista() {
		return velocista;
	}

	public void setVelocista(Velocista velocista) {
		this.velocista = velocista;
	}

	@Transient
	public Gara getGara() {
		return gara;
	}

	public void setGara(Gara gara) {
		this.gara = gara;
	}

	public void setTempo(Float tempo) {
		this.tempo = tempo;
	}

	public Float getTempo() {
		return tempo;
	}

}
