package cr.ac.ucr.if3001.proyecto1.form;

import com.itextpdf.text.DocumentException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jxl.write.WriteException;

public class Proyecto1Algoritmos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws WriteException, DocumentException {
        launch(args);
        //prueba excel
//        GenerarExcel e = new GenerarExcel();
//        String matriz[][] = new String[5][5];
//        matriz [0][0] = "Dilan";
//        matriz [0][1] = "Angulo Ruiz";
//        matriz [0][2] = "dilan0400ar@gmail.com";
//        matriz [0][3] = "1234abc.";
//        matriz [0][4] = "dilanExxito";
//        
//        String ruta = "C:\\Users\\dilan_000\\Desktop\\Proyecto1Algoritmos\\src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\registros.xls";
//        String hoja = "Reporte";
//        
//        e.generar(matriz, ruta, hoja);

        //prueba pdf
//        String contenido = "Prueba de creacion pdf";
//        String ruta = "C:\\Users\\dilan_000\\Desktop\\Proyecto1Algoritmos\\src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\registros";
//        GenerarPDF pdf = new GenerarPDF(contenido, ruta);
//        pdf.generar();
    }
}
