package com.erysa.system.erysasystem.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {

	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "/Forms/login";
	}
}
