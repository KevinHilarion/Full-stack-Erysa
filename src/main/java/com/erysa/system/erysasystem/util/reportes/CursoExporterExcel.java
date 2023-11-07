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
import com.erysa.system.erysasystem.modelo.Curso;


public class CursoExporterExcel {
	
	private XSSFWorkbook libro;
	private XSSFSheet hoja;
	
	private List<Curso> listaCursos;

	public CursoExporterExcel(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Cursos");
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
		estilo.setFillForegroundColor(IndexedColors.ORCHID.getIndex());
		estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		estilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		estilo.setAlignment(HorizontalAlignment.CENTER);
		estilo.setVerticalAlignment(VerticalAlignment.CENTER);

		Cell celda = fila.createCell(0);
		celda.setCellValue("ID");
		celda.setCellStyle(estilo);

		celda = fila.createCell(1);
		celda.setCellValue("Titulo");
		celda.setCellStyle(estilo);

		celda = fila.createCell(2);
		celda.setCellValue("Miniatura");
		celda.setCellStyle(estilo);

		celda = fila.createCell(3);
		celda.setCellValue("Descripcion");
		celda.setCellStyle(estilo);

		celda = fila.createCell(4);
		celda.setCellValue("Precio");
		celda.setCellStyle(estilo);

		celda = fila.createCell(5);
		celda.setCellValue("Duracion");
		celda.setCellStyle(estilo);

		celda = fila.createCell(6);
		celda.setCellValue("Cantidad_videos");
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

		for (Curso curso : listaCursos) {
			Row fila = hoja.createRow(nueroFilas++);

			Cell celda = fila.createCell(0);
			celda.setCellValue(curso.getId_Curso());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);

			celda = fila.createCell(1);
			celda.setCellValue(curso.getTitulo());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);

			celda = fila.createCell(2);
			celda.setCellValue(curso.getMiniatura());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);

			celda = fila.createCell(3);
			celda.setCellValue(curso.getDescripcion());
			hoja.autoSizeColumn(3);
			celda.setCellStyle(estilo);

			celda = fila.createCell(4);
			celda.setCellValue(curso.getPrecio());
			hoja.autoSizeColumn(4);
			celda.setCellStyle(estilo);

			celda = fila.createCell(5);
			celda.setCellValue(curso.getDuracion());
			hoja.autoSizeColumn(5);
			celda.setCellStyle(estilo);

			celda = fila.createCell(6);
			celda.setCellValue(curso.getCantidad_videos());
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