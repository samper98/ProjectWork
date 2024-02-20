package org.generation.italy.newEnteSportivo2.controller;

import org.generation.italy.newEnteSportivo2.security.model.Ruolo;
import org.generation.italy.newEnteSportivo2.security.model.Utente;
import org.generation.italy.newEnteSportivo2.security.path.DatabaseUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

//	@GetMapping("/test")	//gestisce una richiesta GET all'indirizzo /
//	@ResponseBody
//	public String test() {
//		return "questo è un test!";
//	}
	
	
	// GET IS NEW GOT 
	@GetMapping
	public String index(Authentication authentication) {
		DatabaseUserDetails dbUser=(DatabaseUserDetails) authentication.getPrincipal();
		Utente u=dbUser.getUtente();
		Boolean staff=false;
		for (Ruolo r:u.getRuoli())
			if(r.getName().equals("staff-gara"))
				staff=true;
		if (staff)
		
			return "redirect:/ente-sportivo/homepage-staff-gara";
		else {
			System.out.println("homepage velocista");
			return "redirect:/ente-sportivo/homepage-velocista";
		}
	}
	
}
