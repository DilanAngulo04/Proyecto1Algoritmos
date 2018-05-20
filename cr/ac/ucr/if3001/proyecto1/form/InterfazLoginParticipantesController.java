package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InterfazLoginParticipantesController implements Initializable {

    //instancias necesarias
    private ListaEnlazada listE = new ListaEnlazada();
    private ControlArchivos controlA = new ControlArchivos();
    String nombreUsuario;
    String contraseña;
    String nombreCompleto;
    String correo;
    String nombreU;
    String contrasena;
    String confirmarContraseña;
    String numeroTelefono;

    @FXML
    private JFXButton btn_registrate;
    @FXML
    private AnchorPane anp_root;
    @FXML
    private JFXTextField tfd_nombreUsuario;
    @FXML
    private JFXPasswordField pwf_contraseña;
    @FXML
    private AnchorPane anp_registrar;
    @FXML
    private JFXTextField tfd_correo;
    @FXML
    private JFXPasswordField pwf_confContraseña;
    @FXML
    private JFXTextField tfd_numeroTelefono;
    @FXML
    private AnchorPane anp_ingresar;
    @FXML
    private JFXButton btn_registrar;
    @FXML
    private JFXButton btn_back;
    @FXML
    private Label lbl_error;
    @FXML
    private JFXTextField tfd_nombreC;
    @FXML
    private JFXTextField tfd_nombreUs;
    @FXML
    private JFXPasswordField pwf_contrasena;
    @FXML
    private Label lbl_errorNUsuario;
    @FXML
    private Label lbl_errorContraseña;
    @FXML
    private Label lbl_errorNumero;
    @FXML
    private ImageView btn_salir;
    @FXML
    private Label lbl_errorCorreo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }//fin initialize

    @FXML
    private void btn_ingresarInfo(ActionEvent event) throws IOException, ListaException, ClassNotFoundException {
        nombreUsuario = tfd_nombreUsuario.getText().trim();
        contraseña = pwf_contraseña.getText().trim();

        if (nombreUsuario.length() != 0 || contraseña.length() != 0) {

            if (verificar()) {

                AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazPrincipalUsuario.fxml"));
                Scene scene = new Scene(anchor);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();

            } else {
                lbl_error.setVisible(true);
                lbl_error.setText("¡Lo sentimos! No se encuentra registrado aún");
            }

        } else {
            lbl_error.setVisible(true);
            lbl_error.setText("Hay campos sin llenar");
        }

    }//fin action

    @FXML
    private void btn_registrarUsuario(ActionEvent event) throws IOException, ClassNotFoundException {
        //Transicion al mostrar la ventana
        anp_ingresar.setVisible(false);
        anp_registrar.setVisible(true);
        anp_registrar.setOpacity(0);
        Utilidades.transition(anp_registrar);
    }//fin action   

    @FXML
    private void handleRegistrar(ActionEvent event) throws IOException, ClassNotFoundException, ListaException {
        //controles para registrar datos ingresados
        nombreCompleto = tfd_nombreC.getText();
        correo = tfd_correo.getText();
        nombreU = tfd_nombreUs.getText();
        contrasena = pwf_contrasena.getText();
        confirmarContraseña = pwf_confContraseña.getText();
        numeroTelefono = tfd_numeroTelefono.getText();

        if (nombreCompleto.length() != 0 || correo.length() != 0 || nombreU.length() != 0
                || contrasena.length() != 0 || confirmarContraseña.length() != 0 || (numeroTelefono + "").length() != 0) {
            if (Utilidades.verificarCorreo(correo)) {
                if (!verificar()) {
                    try {
                        if (numeroTelefono.length() == 8) {
                            if (contrasena.equals(confirmarContraseña)) {

                                Participantes participantes = new Participantes(nombreCompleto, correo,
                                        nombreU, contrasena, Integer.parseInt(numeroTelefono));
                                controlA.setNombre("Participantes.dat");
                                controlA.escribir(participantes);
                                tfd_correo.clear();
                                tfd_nombreC.clear();
                                tfd_nombreUs.clear();
                                tfd_numeroTelefono.clear();
                                pwf_confContraseña.clear();
                                pwf_contrasena.clear();
                                pwf_contraseña.clear();
                                tfd_nombreUsuario.clear();
                                lbl_errorContraseña.setVisible(true);
                                lbl_errorContraseña.setText("¡Te damos la bienvenida!");
                                btn_back.setFocusTraversable(true);
                            } else {
                                lbl_errorContraseña.setVisible(true);
                                lbl_errorContraseña.setText("Las contraseñas no coinciden");
                            }

                        } else {
                            lbl_errorNumero.setVisible(true);
                            lbl_errorNumero.setText("No es un número de teléfono válido");

                        }
                    } catch (NumberFormatException ex) {
                        lbl_errorNumero.setVisible(true);
                        lbl_errorNumero.setText("No es un número de teléfono válido");
                    }
                } else {
                    lbl_errorNUsuario.setVisible(true);
                    lbl_errorNUsuario.setText("Nombre de usuario no disponible");

                }
            } else {
                lbl_errorCorreo.setVisible(true);
                lbl_errorCorreo.setText("No es un correo válido");

            }
        } else {
            lbl_errorContraseña.setVisible(true);
            lbl_errorContraseña.setText("Hay campos sin llenar");
        }

    }//fin action

    @FXML
    private void hadleSalir(ActionEvent event) {
        anp_ingresar.setVisible(true);
        anp_ingresar.setOpacity(0);
        Utilidades.transition(anp_ingresar);
    }//fin action

    @FXML
    private void tfd_nombreUsuario(MouseEvent event) {
        lbl_error.setVisible(false);

    }//fin action

    @FXML
    private void pwf_contraseña(MouseEvent event) {
        lbl_error.setVisible(false);
    }//fin action

    @FXML
    private void tfd_nombreC(MouseEvent event) {
        invisible();
    }//fin action

    @FXML
    private void tfd_correo(MouseEvent event) {
        invisible();
    }//fin action

    @FXML
    private void tfd_nombreUsu(MouseEvent event) {
        invisible();
    }//fin action

    @FXML
    private void pwf_confContraseña(MouseEvent event) {
        invisible();
    }//fin action

    @FXML
    private void tfd_numeroT(MouseEvent event) {
        invisible();
    }//fin action

    public boolean verificar() throws ListaException, IOException, ClassNotFoundException {
        Object participantes = new Participantes();
        controlA.setNombre("Participantes.dat");
        listE = controlA.cargarLista();

        if (!listE.isEmpty()) {

            for (int i = 1; i <= listE.getSize(); i++) {
                participantes = listE.getNodo(i).elemento;
                Participantes p = (Participantes) participantes;

                if (p.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && p.getContraseña().equals(contraseña)) {
                    return true;
                } else if (p.getNombreUsuario().equalsIgnoreCase(nombreU)) {
                    return true;
                }
//                if (p.getCorreo().equalsIgnoreCase(correo)) {
//                    return true;
//                }
//                if (("" + p.getNumeroTelefono()).equals(numeroTelefono + "")) {
//                    return true;
//                }
            }
        }
        return false;
    }//fin verificar

    public void invisible() {
        
        lbl_errorContraseña.setVisible(false);
        lbl_errorNUsuario.setVisible(false);
        lbl_error.setVisible(false);
        lbl_errorNumero.setVisible(false);
        lbl_errorCorreo.setVisible(false);
        
    }//fin invisible

    //salir
    @FXML
    private void ir_elegirLogin(MouseEvent event) throws IOException {
        
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }//fin action

    private void loadDialog() {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Heading"));
        content.setBody(new Text("¡Saludos! " + nombreCompleto + " \n Te damos la bienvenida "
                + "a Treasure Hill "));
        JFXDialog dialog = new JFXDialog();
    }//fin m'etodo

}//fin class
