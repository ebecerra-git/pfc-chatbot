package es.ebecerra.chatbot.view.pdf;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import es.ebecerra.chatbot.model.Mensaje;

@Component("chatbot")
public class ConversacionPDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Mensaje> mensajes = (ArrayList) model.get("conversacion");
		List<String[]> mensajesStr = mensajes.stream().map(mensaje -> {
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
			if(mensaje.getMensajeBot()) {
				return new String[]{"Consejero: "+mensaje.getTexto(), dateFormat.format(mensaje.getFechaEnvio())};
			} else {
				return new String[]{"Tu: "+mensaje.getTexto(), dateFormat.format(mensaje.getFechaEnvio())};
			}
		}).collect(Collectors.toList());
		PdfPCell cell = new PdfPCell(new Phrase("Conversación"));
		cell.setBackgroundColor(new Color(160, 230, 170));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setPadding(8f);
		PdfPTable titulo = new PdfPTable(1);
		titulo.addCell(cell);
		PdfPTable tabla = new PdfPTable(2);
		tabla.setWidths(new float [] {2f, 1f});
		cell = new PdfPCell(new Phrase("Mensajes"));
		cell.setPadding(6f);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setBackgroundColor(new Color(225, 255, 210));
		tabla.addCell(cell);
		cell = new PdfPCell(new Phrase("Fecha y hora"));
		cell.setPadding(6f);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
		cell.setBackgroundColor(new Color(225, 255, 210));
		tabla.addCell(cell);
		for(String[] mensaje : mensajesStr) {
			cell = new PdfPCell(new Phrase(mensaje[0]));
			cell.setPadding(4f);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tabla.addCell(cell);
			cell = new PdfPCell(new Phrase(mensaje[1]));
			cell.setPadding(4f);
			cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			tabla.addCell(cell);
		}
		document.add(titulo);
		document.add(tabla);
	}

}
