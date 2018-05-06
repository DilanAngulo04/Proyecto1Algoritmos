package cr.ac.ucr.if3001.proyecto1.form;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfazElegirLoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //funci'on a la imagen para ingresar al ser presionada con el mouse
    @FXML
    private void ir_administrador(MouseEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazLoginAdministrador.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }//fin m'etodo

    //funci'on a la imagen para ingresar al ser presionada con el mouse
    @FXML
    private void ir_participante(MouseEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazLoginParticipantes.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }//fin m'etodo
}//fin class
