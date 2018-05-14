package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.object.Administrador;
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
import javax.swing.JOptionPane;

public class InterfazRegistroAdministradorController implements Initializable {

    //Clases necesarias
    private ControlArchivos controlA;
    private ListaEnlazada listE = new ListaEnlazada();
    private Administrador admPrueba = new Administrador();

    public InterfazRegistroAdministradorController() {
        this.controlA = new ControlArchivos();
    }

    //Nodos
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
    @FXML
    private AnchorPane anp_root;
    @FXML
    private PasswordField pwf_claveAdmi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }//fin initialize

    @FXML
    private void registrar(ActionEvent event) throws IOException, ClassNotFoundException {

        String nombreComplet = tfd_nombreCompletp.getText().trim();
        String correo = tfd_correo.getText().trim();
        String nombreUsuario = tfd_nombreUsuario.getText().trim();
        String contraseña = pwf_contraseña.getText();
        String confirmarContraseña = pwf_confirmarContraseña.getText();

        if (nombreComplet.length() != 0 || correo.length() != 0
                || nombreUsuario.length() != 0 || contraseña.length() != 0
                || confirmarContraseña.length() != 0) {

                if (contraseña.equals(confirmarContraseña)) {
                    
//                    Administrador administrador = new Administrador(nombreComplet, correo, nombreUsuario, contraseña);
                    controlA.setNombre("Administrador.dat");
//                    controlA.escribir(administrador);
                    
                } else {                    
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");                    
                }

        } else {            
            JOptionPane.showMessageDialog(null, "Debe llenar todos los espacios");            
        }

    }//fin action

}//fin class
