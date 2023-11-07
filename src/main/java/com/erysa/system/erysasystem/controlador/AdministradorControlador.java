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
import com.erysa.system.erysasystem.modelo.Orden;
import com.erysa.system.erysasystem.modelo.Usuario;
import com.erysa.system.erysasystem.servicio.IFacturaService;
import com.erysa.system.erysasystem.servicio.IOrdenService;
import com.erysa.system.erysasystem.util.PageRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AdministradorControlador {
	

	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IOrdenService ordensServicio;
	
	private Logger logg = LoggerFactory.getLogger(AdministradorControlador.class);
	
	//PaginaDeOrdenes
	@GetMapping("/ordenes")
	public String ordenes (@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 7);
		Page<Orden> ordenes = ordensServicio.findAll(pageRequest);
		PageRender<Orden> pageRender = new PageRender<>("/ordenes", ordenes);
		
		modelo.addAttribute("page", pageRender);
		modelo.addAttribute("ordenes", ordensServicio.findAll());
		return "/Orden/Ordenes";
	}
	
	//InfoOrden
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		
		logg.info("Id de la orden {}", id);
		Orden orden = ordensServicio.findById(id).get();
		model.addAttribute("detalles", orden.getDetalle());
		return "/Orden/DetalleOrden";
	}
	
}
