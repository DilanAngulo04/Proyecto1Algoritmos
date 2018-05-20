package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Administrador;
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
import javafx.stage.Stage;

public class InterfazLoginAdministradorController implements Initializable {

    private ListaEnlazada listE = new ListaEnlazada();
    private ControlArchivos controlA = new ControlArchivos();
    String nombreUsuario;
    String contraseña;

    @FXML
    private JFXButton btn_ingresar;
    @FXML
    private AnchorPane anp_root;
    @FXML
    private JFXTextField tfd_nombreUsuario;
    @FXML
    private JFXPasswordField pwf_contraseña;
    @FXML
    private Label lbl_errorDatos;
    @FXML
    private ImageView btn_elegirLogin;

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
                AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazPrincipalAdministrador.fxml"));
                Scene scene = new Scene(anchor);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("TreasureHill::Administrador");
                stage.setMaximized(true);
                stage.show();
            } else {
                lbl_errorDatos.setVisible(true);
                lbl_errorDatos.setText("El usuario ingresado aún no está registrado");
            }
        } else {
            lbl_errorDatos.setVisible(true);
            lbl_errorDatos.setText("Hay campos sin llenar");
        }
    }//fin action     

    public boolean verificar() throws ListaException, IOException, ClassNotFoundException {
        Object participantes = new Administrador();
        controlA.setNombre("Administrador.dat");
        listE = controlA.cargarLista();
        System.out.println(listE.toString());
        if (!listE.isEmpty()) {
            for (int i = 1; i <= listE.getSize(); i++) {
                participantes = listE.getNodo(i).elemento;
                Administrador a = (Administrador) participantes;
                if (a.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && a.getContraseña().equals(contraseña)) {
                    return true;
                }
            }
        }
        return false;
    }//fin verificar   

    @FXML
    private void tfd_nombreUsuario(MouseEvent event) {
        lbl_errorDatos.setVisible(false);
    }

    @FXML
    private void pwf_contraseña(MouseEvent event) {
        lbl_errorDatos.setVisible(false);
    }

    @FXML
    private void ir_elegirLogin(MouseEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}//fin class
