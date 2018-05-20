package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

public class InterfazModuloParticipantesController implements Initializable {

    @FXML
    private AnchorPane anp_root;
    @FXML
    private JFXTreeTableView<User> tbv_participantes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        anp_root.setOpacity(0);
//        Utilidades.transition(anp_root);
          JFXTreeTableColumn<User, String> colunm = new JFXTreeTableColumn<>("Nombre");
          colunm.setPrefWidth(150);
          colunm.setCellValueFactory((param) -> {
              return param.getValue().getValue().nombre;
          });
          
          JFXTreeTableColumn<User, String> colunm2 = new JFXTreeTableColumn<>("Usuario");
          colunm2.setPrefWidth(200);
          colunm2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                  return param.getValue().getValue().nombreUsuario;
              }
          });
          
          JFXTreeTableColumn<User, String> colunm3 = new JFXTreeTableColumn<>("Correo");
          colunm3.setPrefWidth(200);
          colunm3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                  return param.getValue().getValue().correo;
              }
          });
          
          ObservableList<User> participantes = FXCollections.observableArrayList();
          participantes.add(new User("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new User("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new User("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new User("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          participantes.add(new User("Dilan", "dilana0400.", "dilan0400ar@gmail.com"));
          
          final TreeItem<User> root = new RecursiveTreeItem<>(participantes, RecursiveTreeObject::getChildren);
          tbv_participantes.getColumns().setAll(colunm, colunm2, colunm3);
          tbv_participantes.setRoot(root);
          tbv_participantes.setShowRoot(false);
    }//fin initialize
    
    class User extends RecursiveTreeObject<User>{
                
        SimpleStringProperty nombre;
        SimpleStringProperty nombreUsuario;
        SimpleStringProperty correo;
        
        public User(String nombre, String nombreUsuario, String correo){
            this.nombre = new SimpleStringProperty(nombre);
            this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
            this.correo = new SimpleStringProperty(correo);            
        }
        
    }
    
}//fin class
