package org.generation.italy.newEnteSportivo2.controller;

import java.util.List;

import org.generation.italy.newEnteSportivo2.model.Gara;
import org.generation.italy.newEnteSportivo2.repository.GaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/gara")
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
		for (Gara g:elencoGara)
			elenco.append(g.toString()+ "<br>");
		return elenco.toString();
		
	}
	

}
