package cr.ac.ucr.if3001.proyecto1.domain;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
 
public class Imprimir {
 
    public void imprimir(String nombre, String ruta) throws IOException {
 
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(ruta+nombre);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
 
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
 
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
 
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
 
 
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }
 
        inputStream.close();
    }
}
