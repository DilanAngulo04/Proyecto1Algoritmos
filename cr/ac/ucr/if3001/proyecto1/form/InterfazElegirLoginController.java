package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfazElegirLoginController implements Initializable {

    @FXML
    private JFXButton btn_administrador;
    @FXML
    private JFXButton btn_usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ir_administrador(ActionEvent event) {        
    }

    @FXML
    private void ir_usuario(ActionEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazLoginParticipantes.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();        
    }    
}
