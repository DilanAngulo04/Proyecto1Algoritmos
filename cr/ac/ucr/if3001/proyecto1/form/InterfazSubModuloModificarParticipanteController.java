package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class InterfazSubModuloModificarParticipanteController implements Initializable {

    @FXML
    private AnchorPane anp_root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }//fin initialize
    
}//fin class
