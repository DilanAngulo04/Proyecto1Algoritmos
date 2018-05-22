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
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class InterfazPrincipalUsuarioController extends Thread implements Initializable {

    //Formato fecha
    DateFormat dfDateFull = DateFormat.getDateInstance(DateFormat.FULL);
    Date fecha = new Date();
    String fech = "" + dfDateFull.format(fecha);


    @FXML
    private JFXListView<String> listViewMaterial;
    @FXML
    private TabPane tab_ventanas;
    @FXML
    private AnchorPane anp_root;
    @FXML
    private JFXButton btn_cerrarSesion;
    @FXML
    private Label lbl_hora;
    @FXML
    private Label lbl_fecha;
    @FXML
    private JFXListView<String> lvw_participantes;
    @FXML
    private JFXListView<String> lvw_subastas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lbl_fecha.setText(fech);
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar calendario = Calendar.getInstance();
            String segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
            String minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
            String hora = calendario.get(Calendar.HOUR)>9?""+calendario.get(Calendar.HOUR):"0"+calendario.get(Calendar.HOUR);
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

        anp_root.setOpacity(10);
        Utilidades.transition(anp_root);
        loadListViewMateriales();
        selectmenuMateriales();
        loadListViewParticipantes();
        selectmenuParticipantes();
    }//fin initialize

    //Agregar contenido a la lista de opciones de participantes
    private void loadListViewParticipantes() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Participantes");
        lvw_participantes.setItems(ols);
    }//fin m'etodo

    //Añadir la ventana que se elije
    private void selectmenuParticipantes() {
        lvw_participantes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = lvw_participantes.getSelectionModel().getSelectedIndex();
                //se agrega la ventana según el numero por defecto de las opciones de la lista
                if (i == 0) {
                    try {

                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazParticipanteUsuario.fxml"));
                        Tab td = new Tab("Participantes", node);
                        tab_ventanas.getSelectionModel().select(td);
                        tab_ventanas.getTabs().add(td);

                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }//fin m'etodo        

    //Agregar contenido a la lista de opciones de materiales
    private void loadListViewMateriales() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Ver Bienes");
        //ols.add("Bienes");
        //ols.add("Servicios");
        listViewMaterial.setItems(ols);
    }//fin m'etodo

    //Añadir la ventana que se elije
    private void selectmenuMateriales() {
        listViewMaterial.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = listViewMaterial.getSelectionModel().getSelectedIndex();
                //se agrega la ventana según el numero por defecto de las opciones de la lista
                if (i == 0) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloVerProducto.fxml"));
                        Tab td = new Tab("Ver bienes", node);
                        tab_ventanas.getSelectionModel().select(td);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 1) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloBienes.fxml"));
                        Tab td = new Tab("Bienes", node);
                        tab_ventanas.getSelectionModel().select(td);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
                if (i == 2) {
                    try {
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloServicios.fxml"));
                        Tab td = new Tab("Servicios", node);
                        tab_ventanas.getSelectionModel().select(td);
                        tab_ventanas.getTabs().add(td);
                    } catch (IOException ioe) {
                        Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }//fin m'etodo        

    @FXML
    private void ir_elegirlogin(ActionEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}//fin class
