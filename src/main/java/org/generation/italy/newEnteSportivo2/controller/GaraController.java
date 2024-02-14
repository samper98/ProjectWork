package org.generation.italy.newEnteSportivo2.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.generation.italy.newEnteSportivo2.model.Velocista;
import org.generation.italy.newEnteSportivo2.repository.GaraRepository;
import org.generation.italy.newEnteSportivo2.repository.VelocistiRepository;
import org.hibernate.mapping.Collection;
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
			Model model,
			@RequestParam(required = false) String luogo,
			@RequestParam(required = false) String ordina
			) throws Exception{
		 
//		List<Gara> elencoGara=garaRepository.findByLuogo(luogo);
		ArrayList<Gara> elencoGara=(ArrayList<Gara>) garaRepository.findAll();
	    List<Velocista> elencoVelocisti=velocistiRepository.findAll();

	   
       if(ordina!=null)
       {
    	   if(ordina.equals("asc")) 
    		   Collections.sort(elencoGara);
    	   else 
    		   return"ordinamento non valido";
    	   
       }
		
		model.addAttribute("elencogare",elencoGara);
		model.addAttribute("elencovelocisti",elencoVelocisti);
		return "/gara/elencogara";
		
	}

	
//	@GetMapping("/dettaglio")
//	public String luogoGara(
//			Model model,
//			@PathVariable String luogo) {
//		List<Gara> optGara=garaRepository.findByLuogo(luogo);
//		
//
//		
//		model.addAttribute("luogo",optGara);
//		return "/gara/luogogara";
//	}
	
	

	
}

