package com.erysa.system.erysasystem.util.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.erysa.system.erysasystem.modelo.Usuario;
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



public class UsuarioExporterPDF {
	
	private List<Usuario> listaUsuarios;
	
	public UsuarioExporterPDF(List<Usuario> listaUsuarios) {
		super();
		this.listaUsuarios = listaUsuarios;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.MAGENTA);
		celda.setPadding(5);

		Font fuente = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Nombre", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Apellido", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Celular", fuente));
		tabla.addCell(celda);

		celda.setPhrase(new Phrase("Email", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Username", fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Dirección", fuente));
		tabla.addCell(celda);
	}
	
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Usuario usuario : listaUsuarios) {
			tabla.addCell(String.valueOf(usuario.getId_Usuario()));
			tabla.addCell(String.valueOf(usuario.getNombre()));
			tabla.addCell(String.valueOf(usuario.getApellido()));
			tabla.addCell(String.valueOf(usuario.getNumero_celular()));
			tabla.addCell(String.valueOf(usuario.getEmail()));
			tabla.addCell(String.valueOf(usuario.getUsername()));
			tabla.addCell(String.valueOf(usuario.getDireccion()));
		}
	}
	
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());

		documento.open();

		Font fuente = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
		fuente.setColor(Color.MAGENTA);
		fuente.setSize(18);

		Paragraph titulo = new Paragraph("Lista de usuarios", fuente);
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
