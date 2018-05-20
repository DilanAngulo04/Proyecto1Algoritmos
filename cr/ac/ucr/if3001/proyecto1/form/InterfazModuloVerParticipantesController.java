package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXTreeTableView;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.krb5.internal.crypto.Des3;

public class InterfazModuloVerParticipantesController implements Initializable {

    @FXML
    private TableView<Participantes> tvw_verParticipantes;

    @FXML
    private TableColumn<Participantes, SimpleStringProperty> clm_nombreCompleto;

    @FXML
    private TableColumn<Participantes, SimpleStringProperty> clm_nombreUsuario;

    @FXML
    private TableColumn<Participantes, SimpleStringProperty> clm_correo;

    @FXML
    private TableColumn<Participantes, Integer> clm_telefono;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
//    public ObservableList<Participantes> getParticipantes() throws IOException, ClassNotFoundException{
//          
//    }

}
