package com.erysa.system.erysasystem.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.erysa.system.erysasystem.modelo.Orden;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrdenExporterPDF {

	private List<Orden> listaOrdenes;

	public OrdenExporterPDF(List<Orden> listaOrdenes) {
		super();
		this.listaOrdenes = listaOrdenes;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.MAGENTA);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Número de orden", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Nombre Usuario", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellido Usuario", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Fecha creación", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Fecha recibida", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Total", fuente));
		tabla.addCell(celda);
		
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Orden orden : listaOrdenes) {
			tabla.addCell(String.valueOf(orden.getId()));
			tabla.addCell(String.valueOf(orden.getNumero()));
			tabla.addCell(String.valueOf(orden.getUsuario().getNombre()));
			tabla.addCell(String.valueOf(orden.getUsuario().getApellido()));
			tabla.addCell(String.valueOf(orden.getFechaCreacion()));
			tabla.addCell(String.valueOf(orden.getFechaRecibida()));
			tabla.addCell(String.valueOf(orden.getTotal()));
			
		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
		fuente.setColor(Color.MAGENTA);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de ordenes", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(7);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 2.5f, 2.3f, 3.0f, 2.9f, 3.5f, 6f});
		tabla.setWidthPercentage(110);

		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);

		documento.add(tabla);
		documento.close();
	}

}
