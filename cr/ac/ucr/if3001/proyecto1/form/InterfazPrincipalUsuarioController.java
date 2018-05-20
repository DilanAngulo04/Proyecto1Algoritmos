package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXListView;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;

public class InterfazPrincipalUsuarioController implements Initializable {
    
    @FXML
    private JFXListView<String> listViewMaterial;
    @FXML
    private TabPane tab_ventanas;
    @FXML
    private AnchorPane anp_root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Node node;
        try {
            node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazBienvenida.fxml"));
            Tab td = new Tab("¡Hola!", node);
            tab_ventanas.getTabs().add(td);
        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        anp_root.setOpacity(10);
        Utilidades.transition(anp_root);
        loadListView();
        selectmenu();
    }//fin initialize

    //Agregar contenido a la lista de opciones
    private void loadListView(){
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Productos");
        ols.add("Bienes");
        ols.add("Servicios");
        listViewMaterial.setItems(ols);
    }//fin m'etodo
    
    //Añadir la ventana que se elije
    private void selectmenu(){
        listViewMaterial.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = listViewMaterial.getSelectionModel().getSelectedIndex();
                //se agrega la ventana según el numero por defecto de las opciones de la lista
                if(i==0){
                    try{
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloProductos.fxml"));
                        Tab td = new Tab("Productos", node);
                        tab_ventanas.getTabs().add(td);
                    }catch(IOException ioe){
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if(i==1){
                    try{
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloBienes.fxml"));
                        Tab td = new Tab("Bienes", node);
                        tab_ventanas.getTabs().add(td);
                    }catch(IOException ioe){
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if(i==2){
                    try{
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloServicios.fxml"));
                        Tab td = new Tab("Servicios", node);
                        tab_ventanas.getTabs().add(td);
                    }catch(IOException ioe){
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }//fin m'etodo        
}//fin class
