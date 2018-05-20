package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

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
    @FXML
    private JFXButton btn_cerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_Root.setOpacity(0);
        Utilidades.transition(anp_Root);
        Node node;
        try {
            node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazBienvenida.fxml"));
            Tab td = new Tab("¡Hola!", node);
            tab_ventanas.getTabs().add(td);
        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        loadListViewParticipantes();
        selectmenuParticipantes();
        loadListViewMateriales();
        selectmenuMateriales();
        loadListViewSubastas();
        selectmenuSubastas();
        loadListViewSeguridad();
        selectmenuSeguridad();

    }//fin initialize

    //Agregar contenido a la lista de opciones
    private void loadListViewParticipantes() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Ver Participantes");
        ols.add("Modificar Participantes");
        ols.add("Eliminar Participantes");
        ols.add("Invitar Participantes");
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
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloVerParticipantes.fxml"));
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
                if (i == 3) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloInvitarParticipantes.fxml"));
                        Tab td = new Tab("Invitar Participantes", node);
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
    
     private void loadListViewSeguridad() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Registrar Administrador");
        lsv_seguridad.setItems(ols);
    }

    //Añadir la ventana que se elije
    private void selectmenuSeguridad() {
        lsv_seguridad.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = lsv_seguridad.getSelectionModel().getSelectedIndex();
                if (i == 0) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazModuloSeguridadAdministrador.fxml"));
                        Tab td = new Tab("Registrar Administrador", node);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }                
            }
        });
    }

    @FXML
    private void ir_elegirlogin(ActionEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();        
    }

}//fin class
