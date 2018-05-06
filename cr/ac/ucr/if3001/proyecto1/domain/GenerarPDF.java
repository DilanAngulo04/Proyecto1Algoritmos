package cr.ac.ucr.if3001.proyecto1.domain;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GenerarPDF {
    private String contenido;
    private String ruta;
    
    public GenerarPDF(String contenido, String ruta){
       this.contenido = contenido;
       this.ruta = ruta;
    }    
    
    public void generar() throws DocumentException{
        try{
        FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
        Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            doc.add(new Paragraph(contenido));
            doc.close();                       
        }catch(FileNotFoundException ex){
            System.out.println("...Error " + ex);
        }
    }
}//fin class
