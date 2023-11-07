package com.erysa.system.erysasystem.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.erysa.system.erysasystem.modelo.Curso;
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



public class CursoExporterPDF {
	
	private List<Curso> listaCursos;
	
	public CursoExporterPDF(List<Curso> listaCursos) {
		super();
		this.listaCursos = listaCursos;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.MAGENTA);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Titulo", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Miniatura", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Descripcion", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Precio", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Duracion", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Cantidad de videos", fuente));
		tabla.addCell(celda);
	}
	
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Curso curso : listaCursos) {
			tabla.addCell(String.valueOf(curso.getId_Curso()));
			tabla.addCell(String.valueOf(curso.getTitulo()));
			tabla.addCell(String.valueOf(curso.getMiniatura()));
			tabla.addCell(String.valueOf(curso.getDescripcion()));
			tabla.addCell(String.valueOf(curso.getPrecio()));
			tabla.addCell(String.valueOf(curso.getDuracion()));
			tabla.addCell(String.valueOf(curso.getCantidad_videos()));
		}
	}
	
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
		fuente.setColor(Color.MAGENTA);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de cursos", fuente);
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
