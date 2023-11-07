package com.erysa.system.erysasystem.controlador;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erysa.system.erysasystem.modelo.Curso;
import com.erysa.system.erysasystem.modelo.Producto;
import com.erysa.system.erysasystem.servicio.CursoServicio;
import com.erysa.system.erysasystem.servicio.ProductoServicio;

@Controller
public class HomeControler {

	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
	private CursoServicio cursoServicio;
	
	//Productos
	@GetMapping({"index", "/", ""})
	public String home(Model model) {

		List<Producto> productos = productoServicio.findAll();
		model.addAttribute("productos", productos);

		return "index";
	}

	@GetMapping("productoindex/{id}")
	public String verDetallesDelProducto(@PathVariable(value = "id") Integer id, Map<String, Object> modelo,
			RedirectAttributes flash) {
		Producto producto = productoServicio.findOne(id);
		if (producto == null) {
			flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
			return "redirect:/index";
		}
		modelo.put("producto", producto);
		modelo.put("titulo", "Detalles del producto " + producto.getNombre());
		return "Products/productohome";
	}
	
	//Cursos
	@GetMapping({"index-curso"})
	public String cursos (Model model) {

		List<Curso> cursos = cursoServicio.findAll();
		model.addAttribute("cursos", cursos);

		return "Courses/IndexCourses";
	}

	@GetMapping("cursoindex/{id_curso}")
	public String verDetallesDelCurso (@PathVariable(value = "id_curso") Long id_curso, Map<String, Object> modelo,
			RedirectAttributes flash) { 
		Curso curso = cursoServicio.findOne(id_curso);
		if (curso == null) {
			flash.addFlashAttribute("error", "El curso no existe en la base de datos");
			return "redirect:/index";
		}
		modelo.put("curso", curso);
		modelo.put("titulo", "Detalles del curso " + curso.getTitulo());
		return "Courses/CourseHome";
	}

}
