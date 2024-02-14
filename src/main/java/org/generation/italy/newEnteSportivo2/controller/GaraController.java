package org.generation.italy.newEnteSportivo2.controller;

import java.security.Timestamp;
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
	public String elencoGara(Model model) {

		List<Gara> elencoGara = garaRepository.findAll();
		// ArrayList<Gara> elencoGara=(ArrayList<Gara>) garaRepository.findAll();
		List<Velocista> elencoVelocisti = velocistiRepository.findAll();

		model.addAttribute("elencogare", elencoGara);
		model.addAttribute("elencovelocisti", elencoVelocisti);
		return "gara/elencogara";

	}

	@GetMapping("/ordina-gare")
	public String ordinaGare(Model model) {
		List<Gara> elencoGara = garaRepository.findAll();

		Collections.sort(elencoGara);

		model.addAttribute("elencogare", elencoGara);
		return "gara/elencoGara";
	}
	
	@GetMapping("/ordina-per-data")
	public String ordinaPerData(Model model) {
		List<Gara> elencoDataGara=garaRepository.findAll();
		Collections.sort(elencoDataGara);
		model.addAttribute("elencoDataGara", elencoDataGara);
		return "gara/elencogara";
	}

}
