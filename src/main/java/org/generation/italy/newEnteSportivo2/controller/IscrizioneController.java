package org.generation.italy.newEnteSportivo2.controller;

import java.util.List;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.generation.italy.newEnteSportivo2.model.Iscrizione;
import org.generation.italy.newEnteSportivo2.repository.GaraRepository;
import org.generation.italy.newEnteSportivo2.repository.IscrizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/iscrizione")
public class IscrizioneController {
	
	
	@Autowired
	IscrizioneRepository iscrizioneRepository;
	
	@Autowired
	GaraRepository garaRepository;
	
	@GetMapping("/elenco")
	public String elencoGara(
			Model model
			) {
	List<Iscrizione> elencoIscritti= iscrizioneRepository.findAll();
	
	model.addAttribute("elencoiscritti",elencoIscritti);
	return"gara/iscrizione";
	}
	
	@GetMapping("/nuovo/{idGara}")
	public String nuovaIscrizioneGet(Model model,
			@PathVariable Short idGara) {
		Iscrizione i=new Iscrizione();
		Gara g;
		g=garaRepository.findById(idGara).get();
		i.setGara(g);
		List<Iscrizione> elencoIscrizioni=iscrizioneRepository.findAll();
		model.addAttribute("elencoIscrizioni", elencoIscrizioni);
		model.addAttribute("iscrizione", i);
		return "iscrizione/nuovo";
	}
	
	@PostMapping("/nuovo")
	public String nuovaIscrizionePost(Model model,
		    @ModelAttribute ("iscrizione")
			Iscrizione i,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<Iscrizione> elencoIscrizioni=iscrizioneRepository.findAll();
			model.addAttribute("elencoIscrizioni", elencoIscrizioni);		
			return "iscrizione/nuovo";
		}	
		System.out.println("gara: "+i.getGara());
		System.out.println("velocista: "+i.getVelocista());
		iscrizioneRepository.save(i);
		return "redirect:/gara/elencogara";
	}

}
