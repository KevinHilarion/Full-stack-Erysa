package com.erysa.system.erysasystem.controlador;


import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.erysa.system.erysasystem.modelo.Categoria;
import com.erysa.system.erysasystem.repositorio.CategoriaRepositorio;
import com.erysa.system.erysasystem.servicio.CategoriaServicio;
import com.erysa.system.erysasystem.util.PageRender;

@Controller
public class CategoriaControlador {

	@Autowired
	private CategoriaServicio categoriaServicio;

	@Autowired
	private CategoriaRepositorio categoriaRepository;

	//Se listan las categorias y sus respectivos metodos
	@GetMapping({ "/categorias" })
	public String listarCategorias(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 7);
		Page<Categoria> categorias = categoriaServicio.findAll(pageRequest);
		PageRender<Categoria> pageRender = new PageRender<>("/categorias", categorias);

		modelo.addAttribute("categorias", categorias);
		modelo.addAttribute("page", pageRender);

		return "Categories/Category";
	}

	
	//Se muestra el formulario para registrar las categorias
	@GetMapping("/categorias_form")
	public String mostrarFormularioDeRegistrarCategoria(Map<String, Object> modelo) {
		Categoria categoria = new Categoria();
		modelo.put("categoria", categoria);
		modelo.put("titulo", "Registro de categorias");
		return "Categories/Category_form";
	}

	//Aqui dentro del form una vez se retorne se guardan los datos 
	@PostMapping("/categorias_form")
	public String guardarCategoria(@Valid Categoria categoria, BindingResult result, Model modelo,
		RedirectAttributes attribute, SessionStatus status) {
		categoriaRepository.save(categoria);
		status.setComplete();
		attribute.addFlashAttribute("success", "Categoria Guardada");
		
		return "redirect:/categorias";

	}

	//Aqui esa para editar la categoria donde se retorna un form para cambiar cierto dato
	@GetMapping("/categorias_form/{id}")
	public String editarCategoria(@PathVariable(value = "id") Integer id, Map<String, Object> modelo,
			RedirectAttributes flash) {
		Categoria categoria = null;
		if (id > 0) {
			categoria = categoriaServicio.findOne(id);
			if (categoria == null) {
				flash.addFlashAttribute("error", "El ID de la categoria no existe en la base de datos");
				return "redirect:/categorias";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del producto no puede ser cero");
			return "redirect:/categorias";
		}
	
		modelo.put("categoria", categoria);
		modelo.put("titulo", "Edición de categoria");
		return "Categories/Category_form";
	}
	

}
