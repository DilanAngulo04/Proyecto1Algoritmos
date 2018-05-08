package cr.ac.ucr.if3001.proyecto1.form;

import com.itextpdf.text.DocumentException;
import cr.ac.ucr.if3001.proyecto1.domain.ComprobarConexion;
import cr.ac.ucr.if3001.proyecto1.domain.Imprimir;
import java.awt.Color;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jxl.write.WriteException;

public class Proyecto1Algoritmos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazSubModuloProductos.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazPrincipalAdministrador.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(root);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws WriteException, DocumentException, IOException {
//        launch(args);
        Imprimir im = new Imprimir();
        im.imprimir("Administrador.dat", "src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\");
    }
}
