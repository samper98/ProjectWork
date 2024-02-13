package org.generation.italy.newEnteSportivo2.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "iscrizione")
@AssociationOverrides({ @AssociationOverride(name = "iscrizionePK.gara", joinColumns = @JoinColumn(name = "id_gara")),
		@AssociationOverride(name = "iscrizionePK.velocista", joinColumns = @JoinColumn(name = "codice_fiscale", columnDefinition = "char(16)")) })

public class Iscrizione {

	private IscrizionePK iscrizionePK = new IscrizionePK();

	/*
	 * @Id
	 * 
	 * @Column(nullable=false, name="id_gara") private Short idGara;
	 */

//	 @EmbeddedId
//	    protected IscrizionePK iscrizionePK;

	/*
	 * @Column(nullable=false, columnDefinition="char(16)", name="codice_fiscale")
	 * private String codiceFiscale;
	 */

	@EmbeddedId
	public IscrizionePK getIscrizionePK() {
		return iscrizionePK;
	}

	public void setIscrizionePK(IscrizionePK iscrizionePK) {
		this.iscrizionePK = iscrizionePK;
	}

	@Column(nullable = false, name = "data_iscrizione")
	private Timestamp dataIscrizione;

	@JsonBackReference
	@ManyToOne(optional = false)
	private Velocista velocista;

	@JsonBackReference
	@ManyToOne(optional = false)
	private Gara gara;

	public Iscrizione() {
		super();
	}

	public Iscrizione(Timestamp dataIscrizione, Velocista velocista, Gara gara) {
		super();
		this.dataIscrizione = dataIscrizione;
		this.velocista = velocista;
		this.gara = gara;
	}

	/*
	 * public String getCodiceFiscale() { return codiceFiscale; } /@Id
	 * 
	 * @Column(nullable=false, name="id_gara") private Short idGara;
	 */

//	public Short getIdGara() {
//		return idGara;
//	}

	public Timestamp getDataIscrizione() {
		return dataIscrizione;
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

	public void setDataIscrizione(Timestamp dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

}
