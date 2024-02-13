package org.generation.italy.newEnteSportivo2.controller;

import java.util.List;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.generation.italy.newEnteSportivo2.model.Velocista;
import org.generation.italy.newEnteSportivo2.repository.GaraRepository;
import org.generation.italy.newEnteSportivo2.repository.VelocistiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping ("/velocisti")
public class VelocistiController {
	
	@Autowired
	VelocistiRepository velocistiRepository;
	
	@GetMapping("/elenco")
	public String elencoVelocisti(
			Model model) {
		List<Velocista> elencoVelocisti=velocistiRepository.findAll();
//		StringBuilder elenco=new StringBuilder();
//		elenco.append("elenco gare: " + elencoVelocisti.size());
//		elenco.append("<br><br>");
//		for (Velocista v:elencoVelocisti) {
//			elenco.append(v.getCodiceFiscale()+ "<br>");
//			elenco.append(v.getNominativo()+ "<br>");
//			elenco.append(v.getAltezza()+ "<br>");
//			elenco.append(v.getPeso()+ "<br>");
//			elenco.append(v.getEta()+ "<br>");
//			
//		} 
		model.addAttribute("elenco",elencoVelocisti);
		return "/velocisti/elenco";
		
	}

}
