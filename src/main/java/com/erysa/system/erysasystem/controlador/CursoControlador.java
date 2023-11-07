package com.erysa.system.erysasystem.controlador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.erysa.system.erysasystem.modelo.Curso;
import com.erysa.system.erysasystem.servicio.CursoServicio;
import com.erysa.system.erysasystem.util.PageRender;
import com.erysa.system.erysasystem.util.reportes.CursoExporterExcel;
import com.erysa.system.erysasystem.util.reportes.CursoExporterPDF;
import com.lowagie.text.DocumentException;

@Controller
public class CursoControlador {
	
	 //Se importa de servicio para determinar el metodo segun corresponda
	@Autowired
	private CursoServicio cursoServicio;
	
	 //Aqui es para ver los detalles del producto una vez se listen mediante su id
	@GetMapping("/mas/{id_Curso}")
	public String verDetallesDelCurso(@PathVariable(value = "id_Curso") Long id_Curso, Map<String, Object> modelo,
			RedirectAttributes flash) {
			Curso curso = cursoServicio.findOne(id_Curso);
			if (curso == null) {
				flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
				return "redirect:/listar";
			}
			modelo.put("curso", curso);
			modelo.put("titulo", "Detalles del curso " + curso.getTitulo());
			return "Courses/More";
		}
	
	 //Se listan los productos y sus respectivos metodos
	@GetMapping("/cursos")
	public String listarCursos(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Curso> cursos = cursoServicio.findAll(pageRequest);
		PageRender<Curso> pageRender = new PageRender<>("/cursos", cursos);

		modelo.addAttribute("titulo", "Listado de cursos");
		modelo.addAttribute("cursos", cursos);
		modelo.addAttribute("page", pageRender);

		return "Courses/Course";
	}
	
	//Se muestra el formulario para registrar los cursos 
	@GetMapping("/formulario_curso")
	public String mostrarFormularioDeRegistrarCurso(Map<String, Object> modelo) {
		Curso curso = new Curso();
		modelo.put("curso", curso);
		modelo.put("titulo", "Registro de cursos");
		return "Courses/Form_course";
		}
	
	//Aqui dentro del form una vez se retorne se guardan los datos y se realiza una
	//condicional para la imagen la cual tiene una ruta que se crea en el local y
	//se sube en la db
	@PostMapping("/formulario_curso")
	public String guardarCurso(@RequestParam(value = "file") MultipartFile imagen, @Valid Curso curso,
			BindingResult result, Model modelo, RedirectAttributes attribute, SessionStatus status) {

		if (result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de curso");
			modelo.addAttribute("curso", curso);
			return "Courses/Form_course";
		}

		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//CoursesImages");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				curso.setMiniatura(imagen.getOriginalFilename());

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		cursoServicio.save(curso);
		status.setComplete();
		attribute.addFlashAttribute("success", "Curso Guardado");

		return "redirect:/cursos";
	}

	//	 Aqui esa para editar el producto donde se retorna un form para cambiar cierto dato incluyendo 
	@GetMapping("/formulario_curso/{id_Curso}")
	public String editarCurso(@PathVariable(value = "id_Curso") Long id_Curso, Map<String, Object> modelo,
			RedirectAttributes flash) {

		Curso curso = null;
		if (id_Curso > 0) {
			curso = cursoServicio.findOne(id_Curso);
			if (curso == null) {
				flash.addFlashAttribute("error", "El ID del curso no existe en la base de datos");
				return "redirect:/cursos";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del curso no puede ser cero");
			return "redirect:/cursos";
		}
		modelo.put("curso", curso);
		modelo.put("titulo", "Edición de curso");
		return "Courses/Form_course";
	}

	// Se elimina un registro
	@GetMapping("/eliminar_curso/{id_Curso}")
	public String eliminarCurso(@PathVariable(value = "id_Curso") Long id_Curso, RedirectAttributes flash) {
		if (id_Curso > 0) {
			cursoServicio.delete(id_Curso);
			flash.addFlashAttribute("success", "Curso eliminado con exito");
		}
		return "redirect:/cursos";
	}
		
	
	//Se exporta en formato pdf donde se selecciona el modelo a exportar y se
	//determina el nombre y fecha para el archivo
	
	//Se llama del paquete util
	@GetMapping("/exportarCursoPDF")
	public void exportarListadoDeCursosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Cursos_" + fechaActual + ".pdf";

		response.setHeader(cabecera, valor);

		List<Curso> cursos = cursoServicio.findAll();

		CursoExporterPDF exporter = new CursoExporterPDF(cursos);
		exporter.exportar(response);
	}
	
	
	 //Se exporta en formato excel donde se selecciona el modelo a exportar y se
	 //determina el nombre y fecha para el archivo
	
	 //Se llama del paquete util
	@GetMapping("/exportarCursoExcel")
	public void exportarListadoDeCursosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Cursos_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Curso> cursos = cursoServicio.findAll();

		CursoExporterExcel exporter = new CursoExporterExcel(cursos);
		exporter.exportar(response);
	}
	
	
}
