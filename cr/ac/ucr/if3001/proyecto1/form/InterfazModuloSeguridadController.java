package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Administrador;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InterfazModuloSeguridadController implements Initializable {

    //Instancias
    String nombreUsuario;
    String contraseña;
    String nombreCompleto;
    String correo;
    String nombreU;
    String contrasena;
    String confirmarContraseña;
    String numeroTelefono;
    private ListaEnlazada listE = new ListaEnlazada();
    private ControlArchivos controlA = new ControlArchivos();

    @FXML
    private AnchorPane anp_root;
    @FXML
    private TextField tfd_nombreCompleto;
    @FXML
    private TextField tfd_nombreUsuario;
    @FXML
    private TextField tfd_correo;
    @FXML
    private PasswordField pwf_contraseña;
    @FXML
    private PasswordField pwf_confContraseña;
    @FXML
    private Label lbl_errorNUsuario;
    @FXML
    private Label lbl_errorCorreo;
    @FXML
    private Label lbl_errorContraseña;
    @FXML
    private JFXButton btn_registrar;
    @FXML
    private TextField tfd_numeroTelefono;
    @FXML
    private Label lbl_errorNum;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }

    @FXML
    private void resgistrarInfo(ActionEvent event) throws ListaException, IOException, ClassNotFoundException {
        //controles para registrar datos ingresados
        nombreCompleto = tfd_nombreCompleto.getText();
        correo = tfd_correo.getText();
        nombreU = tfd_nombreUsuario.getText();
        contrasena = pwf_contraseña.getText();
        confirmarContraseña = pwf_confContraseña.getText();
        numeroTelefono = tfd_numeroTelefono.getText();

        if (nombreCompleto.length() != 0 || correo.length() != 0 || nombreU.length() != 0
                || contrasena.length() != 0 || confirmarContraseña.length() != 0 || (numeroTelefono + "").length() != 0) {

            if (!verificar()) {
                if (contrasena.equals(confirmarContraseña)) {
                    try {
                        Administrador administrador = new Administrador(nombreCompleto, correo, nombreU, contrasena,
                                Integer.parseInt(numeroTelefono));
                        controlA.setNombre("Administrador.dat");
                        controlA.escribir(administrador);
                    } catch (NumberFormatException ex) {
                        lbl_errorNum.setVisible(true);
                        lbl_errorNum.setText("Ingresar un número válido");
                    }
                } else {
                    lbl_errorContraseña.setVisible(true);
                    lbl_errorContraseña.setText("Las contraseñas no coinciden");
                }
            } else {
                lbl_errorNUsuario.setVisible(true);
                lbl_errorNUsuario.setText("Nombre de usuario ya registrado");
            }
        } else {
            lbl_errorContraseña.setVisible(true);
            lbl_errorContraseña.setText("Hay campos sin llenar");
        }

    }

    public boolean verificar() throws ListaException, IOException, ClassNotFoundException {
        Object administrador = new Administrador();
        controlA.setNombre("Administrador.dat");
        listE = controlA.cargarLista();

        if (!listE.isEmpty()) {

            for (int i = 1; i <= listE.getSize(); i++) {
                administrador = listE.getNodo(i).elemento;
                Administrador p = (Administrador) administrador;

                if (p.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && p.getContraseña().equals(contraseña)) {
                    return true;
                } else if (p.getNombreUsuario().equalsIgnoreCase(nombreU)) {
                    return true;
                }
            }
        }
        return false;
    }//fin verificar

    public void invisible() {
        lbl_errorContraseña.setVisible(false);
        lbl_errorNUsuario.setVisible(false);
        lbl_errorNum.setVisible(false);
    }//fin invisible

    @FXML
    private void tfd_nombreCompleto(MouseEvent event) {
        invisible();
    }

    @FXML
    private void tfd_nombreUsuario(MouseEvent event) {
        invisible();
    }

    @FXML
    private void tfd_correo(MouseEvent event) {
        invisible();
    }

    @FXML
    private void pwf_contraseña(MouseEvent event) {
        invisible();
    }

    @FXML
    private void pwf_confContraseña(MouseEvent event) {
        invisible();
    }

    @FXML
    private void tfd_numeroTelefono(MouseEvent event) {
        invisible();
    }

}//fin class
