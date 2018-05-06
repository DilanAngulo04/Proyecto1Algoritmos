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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;

public class InterfazPrincipalAdministradorController implements Initializable {

    @FXML
    private JFXListView<String> listViewMaterial;
    @FXML
    private TabPane tab_ventanas;
    @FXML
    private JFXListView<String> lsv_participantes;
    @FXML
    private ListView<String> lsw_subastas;
    @FXML
    private ListView<String> lsv_seguridad;
    @FXML
    private ListView<String> lsv_administrador;
    @FXML
    private AnchorPane anp_Root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_Root.setOpacity(0);
        Utilidades.transition(anp_Root);
        loadListViewParticipantes();
        selectmenuParticipantes();
        loadListViewMateriales();
        selectmenuMateriales();
        loadListViewSubastas();
        selectmenuSubastas();
    }//fin initialize

    //Agregar contenido a la lista de opciones
    private void loadListViewParticipantes() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Ver Participantes");
        ols.add("Modificar Participantes");
        ols.add("Eliminar Participantes");
        lsv_participantes.setItems(ols);
    }//fin

    //Añadir la ventana que se elije
    private void selectmenuParticipantes() {
        lsv_participantes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = lsv_participantes.getSelectionModel().getSelectedIndex();
                if (i == 0) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloVerParticipante.fxml"));
                        Tab td = new Tab("Ver Participantes", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 1) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloModificarParticipante.fxml"));
                        Tab td = new Tab("Modificar Participantes", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 2) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloEliminarParticipante.fxml"));
                        Tab td = new Tab("Eliminar Participantes", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }//fin m'etodo

    //Agregar contenido a la lista de opciones
    private void loadListViewSubastas() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Configurar Subasta");
        ols.add("Control de Subasta");
        lsw_subastas.setItems(ols);
    }//fin m'etodo

    //Añadir la ventana que se elije
    private void selectmenuSubastas() {
        lsw_subastas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = lsw_subastas.getSelectionModel().getSelectedIndex();
                if (i == 0) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloConfSubasta.fxml"));
                        Tab td = new Tab("Configurar Subasta", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }//fin if
                if (i == 1) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloControlSubasta.fxml"));
                        Tab td = new Tab("Control Subastas", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }//fin if              
            }
        });
    }//fin m'etodo

    //Agregar contenido a la lista de opciones
    private void loadListViewMateriales() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Productos");
        ols.add("Bienes");
        ols.add("Servicios");
        listViewMaterial.setItems(ols);
    }

    //Añadir la ventana que se elije
    private void selectmenuMateriales() {
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
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 1) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloBienes.fxml"));
                        Tab td = new Tab("Bienes", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 2) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloServicios.fxml"));
                        Tab td = new Tab("Subastas", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }

}//fin class
