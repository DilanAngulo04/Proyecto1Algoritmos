package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfazLoginParticipantesController implements Initializable {

    //instancias necesarias
    private ListaEnlazada listE = new ListaEnlazada();
    private ControlArchivos controlA = new ControlArchivos();

    @FXML
    private JFXButton btn_ingresar;
    @FXML
    private JFXButton btn_registrate;
    @FXML
    private AnchorPane anp_root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }//fin initialize

    @FXML
    private void btn_ingresarInfo(ActionEvent event) throws IOException, ListaException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazPrincipalUsuario.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }//fin action

    @FXML
    private void btn_registrarUsuario(ActionEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazRegistroParticipantes.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }//fin action

    public void verificar() throws ListaException {
        Object participantes = new Participantes();

        controlA.setNombre("Participantes.dat");
        listE.insertar(controlA.cargar(new Participantes()));
        System.out.println(listE.toString());
        if (!listE.isEmpty()) {
            for (int i = 0; i < listE.getSize(); i++) {
                participantes = (Participantes) listE.getNodo(i);
            }
        }
    }//fin verificar

}//fin class
