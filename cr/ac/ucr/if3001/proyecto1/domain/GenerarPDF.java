package cr.ac.ucr.if3001.proyecto1.domain;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GenerarPDF {
    //Atributos
    private String ruta;
      
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    //M'etodo para crear un archivo .pdf
    public boolean generar(String contenido) throws DocumentException{
        
        try{
        FileOutputStream archivo = new FileOutputStream(ruta + ".pdf");
        Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            doc.add(new Paragraph(contenido));
            doc.close();   
            return true;
        }catch(FileNotFoundException ex){
            System.out.println("...Error " + ex);
        }
        return false;
    }
}//fin class
