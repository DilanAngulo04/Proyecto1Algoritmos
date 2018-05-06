package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InterfazRegistroParticipantesController implements Initializable {

    private ControlArchivos controlA;
    private ListaEnlazada listE = new ListaEnlazada();
    

    public InterfazRegistroParticipantesController() {
        this.controlA = new ControlArchivos();
    }    

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
        // TODO
    }  

    @FXML
    private void registrar(ActionEvent event) {
        
        String nombreComplet = tfd_nombreCompletp.getText();
        String correo = tfd_correo.getText();
        String nombreUsuario = tfd_nombreUsuario.getText();
        String contraseña = pwf_contraseña.getText();
        String confirmarContraseña = pwf_confirmarContraseña.getText();
        Participantes participantes = new Participantes(nombreComplet, correo, nombreUsuario, contraseña, confirmarContraseña);
        controlA.setNombre("Participantes.dat");
        controlA.escribir(participantes);        
        
    }
    public void mostrar(){
                        
        listE.insertar(controlA.cargar(this));
        System.out.println(listE.toString());
        
    }
}
