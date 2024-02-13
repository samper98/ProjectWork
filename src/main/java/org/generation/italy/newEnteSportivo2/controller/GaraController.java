package org.generation.italy.newEnteSportivo2.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.generation.italy.newEnteSportivo2.model.Velocista;
import org.generation.italy.newEnteSportivo2.repository.GaraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Gara")
public class GaraController {
	
	@Autowired
	GaraRepository garaRepository;
	
	@GetMapping("/elenco")
	@ResponseBody
	public String elencoGara() {
		List<Gara> elencoGara=garaRepository.findAll();
		StringBuilder elenco=new StringBuilder();
		elenco.append("elenco gare: " + elencoGara.size());
		elenco.append("<br><br>");
		for (Gara g:elencoGara) {
			elenco.append(g.getIdGara()+ "<br>");
			elenco.append(g.getLuogo()+ "<br>");
			elenco.append(g.getDataGara()+ "<br>");
			
		}
		return elenco.toString();
		
	}
	
//	@GetMapping("{id}")			//gestisce una richiesta GET all'indirizzo /Fornitori/elenco
//	@ResponseBody
//	public String dettaglioGara(@PathVariable Integer id) {
//		Optional<Gara> optGara=garaRepository.findById(id);
//		if (optGara.isPresent())		//il prodotto è stato trovato
//		{
//			StringBuilder dettaglio=new StringBuilder();
//			dettaglio.append(optGara.get().toString()+"<br>");
//			dettaglio.append("Prodotti forniti: <br>");
//			for (Velocista v: optGara.get().getIdGara())
//				dettaglio.append(v.toString()+"<br>");
//			return dettaglio.toString();
//		}			
//		else
//			return "Velocista non trovato";}
	
	

}
