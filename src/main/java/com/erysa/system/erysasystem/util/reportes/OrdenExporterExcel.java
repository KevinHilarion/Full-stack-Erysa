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
import com.erysa.system.erysasystem.modelo.Orden;


public class OrdenExporterExcel {

	private XSSFWorkbook libro;
	private XSSFSheet hoja;

	private List<Orden> listaOrdenes;

	public OrdenExporterExcel(List<Orden> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
		libro = new XSSFWorkbook();
		hoja = libro.createSheet("Ordenes");
		
	}
	

	private void escribirCabeceraDeTabla() {
		Row fila = hoja.createRow(0);

		CellStyle estilo = libro.createCellStyle();
		XSSFFont fuente = libro.createFont();
	
		fuente.setBold(true);
		fuente.setFontHeight(11);
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
		celda.setCellValue("# Orden");
		celda.setCellStyle(estilo);

		celda = fila.createCell(2);
		celda.setCellValue("U.Nombre");
		celda.setCellStyle(estilo);

		celda = fila.createCell(3);
		celda.setCellValue("U.Apellido");
		celda.setCellStyle(estilo);

		celda = fila.createCell(4);
		celda.setCellValue("Creación");
		celda.setCellStyle(estilo);

		celda = fila.createCell(5);
		celda.setCellValue("Recibida");
		celda.setCellStyle(estilo);

		celda = fila.createCell(6);
		celda.setCellValue("Total");
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
		

		for (Orden orden : listaOrdenes) {
			Row fila = hoja.createRow(nueroFilas++);

			Cell celda = fila.createCell(0);
			celda.setCellValue(orden.getId());
			hoja.autoSizeColumn(0);
			celda.setCellStyle(estilo);

			celda = fila.createCell(1);
			celda.setCellValue(orden.getNumero());
			hoja.autoSizeColumn(1);
			celda.setCellStyle(estilo);

			celda = fila.createCell(2);
			celda.setCellValue(orden.getUsuario().getNombre());
			hoja.autoSizeColumn(2);
			celda.setCellStyle(estilo);

			celda = fila.createCell(3);
			celda.setCellValue(orden.getUsuario().getApellido());
			hoja.autoSizeColumn(3);
			celda.setCellStyle(estilo);

			celda = fila.createCell(4);
			celda.setCellValue(orden.getFechaCreacion());
			hoja.autoSizeColumn(4);
			celda.setCellStyle(estilo);

			celda = fila.createCell(5);
			celda.setCellValue(orden.getFechaRecibida());
			hoja.autoSizeColumn(5);
			celda.setCellStyle(estilo);

			celda = fila.createCell(6);
			celda.setCellValue(orden.getTotal());
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
