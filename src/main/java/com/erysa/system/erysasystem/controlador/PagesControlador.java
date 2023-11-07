package com.erysa.system.erysasystem.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesControlador {

	// Dashboard
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}

	// Prueba
	//	@GetMapping("/prueba")
	//	public String prueba() {
	//		return "Prueba";
	//	}

	@GetMapping("/Recuperar")
	public String Recuperar() {
		return "/Forms/Recuperar";

	}
}
