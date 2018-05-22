package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import cr.ac.ucr.if3001.proyecto1.domain.ComprobarConexion;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.EnviarCorreo;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.object.RegistroInvitaciones;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;

public class InterfazSubModuloInvitarParticipantesController extends Thread implements Initializable {

    //instancias necesarias
    private final ObservableList<String> ols = FXCollections.observableArrayList();
    private ListaEnlazada listE = new ListaEnlazada();
    private final ControlArchivos controlA = new ControlArchivos();
    private final ObservableList<Añadidos> produc = FXCollections.observableArrayList();
    private final ObservableList<Añadidos> part = FXCollections.observableArrayList();
    private final ListaEnlazada listaProductosS = new ListaEnlazada();
    private final ListaEnlazada listaParticipantesS = new ListaEnlazada();
    String ruta = "src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\";
    String nombre = "Participantes.dat";
    String producto, participante, material, usuario;
    LocalDate diaSubasta;
    LocalTime horaInicio, horaFin;

    //Childrens de la clase
    @FXML
    private JFXListView<String> lsv_listaProductos;
    @FXML
    private JFXListView<String> lsv_listaParticipantes;
    @FXML
    private JFXTreeTableView<Añadidos> tvw_productos;
    @FXML
    private JFXTreeTableView<Añadidos> tvw_participantes;
    @FXML
    private Label lbl_errorProductos;
    @FXML
    private Label lbl_errorParticipantes;
    @FXML
    private JFXDatePicker dpk_diaSubasta;
    @FXML
    private JFXDatePicker dpk_horaInicio;
    @FXML
    private JFXDatePicker dpk_horaFin;
    @FXML
    private JFXButton btn_enviarCorreos;
    @FXML
    private Label lbl_errorDia;
    @FXML
    private Label lbl_errorInicio;
    @FXML
    private Label lbl_errorfin;
    @FXML
    private AnchorPane anp_root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);

        if (ComprobarConexion.comprobarConexion()) {
            Notifications.create().title(":)").text("Se establecio la conección a internet").showInformation();
        } else {
            Notifications.create().title("¡Ups!").text("No hay conección a internet").showError();
        }

        //Se deshabilitan los dias anteriores a la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        dpk_diaSubasta.setConverter(new LocalDateStringConverter(formatter, null));
        dpk_diaSubasta.setConverter(new LocalDateStringConverter(FormatStyle.FULL)); //Fecha en un formato String
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now().plusDays(-1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #CFD1CF;");
                        }
                    }
                };
            }
        };
        dpk_diaSubasta.setDayCellFactory(dayCellFactory);
        dpk_diaSubasta.setValue(LocalDate.now().plusDays(0));

        loadListViewProductos();
        try {
            cargarListaParticipantes();
        } catch (ListaException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloInvitarParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Definicion de las columnas de la tabla productos
        JFXTreeTableColumn<Añadidos, String> productos = new JFXTreeTableColumn<>("Productos Seleccionados");
        productos.setPrefWidth(229);
        productos.setCellValueFactory((param) -> {
            return param.getValue().getValue().producto;
        });

        final TreeItem<Añadidos> rootProduc = new RecursiveTreeItem<>(produc, RecursiveTreeObject::getChildren);
        tvw_productos.getColumns().setAll(productos);
        tvw_productos.setRoot(rootProduc);
        tvw_productos.setShowRoot(false);

        //Definicion de las columnas de la tabla participantes
        JFXTreeTableColumn<Añadidos, String> parte = new JFXTreeTableColumn<>("Usuarios Seleccionados");
        parte.setPrefWidth(229);
        parte.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombreUsuario;
        });

        final TreeItem<Añadidos> rootPart = new RecursiveTreeItem<>(part, RecursiveTreeObject::getChildren);
        tvw_participantes.getColumns().setAll(parte);
        tvw_participantes.setRoot(rootPart);
        tvw_participantes.setShowRoot(false);
    }//fin initialize

    //Se obtiene el valor del participante seleccionado en la lista
    @FXML
    void cargarTablaPart(MouseEvent event) throws ListaException {
        invisible();

        usuario = lsv_listaParticipantes.getSelectionModel().getSelectedItem();
        //Se verifica si la lista est'a vac'ia        
        if (listaParticipantesS.isEmpty()) { //si est'a vac'ia se ingresa el primer elemento
            listaParticipantesS.insertar(usuario);
            part.add(new Añadidos(usuario, null));

        } else {//si no, se recorre la lista para comparar los elementos internos
            //se verifica que no re repitan elementos            
            if (!verificar(usuario, listaParticipantesS)) {//si no se repiten elementos se ingresa el valor a la lista y la la tableview
                listaParticipantesS.insertar(usuario);
                part.add(new Añadidos(usuario, null));
                lbl_errorParticipantes.setVisible(false);
            } else {
                lbl_errorParticipantes.setVisible(true);
            }
        }//fin else
    }//fin action

    //Se obtiene el valor del producto seleccionado en la lista
    @FXML
    void cargarTablaProductos(MouseEvent event) throws ListaException {
        invisible();
        material = lsv_listaProductos.getSelectionModel().getSelectedItem();
        //Se verifica si la lista est'a vac'ia   
        if (listaProductosS.isEmpty()) {//si est'a vac'ia se ingresa el primer elemento
            listaProductosS.insertar(material);
            produc.add(new Añadidos(null, material));

        } else {//si no, se recorre la lista para comparar los elementos internos
            //se verifica que no re repitan elementos 
            if (!verificar(material, listaProductosS)) {//si no se repiten elementos se ingresa el valor a la lista y la la tableview                
                listaProductosS.insertar(material);
                produc.add(new Añadidos(null, material));
                lbl_errorProductos.setVisible(false);
            } else {
                lbl_errorProductos.setVisible(true);
            }

        }//fin else
    }//fin action

    //Método para verificar que no se seleccione la opcion mas de una ves en listview 
    private boolean verificar(String elemento, ListaEnlazada lista) throws ListaException {
        for (int i = 1; i <= lista.getSize(); i++) {
            //Se verifica que el elemento seleccionado no se repita
            if (("" + lista.getNodo(i).elemento).equals(elemento)) {
                return true;
            }
        }
        return false;
    }//fin m'etodo

    //se reinician los mensajes de error
    private void invisible() {
        lbl_errorParticipantes.setVisible(false);
        lbl_errorProductos.setVisible(false);
        lbl_errorDia.setVisible(false);
        lbl_errorInicio.setVisible(false);
        lbl_errorfin.setVisible(false);
    }

    //se carga la lista de productos totales
    private void loadListViewProductos() {
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Auto BMW");
        ols.add("Casa");
        ols.add("Barco");
        ols.add("Mesa");
        ols.add("Pintura");
        ols.add("PS4 PRO");
        ols.add("iop");

        lsv_listaProductos.setItems(ols);
    }//fin m'etodo

    //Se carga la lista de participantes totales 
    public void cargarListaParticipantes() throws ListaException, IOException, ClassNotFoundException {
        Object part = new Participantes();
        controlA.setNombre("Participantes.dat");
        listE = controlA.cargarLista();

        //Se comprueba que la lista no este vac'ia
        if (!listE.isEmpty()) {

            //Se recorre la lista para obtener todos los nombres de usuario
            for (int i = 1; i <= listE.getSize(); i++) {
                part = listE.getNodo(i).elemento;
                Participantes p = (Participantes) part;
                ols.add(p.getNombreUsuario().trim());
            }
        }

        lsv_listaParticipantes.setItems(ols);
    }//Fin m'etodo 

    //***********************************************Invisivilizar mensajes de error************************************************
    @FXML
    private void ingresarDia(MouseEvent event) {
        invisible();
    }

    @FXML
    private void ingresarValorinicio(MouseEvent event) {
        invisible();
    }//fin action

    @FXML
    private void ingresarvalorfin(MouseEvent event) {
        invisible();
    }//fin action
    //****************************************************************************************************************************

    //Se envian los correos si cumple con las restricciones
    @FXML
    private void enviarCorreos(ActionEvent event) throws ListaException, IOException, ClassNotFoundException, MessagingException {

        invisible();
        if (ComprobarConexion.comprobarConexion()) {

            //Se verifica que el campo de ingresar el dia no este vac'io
            if (dpk_diaSubasta.getValue() != null) {

                //Se verifica que el campo de ingresar la hora de inicio no este vac'io
                if (dpk_horaInicio.getTime() != null) {

                    //Se verifica que el campo de ingresar la hora de finalizaci'on no este vac'io
                    if (dpk_horaFin.getTime() != null) {

                        //Se verifica que se hayan selecionado productos
                        if (!listaProductosS.isEmpty()) {

                            //Se verifica que se hayan seleccionado participantes
                            if (!listaParticipantesS.isEmpty()) {

                                //Se corre el metodo de enviar correo por medio de herencia a Thread
                                //Esto para que siga funcionado la app mientras se env'ian los correos
                                //(no se pegue)
                                new EnviarCorre().start();
                                cambiarInvitacion();
                                Notifications.create().title(":D").text("Enviando Correos").showInformation();

                            } else {

                                lbl_errorParticipantes.setVisible(true);
                                lbl_errorParticipantes.setText("No se han seleccionado participantes");
                            }

                        } else {
                            lbl_errorProductos.setVisible(true);
                            lbl_errorProductos.setText("No han selecionado productos");
                        }

                    } else {
                        lbl_errorfin.setVisible(true);
                    }

                } else {
                    lbl_errorInicio.setVisible(true);
                }

            } else {
                lbl_errorDia.setVisible(true);
            }

        } else {
            Notifications.create().title("¡Ups!").text("No hay conección a internet").showError();
        }

    }//fin action

    public void enviarCorreo() throws InterruptedException, ListaException, MessagingException, IOException, ClassNotFoundException {

        //Se optienen los valores de los campos de fecha
        diaSubasta = dpk_diaSubasta.getValue();
        horaInicio = dpk_horaInicio.getTime();
        horaFin = dpk_horaFin.getTime();
        List<RegistroInvitaciones> invitaciones = new ArrayList<RegistroInvitaciones>();

        //Se declaran los strings para el correo
        String productos = "";
        String asuntoCorreo = "TreasureHill by Leorans Auctions";

        //Se llena un String con los productos que se van a subastar para la creaci'on del registro
        for (int y = 1; y <= listaProductosS.getSize(); y++) {
            productos += "un(a) " + listaProductosS.getNodo(y).elemento + ", ";
        }

        /*Se recorre la lista de participantes obtener los datos de los
            participantes seleccionados*/
        for (int i = 1; i <= listaParticipantesS.getSize(); i++) {
            //se hace un registro de los participantes
            RegistroInvitaciones rInvitaciones
                    = new RegistroInvitaciones("" + listaParticipantesS.getNodo(i).elemento,
                            productos, "" + diaSubasta, "" + horaInicio, "" + horaFin);

            invitaciones.add(rInvitaciones);

            Object part = new Participantes();

            //Se obtienen los valores necesarios
            for (int j = 1; j <= listE.getSize(); j++) {
                part = listE.getNodo(j).elemento;
                Participantes p = (Participantes) part;

                if (p.getNombreUsuario().equalsIgnoreCase("" + listaParticipantesS.getNodo(i).elemento)) {
                    System.out.println("Si lo encontro");
                    String mensajeCorreo = "Hola señor(a) " + p.getNombre() + ", Tresure Hill"
                            + " te invita a probar tu suerte, en las subastas del día " + diaSubasta
                            + " la cual inicia a partir de las " + horaInicio + ", y finaliza a las " + horaFin
                            + " donde prodás obtener los productos " + productos + "\n"
                            + "¡Te esperamos! :)";
                    String destinatario = p.getCorreo().trim();

                    //Se env'ian los correos
                    EnviarCorreo.enviarConGMail(destinatario, asuntoCorreo, mensajeCorreo);
                }//if

            }//for

        }//for
        controlA.setNombre("RegistroInvitaciones.dat");
        controlA.escribirNuevo(invitaciones);
    }//fin m'etodo

    //metodo para cambiar el atributo invitaci'on a true de los participantes invitados
    public void cambiarInvitacion() throws IOException, ClassNotFoundException, ListaException {

        File file = new File(ruta + nombre);
        List<Participantes> array = new ArrayList<Participantes>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Participantes>) aux;

            for (int i = 1; i <= listaParticipantesS.getSize(); i++) {

                for (int j = 0; j < array.size(); j++) {
                    if (array.get(j).getNombreUsuario().equalsIgnoreCase(""
                            + listaParticipantesS.getNodo(i).elemento)) {

                        Participantes usuarios = new Participantes(array.get(j).getNombre(),
                                array.get(j).getCorreo(), array.get(j).getNombreUsuario(),
                                array.get(j).getContraseña(), array.get(j).getNumeroTelefono(), true);
                        array.remove(j);
                        array.add(usuarios);
                    }

                    objectInput.close();
                }

                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
                output.writeUnshared(array);

            }

        }//for

    }//fin m'etodo

    //Clase con el metodo abstracto run para la ejecion independiente de enviar correos 
    class EnviarCorre extends Thread {

        @Override
        public void run() {
            try {
                enviarCorreo();

            } catch (InterruptedException | ListaException | MessagingException | IOException | ClassNotFoundException ex) {
                Logger.getLogger(InterfazSubModuloInvitarParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//fin class

    //Se crea un objeto de "relleno" para poder utilizar en la tabla
    //ya que esta ocupa valores SimpleProperty
    class Añadidos extends RecursiveTreeObject<Añadidos> {

        //atributos
        SimpleStringProperty nombreUsuario;
        SimpleStringProperty producto;

        public Añadidos(String nombreUsuario, String producto) {
            this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
            this.producto = new SimpleStringProperty(producto);
        }//fin constructor

    }//fin añadidos  

}//fin class

