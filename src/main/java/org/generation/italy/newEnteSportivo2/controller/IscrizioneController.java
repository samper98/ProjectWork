package org.generation.italy.newEnteSportivo2.controller;

import java.util.List;

import org.generation.italy.newEnteSportivo2.model.Iscrizione;
import org.generation.italy.newEnteSportivo2.repository.IscrizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/iscrizione")
public class IscrizioneController {
	
	
	@Autowired
	IscrizioneRepository iscrizioneRepository;
	
	
	@GetMapping("/elenco")
	public String elencoGara(
			Model model
			) {
	List<Iscrizione> elencoIscritti= iscrizioneRepository.findAll();
	
	model.addAttribute("elencoiscritti",elencoIscritti);
	return"gara/iscrizione";
	}

}
