package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InterfazSubModuloProductosController implements Initializable {
    
    private AnchorPane anp_root;
    @FXML
    private AnchorPane anp_options;
    @FXML
    private AnchorPane anp_perfil;
    @FXML
    private AnchorPane anp_participantes;
    @FXML
    private ImageView btn_perfil;
    private ImageView btn_participnates;
    private ImageView btn_cerrar;
    @FXML
    private Label lbl_nombreUsuario;
    @FXML
    private Label lbl_NombreCompleto;
    @FXML
    private Label lbl_NombreCompleto1;
    @FXML
    private JFXListView<String> lvw_participantes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        anp_root.setOpacity(0);
//        Utilidades.transition(anp_root);
//        lvw_participantes.setEditable(false);



//        anp_participantes.setVisible(false);
        anp_participantes.setVisible(false);
//        anp_options.setVisible(true);
        
    }//fin initialize

    @FXML
    private void action(MouseEvent event) {

        int count= event.getClickCount();
        
        if (event.getTarget() == btn_perfil && count==1) {
            anp_participantes.setVisible(true);
        } else if(event.getTarget() == btn_perfil && count==2)
            anp_participantes.setVisible(false);
             count=0;
        
    }
    
    private void loadListViewParticipantes() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Ver Participantes");
        ols.add("Modificar Participantes");
        ols.add("Eliminar Participantes");
        lvw_participantes.setItems(ols);
    }//fin
    
}//fin class
