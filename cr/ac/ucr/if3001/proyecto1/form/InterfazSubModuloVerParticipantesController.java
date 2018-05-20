package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class InterfazSubModuloVerParticipantesController implements Initializable {
    
    
    private ListaEnlazada listE = new ListaEnlazada();
    private final ControlArchivos controlA = new ControlArchivos();
    ObservableList<User> participantes = FXCollections.observableArrayList();
    
    @FXML
    private JFXTreeTableView<User> tbv_participantes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //        anp_root.setOpacity(0);
//        Utilidades.transition(anp_root);
          JFXTreeTableColumn<User, String> colunm = new JFXTreeTableColumn<>("Nombre Completo");
          colunm.setPrefWidth(150);
          colunm.setCellValueFactory((param) -> {
              return param.getValue().getValue().nombre;
          });
          
          JFXTreeTableColumn<User, String> colunm2 = new JFXTreeTableColumn<>("Nombre de Usuario");
          colunm2.setPrefWidth(200);
          colunm2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                  return param.getValue().getValue().nombreUsuario;
              }
          });
          
          JFXTreeTableColumn<User, String> colunm3 = new JFXTreeTableColumn<>("Correo Electrónico");
          colunm3.setPrefWidth(200);
          colunm3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                  return param.getValue().getValue().correo;
              }
          });     
          
          JFXTreeTableColumn<User, String> colunm4 = new JFXTreeTableColumn<>("Número de Teléfono");
          colunm4.setPrefWidth(200);
          colunm4.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
              @Override
              public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                  return param.getValue().getValue().telefono;
              }
          }); 
          
          
        try {
            cargarTabla();
        } catch (ListaException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloVerParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          final TreeItem<User> root = new RecursiveTreeItem<>(participantes, RecursiveTreeObject::getChildren);
          tbv_participantes.getColumns().setAll(colunm, colunm2, colunm3, colunm4);
          tbv_participantes.setRoot(root);
          tbv_participantes.setShowRoot(false);
    }//fin initialize
    
    //Este metodo debe ir antes de User, de lo contrario tendrá errores
     public void cargarTabla() throws ListaException, IOException, ClassNotFoundException{
        Object part = new Participantes();
        controlA.setNombre("Participantes.dat");
        listE = controlA.cargarLista();

        if (!listE.isEmpty()) {

            for (int i = 1; i <= listE.getSize(); i++) {
                part = listE.getNodo(i).elemento;
                Participantes p = (Participantes) part;
                
                participantes.add(new User(p.getNombre(), p.getNombreUsuario(), p.getCorreo(), 
                        Utilidades.formatTelefono(p.getNumeroTelefono())));
            }
        }
    }//Fin m'etodo
    
    class User extends RecursiveTreeObject<User>{
                
        SimpleStringProperty nombre;
        SimpleStringProperty nombreUsuario;
        SimpleStringProperty correo;
        SimpleStringProperty telefono;
        
        public User(String nombre, String nombreUsuario, String correo, String telefono){
            this.nombre = new SimpleStringProperty(nombre);
            this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
            this.correo = new SimpleStringProperty(correo);  
            this.telefono = new SimpleStringProperty(telefono);
        }
        
    }
}
