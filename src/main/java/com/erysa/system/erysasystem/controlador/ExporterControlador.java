package com.erysa.system.erysasystem.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.erysa.system.erysasystem.modelo.Orden;
import com.erysa.system.erysasystem.modelo.Usuario;
import com.erysa.system.erysasystem.servicio.IFacturaService;
import com.erysa.system.erysasystem.servicio.IOrdenService;
import com.erysa.system.erysasystem.util.reportes.OrdenExporterExcel;
import com.erysa.system.erysasystem.util.reportes.OrdenExporterPDF;
import com.erysa.system.erysasystem.util.reportes.UsuarioExporterExcel;
import com.erysa.system.erysasystem.util.reportes.UsuarioExporterPDF;
import com.lowagie.text.DocumentException;

@Controller
public class ExporterControlador {

	@Autowired
	private IFacturaService usuarioServicio;
	
	@Autowired
	private IOrdenService ordenServicio;
	
	//USUARIOS
	// Se llama del paquete util
	@GetMapping("/exportarUsuariosPDF")
	public void exportarListadoDeUsuariosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Usuarios_" + fechaActual + ".pdf";

		response.setHeader(cabecera, valor);

		List<Usuario> usuarios = usuarioServicio.findAll();

		UsuarioExporterPDF exporter = new UsuarioExporterPDF(usuarios);
		exporter.exportar(response);
	}

	// Se exporta en formato excel donde se selecciona el modelo a exportar y se
	// determina el nombre y fecha para el archivo

	// Se llama del paquete util
	@GetMapping("/exportarUsuariosExcel")
	public void exportarListadoDeUsuariosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Productos_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Usuario> usuarios = usuarioServicio.findAll();

		UsuarioExporterExcel exporter = new UsuarioExporterExcel(usuarios);
		exporter.exportar(response);
	}
	//USUARIOS
	
	//ORDENES
	// Se llama del paquete util
		@GetMapping("/exportarOrdenesPDF")
		public void exportarListadoDeOrdenesEnPDF(HttpServletResponse response) throws DocumentException, IOException {
			response.setContentType("application/pdf");

			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String fechaActual = dateFormatter.format(new Date());

			String cabecera = "Content-Disposition";
			String valor = "attachment; filename=Ordenes_" + fechaActual + ".pdf";

			response.setHeader(cabecera, valor);

			List<Orden> ordenes = ordenServicio.findAll();

			OrdenExporterPDF exporter = new OrdenExporterPDF(ordenes);
			exporter.exportar(response);
		}

		// Se exporta en formato excel donde se selecciona el modelo a exportar y se
		// determina el nombre y fecha para el archivo

		// Se llama del paquete util
		@GetMapping("/exportarOrdenesExcel")
		public void exportarListadoDeOrdenesEnExcel(HttpServletResponse response) throws DocumentException, IOException {
			response.setContentType("application/octet-stream");

			DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String fechaActual = dateFormatter.format(new Date());

			String cabecera = "Content-Disposition";
			String valor = "attachment; filename=Ordenes_" + fechaActual + ".xlsx";

			response.setHeader(cabecera, valor);

			List<Orden> ordenes = ordenServicio.findAll();

			OrdenExporterExcel exporter = new OrdenExporterExcel(ordenes);
			exporter.exportar(response);
		}
	//ORDENES
}
