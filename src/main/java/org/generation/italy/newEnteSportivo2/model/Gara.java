package org.generation.italy.newEnteSportivo2.model;

import java.sql.Timestamp;


import java.util.List;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "gara")
public class Gara implements Comparable<Gara> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "id_gara")
	private Short idGara;

	@Column(nullable = false, name = "data_ora_gara")
	private Timestamp dataGara;

	@Column(nullable = false, length = 30, name = "luogo")
	private String luogo;

	@JsonBackReference
	@OneToMany(mappedBy = "iscrizionePK.gara")
	List<Iscrizione> elencoIscritti;

	@JsonBackReference
	@OneToMany(mappedBy = "partecipazionePK.gara")
	List<Partecipazione> elencoPartecipanti;

	public Gara() {
		super();
	}

	public Gara(Short idGara, Timestamp dataGara, String luogo) {
		super();
		this.idGara = idGara;
		this.dataGara = dataGara;
		this.luogo = luogo;
	}

	public Timestamp getDataGara() {
		return dataGara;
	}

	public void setDataGara(Timestamp dataGara) {
		this.dataGara = dataGara;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Short getIdGara() {
		return idGara;
	}
	
	@Override
	public int compareTo(Gara g) {
		
		return this.getLuogo().compareTo(g.getLuogo());
		
	}
	//prova ordine per data
	public int compareTo1(Gara g) {
		
		return this.getDataGara().compareTo(g.getDataGara());
		
	}
	


}
