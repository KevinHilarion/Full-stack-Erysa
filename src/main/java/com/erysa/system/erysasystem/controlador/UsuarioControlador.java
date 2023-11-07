package com.erysa.system.erysasystem.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.erysa.system.erysasystem.modelo.Usuario;
import com.erysa.system.erysasystem.servicio.IFacturaService;
import com.erysa.system.erysasystem.util.PageRender;

@Controller
public class UsuarioControlador {

	
	@Autowired
	private IFacturaService facturaService;
	
	@GetMapping({ "/detallesUser/{id}" })
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Usuario usuario = facturaService.fetchByIdWithFacturas(id);

		if (usuario == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", " Datos del usuario: " + usuario.getNombre());

		return "/Users/VerUser";

	}
	
	//TablaDeUsuarios
	@GetMapping("/usuarios")
	public String usuarios(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 7);
		Page<Usuario> usuarios = facturaService.findAll(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/usuarios", usuarios);
		
		modelo.addAttribute("page", pageRender);
		modelo.addAttribute("usuarios", facturaService.findAll());
		return "/Users/Usuarios";
	}
	
}
