package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InterfazPrincipalAdministradorController implements Initializable {

    //Formato fecha
    DateFormat dfDateFull = DateFormat.getDateInstance(DateFormat.FULL);
    Date fecha = new Date();
    String fech = "" + dfDateFull.format(fecha);

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
    @FXML
    private Label lbl_hora;
    @FXML
    private Label lbl_fecha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_Root.setOpacity(0);
        Utilidades.transition(anp_Root);

        lbl_fecha.setText(fech);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar calendario = Calendar.getInstance();
            String segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
            String minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
            String hora = calendario.get(Calendar.HOUR) > 9 ? "" + calendario.get(Calendar.HOUR) : "0" + calendario.get(Calendar.HOUR);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            lbl_hora.setText(hora + ":" + (minutos) + ":" + segundos);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

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
                        añadirPestaña("InterfazSubModuloVerParticipantes.fxml", "Ver Participantes");

                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 1) {
                    try {
                        añadirPestaña("InterfazSubModuloModificarParticipante.fxml", "Modificar Participantes");
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
        ols.add("Invitar Participantes");
        lsw_subastas.setItems(ols);
    }//fin m'etodo

    //Añadir la ventana que se elije
    private void selectmenuSubastas() {
        lsw_subastas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = lsw_subastas.getSelectionModel().getSelectedIndex();
                if (i == 2) {
                    try {
                        añadirPestaña("InterfazSubModuloInvitarParticipantes.fxml", "Invitar Participantes");
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }            
            }
        });
    }//fin m'etodo

    //Agregar contenido a la lista de opciones
    private void loadListViewMateriales() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Registrar bienes");
        ols.add("Modificar");
        ols.add("Consultar");
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
                        añadirPestaña("InterfazModuloMaterial.fxml", "Registrar bienes");
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 1) {
                    try {
                        añadirPestaña("InterfazSubModuloModificarProductos.fxml", "Modificar");
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 2) {
                    try {
                        añadirPestaña("InterfazSubModuloConsultarElemento.fxml", "Consultar");
                    } catch (IOException ex) {
                        Logger.getLogger(InterfazPrincipalAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void añadirPestaña(String ventana, String nombreVentana) throws IOException {
        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource(ventana));
        Tab td = new Tab(nombreVentana, node);
        tab_ventanas.getSelectionModel().select(td);
        tab_ventanas.getTabs().add(td);
    }

}//fin class
