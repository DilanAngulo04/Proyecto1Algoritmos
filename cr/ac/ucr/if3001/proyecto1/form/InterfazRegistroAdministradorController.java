package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InterfazRegistroAdministradorController implements Initializable {

    @FXML
    private TextField tfd_nombreCompletp;
    @FXML
    private TextField tfd_correo;
    @FXML
    private TextField tfd_nombreUsuario;
    @FXML
    private PasswordField pwf_contraseña;
    @FXML
    private PasswordField pwf_confirmarContraseña;
    @FXML
    private JFXButton registrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrar(ActionEvent event) {
    }
    
}
