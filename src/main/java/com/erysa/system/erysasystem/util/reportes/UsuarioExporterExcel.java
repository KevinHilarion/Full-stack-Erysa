package com.erysa.system.erysasystem.util.reportes;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.erysa.system.erysasystem.modelo.Usuario;

public class UsuarioExporterExcel {

	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Usuario> listaUsuarios;

	public UsuarioExporterExcel(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Usuarios");
		
	}
	

	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);

		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
	
		fuente.setBold(true);
		fuente.setFontHeight(14);
		fuente.setFontName("Script MT Bold");
		fuente.setColor(IndexedColors.WHITE.getIndex());
		estilo.setFont(fuente);
		estilo.setFillForegroundColor(IndexedColors.PINK1.getIndex());
		estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		estilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estilo.setAlignment(HorizontalAlignment.CENTER);
		estilo.setVerticalAlignment(VerticalAlignment.CENTER);
		
		Cell celda = fila.createCell(0);
		celda.setCellValue("ID");
		celda.setCellStyle(estilo);

		celda = fila.createCell(1);
		celda.setCellValue("Nombre");
		celda.setCellStyle(estilo);

		celda = fila.createCell(2);
		celda.setCellValue("Apellido");
		celda.setCellStyle(estilo);

		celda = fila.createCell(3);
		celda.setCellValue("Celular");
		celda.setCellStyle(estilo);

		celda = fila.createCell(4);
		celda.setCellValue("Email");
		celda.setCellStyle(estilo);

		celda = fila.createCell(5);
		celda.setCellValue("Username");
		celda.setCellStyle(estilo);

		celda = fila.createCell(6);
		celda.setCellValue("Dirección");
		celda.setCellStyle(estilo);
	}

	private void escribirDatosDeLaTabla() {
		int nueroFilas = 1;

		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
		fuente.setFontHeight(14);
		estilo.setFont(fuente);
		
		fuente.setFontHeight(11);
		fuente.setFontName("Arial Narrow");
		estilo.setFont(fuente);
		estilo.setAlignment(HorizontalAlignment.CENTER);
		estilo.setVerticalAlignment(VerticalAlignment.CENTER);
		

		for (Usuario usuario : listaUsuarios) {
			Row fila = hoja.createRow(nueroFilas++);

			Cell celda = fila.createCell(0);
			celda.setCellValue(usuario.getId_Usuario());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);

			celda = fila.createCell(1);
			celda.setCellValue(usuario.getNombre());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);

			celda = fila.createCell(2);
			celda.setCellValue(usuario.getApellido());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);

			celda = fila.createCell(3);
			celda.setCellValue(usuario.getNumero_celular());
			hoja.autoSizeColumn(3);
			celda.setCellStyle(estilo);

			celda = fila.createCell(4);
			celda.setCellValue(usuario.getEmail());
			hoja.autoSizeColumn(4);
			celda.setCellStyle(estilo);

			celda = fila.createCell(5);
			celda.setCellValue(usuario.getUsername());
			hoja.autoSizeColumn(5);
			celda.setCellStyle(estilo);

			celda = fila.createCell(6);
			celda.setCellValue(usuario.getDireccion());
			hoja.autoSizeColumn(6);
			celda.setCellStyle(estilo);

		}
	}

	public void exportar(HttpServletResponse response) throws IOException {
		escribirCabeceraDeTabla();
		escribirDatosDeLaTabla();

		ServletOutputStream outPutStream = response.getOutputStream();
		libro.write(outPutStream);

		libro.close();
		outPutStream.close();
	}

}
