package view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.almin.country.entity.Country;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CountryDataPdfExport extends AbstractPdfView {

	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		Font headerFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD, Color.magenta);
		HeaderFooter header = new HeaderFooter(new Phrase("All Country PDF View", headerFont), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		Font dateFont = new Font(Font.HELVETICA, 12, Font.NORMAL, Color.BLUE);

		HeaderFooter footer = new HeaderFooter(new Phrase("PDF Export Executed On : " + new Date(), dateFont), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// download PDF with a given filename
		response.addHeader("Content-Disposition", "attachment;filename=Country.pdf");

		// read data from controller
		List<Country> list = (List<Country>) model.get("list");

		// create element
		Font titleFont = new Font(Font.TIMES_ROMAN, 24, Font.BOLD, Color.blue);
		Paragraph title = new Paragraph("ALL COUNTRY DATA", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(20.0f);
		title.setSpacingAfter(25.0f);
		// add to document
		document.add(title);

		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.blue);
		PdfPTable table = new PdfPTable(3);// no.of columns
		table.addCell(new Phrase("ID", tableHead));
		table.addCell(new Phrase("COUNTRY", tableHead));
		table.addCell(new Phrase("CAPITAL", tableHead));
		// table.addCell(new Phrase("AMOUNT",tableHead));

		for (Country country : list) {
			table.addCell(country.getId().toString());
			table.addCell(country.getName());
			table.addCell(country.getCapital());

		}
		document.add(table);
	}

}
