package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.object.Material;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class InterfazSubModuloConsultarElementoController implements Initializable {

    //instancias necesarias
    ControlArchivos controlA= new ControlArchivos();
    FilteredList filter;
    
    @FXML
    private TableView<Material> tabla;
    @FXML
    private TableColumn<Material, Integer> costoCol;
    @FXML
    private TableColumn<Material, String> categoriaCol;
    @FXML
    private TableColumn<Material, String> descripcionCol;
    @FXML
    private TextField txt_search;
    @FXML
    private TableColumn<Material, String> nombreCol;
    @FXML
    private TableColumn<Material, Integer> cantCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializa tabla
        try {
            controlA.setNombre("Material.dat");
            ObservableList lista= FXCollections.observableArrayList(controlA.readList());
            
            nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            costoCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
            categoriaCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            cantCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            
            tabla.setItems(lista);
            
            filter= new FilteredList(lista, e->true);
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloConsultarElementoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    //Filtra tabla
    @FXML
    private void search(MouseEvent event) {
        
        txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
            
            filter.setPredicate((Predicate<Material>) (Material material)->{
            
                if (newValue.isEmpty() || newValue==null) {
                    return true;
                }else if(material.getNombre().contains(newValue) || material.getNombre().equalsIgnoreCase(newValue)){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
    
            return false;
            });
            
            SortedList sort= new SortedList(filter);
            sort.comparatorProperty().bind(tabla.comparatorProperty());
            tabla.setItems(sort);
            
        });
    }//fin action
    
}//fin class
