package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InterfazRegistroParticipantesController implements Initializable {

    //Clases necesarias
    private ControlArchivos controlA;
    private ListaEnlazada listE = new ListaEnlazada();

    public InterfazRegistroParticipantesController() {
        this.controlA = new ControlArchivos();
    }    
    
    @FXML
    private AnchorPane anp_root;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }//fin initialize

    //Funci'on para serializar el registro de participantes
    @FXML
    private void registrar(ActionEvent event) throws IOException, ClassNotFoundException {

        String nombreComplet = tfd_nombreCompletp.getText();
        String correo = tfd_correo.getText();
        String nombreUsuario = tfd_nombreUsuario.getText();
        String contraseña = pwf_contraseña.getText();
        String confirmarContraseña = pwf_confirmarContraseña.getText();
        Participantes participantes = new Participantes(nombreComplet, correo, nombreUsuario, contraseña);
        controlA.setNombre("Participantes.dat");
        controlA.escribir(participantes);

    }//fin m'etodo

}//fin class
