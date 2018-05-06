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
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazPrincipalAdministrador.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws WriteException, DocumentException {
        launch(args);
    }
}
