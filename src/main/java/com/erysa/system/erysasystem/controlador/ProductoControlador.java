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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.erysa.system.erysasystem.modelo.Categoria;
import com.erysa.system.erysasystem.modelo.Producto;
import com.erysa.system.erysasystem.repositorio.CategoriaRepositorio;
import com.erysa.system.erysasystem.servicio.ProductoServicio;
import com.erysa.system.erysasystem.util.PageRender;
import com.erysa.system.erysasystem.util.reportes.ProductoExporterExcel;
import com.erysa.system.erysasystem.util.reportes.ProductoExporterPDF;
import com.lowagie.text.DocumentException;

@Controller
public class ProductoControlador {

	// Se importa de servicio para determinar el metodo segun corresponda
	@Autowired
	private ProductoServicio productoServicio;

	// Se importa de respositorio para determinar la id
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	// Aqui es para ver los detalles del producto una vez se listen mediante su id
	@GetMapping("/ver/{id}")
	public String verDetallesDelProducto(@PathVariable(value = "id") Integer id, Map<String, Object> modelo,
			RedirectAttributes flash) {
		Producto producto = productoServicio.findOne(id);
		if (producto == null) {
			flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
			return "redirect:/listar";
		}
		modelo.put("producto", producto);
		modelo.put("titulo", "Detalles del producto " + producto.getNombre());
		return "Products/ver";
	}

	// Se listan los productos y sus respectivos metodos
	@GetMapping("/listar")
	public String listarProductos(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 7);
		Page<Producto> productos = productoServicio.findAll(pageRequest);
		PageRender<Producto> pageRender = new PageRender<>("/listar", productos);

		modelo.addAttribute("titulo", "Listado de productos");
		modelo.addAttribute("productos", productos);
		modelo.addAttribute("page", pageRender);

		return "Products/listar";
	}

	// Se muestra el formulario para registrar los productos y se llama la categoria
	// para el registro
	@GetMapping("/form")
	public String mostrarFormularioDeRegistrarProducto(Map<String, Object> modelo) {
		List<Categoria> listarCategorias = categoriaRepositorio.findAll();
		Producto producto = new Producto();
		modelo.put("producto", producto);
		modelo.put("titulo", "Registro de productos");
		modelo.put("listarCategorias", listarCategorias);
		return "Products/form";
	}

	// Aqui dentro del form una vez se retorne se guardan los datos y se realiza una
	// condicional para la imagen la cual tiene una ruta que se crea en el local y
	// se sube en la db
	@PostMapping("/form")
	public String guardarProducto(@RequestParam(value = "file") MultipartFile imagen, @Valid Producto producto,
			BindingResult result, Model modelo, RedirectAttributes attribute, SessionStatus status) {

		if (result.hasErrors()) {
			modelo.addAttribute("titulo", "Registro de producto");
			modelo.addAttribute("producto", producto);
			return "Products/form";
		}

		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static//ImagesSaved");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				producto.setImagen(imagen.getOriginalFilename());

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		productoServicio.save(producto);
		status.setComplete();
		attribute.addFlashAttribute("success", "Producto Guardado");

		return "redirect:/listar";
	}

	// Aqui esa para editar el producto donde se retorna un form para cambiar cierto
	// dato incluyendo la categoria
	@GetMapping("/form/{id}")
	public String editarProducto(@PathVariable(value = "id") Integer id, Map<String, Object> modelo,
			RedirectAttributes flash) {
		List<Categoria> listarCategorias = categoriaRepositorio.findAll();
		Producto producto = null;
		if (id > 0) {
			producto = productoServicio.findOne(id);
			if (producto == null) {
				flash.addFlashAttribute("error", "El ID del producto no existe en la base de datos");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del producto no puede ser cero");
			return "redirect:/listar";
		}
		modelo.put("listarCategorias", listarCategorias);
		modelo.put("producto", producto);
		modelo.put("titulo", "Edición de producto");
		return "Products/form";
	}

	// Se elimina un registro
	@GetMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {
		if (id > 0) {
			productoServicio.delete(id);
			flash.addFlashAttribute("success", "Producto eliminado con exito");
		}
		return "redirect:/listar";
	}

	// Se exporta en formato pdf donde se selecciona el modelo a exportar y se
	// determina el nombre y fecha para el archivo

	// Se llama del paquete util
	@GetMapping("/exportarPDF")
	public void exportarListadoDeProductosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Productos_" + fechaActual + ".pdf";

		response.setHeader(cabecera, valor);

		List<Producto> productos = productoServicio.findAll();

		ProductoExporterPDF exporter = new ProductoExporterPDF(productos);
		exporter.exportar(response);
	}

	// Se exporta en formato excel donde se selecciona el modelo a exportar y se
	// determina el nombre y fecha para el archivo

	// Se llama del paquete util
	@GetMapping("/exportarExcel")
	public void exportarListadoDeProductosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Productos_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Producto> productos = productoServicio.findAll();

		ProductoExporterExcel exporter = new ProductoExporterExcel(productos);
		exporter.exportar(response);
	}

}
