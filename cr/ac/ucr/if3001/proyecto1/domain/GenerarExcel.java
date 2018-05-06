package cr.ac.ucr.if3001.proyecto1.domain;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class GenerarExcel {

    //M'etodo para crear un archivo .xls (Excel)
    public void generar(String[][] matrizDatos, String ruta, String nombreHoja) throws WriteException {

        try {
            /*Se utiliza la biblioteca -jxl.jar- para declarar los objetos 
            que daran la funci'on de crear un archivo Excel*/
            WorkbookSettings configuracion = new WorkbookSettings();
            configuracion.setEncoding("ISO-8859-1");
            WritableWorkbook ww = Workbook.createWorkbook(new File(ruta), configuracion);
            WritableSheet ws = ww.createSheet(nombreHoja, 0);
            WritableFont font = new WritableFont(WritableFont.COURIER, 16, WritableFont.NO_BOLD);
            WritableCellFormat cFormat = new WritableCellFormat(font);
            
            //Se recorre la matriz pra poder llenar cada celda y fila en Excel
            for (int c = 0; c < matrizDatos.length; c++) {
                for (int f = 0; f < matrizDatos[c].length; f++) {
                    ws.addCell(new jxl.write.Label(f, c, matrizDatos[c][f], cFormat));
                }
            }
            //Escribimos en el archivo
            ww.write();
            //Se cierra el archivo
            ww.close();

        } catch (IOException | WriteException ex) {
            Logger.getLogger(GenerarExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//fin m'etodo
}//fin class
