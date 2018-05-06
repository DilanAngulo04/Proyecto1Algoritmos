package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXListView;
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

public class InterfazBienvenidaAdministradorController implements Initializable {

    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private JFXListView<String> listViewMaterial;
    @FXML
    private TabPane tab_ventanas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadListView();
        selectmenu();
    }

    //Agregar contenido a la lista de opciones
    private void loadListView() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Productos");
        ols.add("Bienes");
        ols.add("Servicios");
        listViewMaterial.setItems(ols);
    }

    //Añadir la ventana que se elije
    private void selectmenu() {
        listViewMaterial.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = listViewMaterial.getSelectionModel().getSelectedIndex();
                if (i == 0) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloProductos.fxml"));
                        Tab td = new Tab("Productos", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazBienvenidaAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 1) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloBienes.fxml"));
                        Tab td = new Tab("Bienes", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazBienvenidaAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 2) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloServicios.fxml"));
                        Tab td = new Tab("Subastas", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazBienvenidaAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }
}//fin class
