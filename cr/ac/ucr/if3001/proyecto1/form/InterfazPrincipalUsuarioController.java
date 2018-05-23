package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.object.RegistroInvitaciones;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void initialize(URL url, ResourceBundle rb) { //hacer commit de esta clase 
        
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);

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
        loadListViewSubastas();
        selectmenuSubastas();
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
    
    private void loadListViewSubastas(){
    ObservableList<String> ols = FXCollections.observableArrayList();
    ols.add("Subastas");
    ols.add("Mantenimiento subastas");
    lvw_subastas.setItems(ols);
    }//fin de metodo
    
    //Añadir la ventana que se elije
    private void selectmenuSubastas() {
        lvw_subastas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    int i = lvw_subastas.getSelectionModel().getSelectedIndex();
                    //se agrega la ventana según el numero por defecto de las opciones de la lista
                    //instancio la invitacion con la fecha mas cercana a comenzar
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM");
                    RegistroInvitaciones regsInvi = subastaEntrante();
                    Date fecha = dateFormat.parse(regsInvi.getHoraIncio());
                    if (i == 0 ) { //hacer condicion aqui para que aparezca pujas o no //&& fechaInvitacionAnterior(fecha)
                        try {
                            
                            Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloSubastasParticipante.fxml"));
                            Tab td = new Tab("Hacer Pujas", node);
                            tab_ventanas.getSelectionModel().select(td);
                            tab_ventanas.getTabs().add(td);
                            
                        } catch (IOException ioe) {
                            Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                        }
                    }
//                    else 
//                        if(i == 0){ // carga pantalla de espera si no ha alcanzado la fecha de subasta
//                        try {
//                            
//                            Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubastaNoDisponible.fxml"));
//                            Tab td = new Tab("Hacer Pujas", node);
//                            tab_ventanas.getSelectionModel().select(td);
//                            tab_ventanas.getTabs().add(td);
//                            
//                        } catch (IOException ioe) {
//                            Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
//                        }
//                    }
                    if(i == 1){
                        try {
                            Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazSubModuloMantSubastas.fxml"));
                            Tab td = new Tab("Mantenimiento Subastas", node);
                            tab_ventanas.getSelectionModel().select(td);
                            tab_ventanas.getTabs().add(td);
                            
                        } catch (IOException ioe) {
                            Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ioe);
                        }
                    }
                } catch (ListaException ex) {
                    Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(InterfazPrincipalUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static RegistroInvitaciones subastaEntrante() throws ListaException, IOException, ClassNotFoundException, ParseException{
        ControlArchivos controlA = new ControlArchivos();
        controlA.setNombre("RegistroInvitaciones.dat");
        ListaEnlazada listaInvitaSub = new ListaEnlazada();
        
        Date fechaActual = new Date();
        listaInvitaSub = controlA.cargarLista();
        
        int marca = 1; //guarda la posicion del nodo en la lista que tiene la fecha mas cercana a realizarse la subasta
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM");
        
        RegistroInvitaciones regAux = (RegistroInvitaciones) listaInvitaSub.getNodo(1).elemento;
        
        Date fechamenor = dateFormat.parse(regAux.getHoraIncio());
        
        for (int i = 1; i <= listaInvitaSub.getSize(); i++) {
            regAux = (RegistroInvitaciones) listaInvitaSub.getNodo(i).elemento;
            
            Date deliveryDay = dateFormat.parse(regAux.getHoraIncio());
            
            if(deliveryDay.compareTo(fechaActual)<0 && deliveryDay.compareTo(fechamenor)<=0){
                fechamenor = deliveryDay;
                marca = i;
            }

        }
        
        return (RegistroInvitaciones) listaInvitaSub.getNodo(marca).elemento;
    }
    
    
    //metodo compara una fecha con la fecha actual del equipo
    public boolean fechaInvitacionAnterior(Date newDate){
        Date fechaActual = new Date();
        
       return  newDate.compareTo(fechaActual)>=0;
    }
}//fin class
