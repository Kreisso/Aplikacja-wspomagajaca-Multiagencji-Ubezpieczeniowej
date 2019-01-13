package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.Policy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class PDFWriter {
    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 30, Font.NORMAL);
    Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
    Font boldSmallFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    public PDFWriter(String name, List policies){
        createPDF(name, policies);
    }
    public void createPDF(String name, java.util.List<Policy> policies){
        Document document = new Document();
        NumberFormat formatter = new DecimalFormat("###.00");
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("polisa-" + name + ".pdf"));
            document.open();

            Image companyImage = Image.getInstance("assets/img/"+policies.get(0).getOffer().getCompanyName() + ".png");
            companyImage.setAbsolutePosition(430f, 720f);
            companyImage.scaleAbsolute(150, 100);
            document.add(companyImage);


            Paragraph title = new Paragraph("Polisa nr " + policies.get(0).getId(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph enter = new Paragraph("\n");
            document.add(enter);
            document.add(enter);
            document.add(enter);

            Paragraph company = new Paragraph("Towarzystwo ubezpieczeniowe: " + policies.get(0).getOffer().getCompanyName(), boldFont);
            document.add(company);

            Paragraph client = new Paragraph("Ubezpieczony: "+ policies.get(0).getClient().getFirstName() + " " + policies.get(0).getClient().getLastName(), boldFont);
            document.add(client);

            Paragraph agent = new Paragraph("Id agenta ubezpieczeniowego: " + policies.get(0).getAgentId(), boldFont);
            document.add(agent);

            Paragraph beginningAndEnding = new Paragraph("Okres ubezpieczenia:  " + policies.get(0).getBeginning() + " - " + policies.get(0).getEnding(), boldFont);
            document.add(beginningAndEnding);

            Paragraph policyType = new Paragraph("Rodzaj ubezpieczenia:  " + policies.get(0).getOffer().getType(), boldFont);
            document.add(policyType);

            Paragraph description = new Paragraph("Opis:  " + policies.get(0).getOffer().getDescription(), boldFont);
            document.add(description);

            Paragraph price = new Paragraph("Cena:  " + formatter.format(policies.get(0).getOffer().getPrice()) + " zl", boldFont);
            document.add(price);

            document.add(enter);
            document.add(enter);
            document.add(enter);
            document.add(enter);
            document.add(enter);
            document.add(enter);
            document.add(enter);

            Paragraph dots = new Paragraph(
                    "............................................                                                               ............................................", boldSmallFont);
            dots.setAlignment(Element.ALIGN_CENTER);
            document.add(dots);

            Paragraph signatures = new Paragraph(
                    "       Podpis agenta                                                                            Podpis ubezpieczającego", boldSmallFont);
            signatures.setAlignment(Element.ALIGN_CENTER);
            document.add(signatures);

            document.close();
            writer.close();

        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
