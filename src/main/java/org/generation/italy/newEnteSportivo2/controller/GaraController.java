package org.generation.italy.newEnteSportivo2.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.generation.italy.newEnteSportivo2.model.Velocista;
import org.generation.italy.newEnteSportivo2.repository.GaraRepository;
import org.generation.italy.newEnteSportivo2.repository.VelocistiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/gara")
public class GaraController {
	
	@Autowired
	VelocistiRepository velocistiRepository;
	
	@Autowired
	GaraRepository garaRepository;
	
	@GetMapping("/elenco")
	public String elencoGara(
			Model model) {
		 
		List<Gara> elencoGara=garaRepository.findAll();
		List<Velocista> elencoVelocisti=velocistiRepository.findAll();
//		ArrayList<Gara> elencoGara=(ArrayList<Gara>) garaRepository.findAll();
//	
//		
//		StringBuilder elenco=new StringBuilder();
//		elenco.append("elenco gare: " + elencoGara.size());
//		elenco.append("<br><br>");
//		for (Gara g:elencoGara) {
//			elenco.append(g.getIdGara()+ "<br>");
//			elenco.append(g.getLuogo()+ "<br>");
//			elenco.append(g.getDataGara()+ "<br>");
//			
//		}
		model.addAttribute("elencogare",elencoGara);
		model.addAttribute("elencovelocisti",elencoVelocisti);
		return "/gara/elencogara";
		
	}

	
	
	
	
//	@GetMapping("/dettaglio/{id}")			//gestisce una richiesta GET all'indirizzo /Fornitori/elenco
//	@ResponseBody
//	public String dettaglioGara(@PathVariable Integer id) {
//		Optional<Gara> optGara=garaRepository.findById(id);
//		if (optGara.isPresent())		//il prodotto è stato trovato
//		{
//			StringBuilder dettaglio=new StringBuilder();
//			dettaglio.append(optGara.get().toString()+"<br>");
//			dettaglio.append("elenco gare : <br>");
//			for (Gara g: optGara.get().getIdGara())
//				dettaglio.append(g.toString()+"<br>");
//			return dettaglio.toString();
//		}			
//		else
//			return "Velocista non trovato";}
	
	
}

