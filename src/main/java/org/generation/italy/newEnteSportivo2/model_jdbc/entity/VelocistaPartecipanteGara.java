package org.generation.italy.newEnteSportivo2.model_jdbc.entity;

public class VelocistaPartecipanteGara {

	
	private Long idGara;
	

	private String nominativo;
	
	private Integer eta;

	
	private Float tempo;


	public VelocistaPartecipanteGara(Long idGara, String nominativo, Integer eta, Float tempo) {
		super();
		this.idGara = idGara;
		this.nominativo = nominativo;
		this.eta = eta;
		this.tempo = tempo;
	}
	
	public VelocistaPartecipanteGara(String nominativo, Integer eta, Float tempo) {
		super();
		this.nominativo = nominativo;
		this.eta = eta;
		this.tempo = tempo;
	}
	

	
	public String getNominativo() {
		return nominativo;
	}

	public Float getTempo() {
		return tempo;
	}

	public Long getIdGara() {
		return idGara;
	}


	public Integer getEta() {
		return eta;
	}

	
}
