package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

public class InterfazModuloParticipantesController implements Initializable {

    @FXML
    private AnchorPane anp_root;
    @FXML
    private TreeTableView <Participantes> tableview;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        anp_root.setOpacity(0);
//        Utilidades.transition(anp_root);
          JFXTreeTableColumn<Participantes, String> colunm = new JFXTreeTableColumn<>("Nombre");
          colunm.setPrefWidth(150);
          colunm.setCellValueFactory((param) -> {
              return param.getValue().getValue().nombre;
          });
          
//          JFXTreeTableColumn<Participantes, String> colunm2 = new JFXTreeTableColumn<>("Nombre");
//          colunm2.setPrefWidth(200);
//          colunm2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Participantes, String>, ObservableValue<String>>() {
//              @Override
//              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Participantes, String> param) {
//                  return param.getValue().getValue().nombreUsuario;
//              }
//          });
//          
//          JFXTreeTableColumn<Participantes, String> colunm3 = new JFXTreeTableColumn<>("Nombre");
//          colunm3.setPrefWidth(200);
//          colunm3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Participantes, String>, ObservableValue<String>>() {
//              @Override
//              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Participantes, String> param) {
//                  return param.getValue().getValue().correo;
//              }
//          });
          
          ObservableList<Participantes> participantes = FXCollections.observableArrayList();
          participantes.add(new Participantes("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new Participantes("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new Participantes("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new Participantes("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new Participantes("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          
          final TreeItem<Participantes> root = new RecursiveTreeItem<Participantes>(participantes, RecursiveTreeObject::getChildren);
          tableview.getColumns().setAll(colunm);
          tableview.setRoot(root);
          tableview.setShowRoot(false);
    }//fin initialize
    
    class Participantes extends RecursiveTreeObject<Participantes>{
                
        StringProperty nombre;
        StringProperty nombreUsuario;
        StringProperty correo;
        
        public Participantes(String nombre, String nombreUsuario, String correo){
            this.nombre = new SimpleStringProperty(nombre);
            this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
            this.correo = new SimpleStringProperty(correo);            
        }
        
    }
    
}//fin class
