package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class InterfazSubModuloInvitarParticipantesController implements Initializable {

    @FXML
    private JFXComboBox<String> comboBox;

    private List<String> numberArticulos = new ArrayList<>();
    private ObservableList<String> obbList;
    @FXML
    private JFXListView<String> lsv_listaProductos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        elegirNumero();
        loadListViewProductos();
    }

    public void elegirNumero() {
        for (int i = 1; i <= 10; i++) {
            numberArticulos.add("" + i);
        }

        obbList = FXCollections.observableArrayList(numberArticulos);
        comboBox.setItems(obbList);

    }

    private void loadListViewProductos() {
        ObservableList<String> ols = FXCollections.observableArrayList();        
        ols.add("Auto BMW");    
        ols.add("Auto BMW");    
        ols.add("Auto BMW");    
        ols.add("Auto BMW");    
        ols.add("Auto BMW");    
        ols.add("Auto BMW");    
        ols.add("Auto BMW");    
        
        lsv_listaProductos.setItems(ols);
    }//fin
}
