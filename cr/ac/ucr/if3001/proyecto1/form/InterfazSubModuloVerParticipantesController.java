package cr.ac.ucr.if3001.proyecto1.form;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.GenerarExcel;
import cr.ac.ucr.if3001.proyecto1.domain.GenerarPDF;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import jxl.write.WriteException;
import org.controlsfx.control.Notifications;

public class InterfazSubModuloVerParticipantesController implements Initializable {

    private ListaEnlazada listE = new ListaEnlazada();
    private final ControlArchivos controlA = new ControlArchivos();
    ObservableList<User> participantes = FXCollections.observableArrayList();

    @FXML
    private JFXTreeTableView<User> tbv_participantes;
    @FXML
    private JFXButton GenerarPDF;
    @FXML
    private JFXButton GenerarExcel;
    @FXML
    private AnchorPane anp_root;
    @FXML
    private JFXButton btn_mostrarArchivos;
    @FXML
    private TextField tfd_nombreUsuarioBuscar;
    @FXML
    private JFXButton btn_buscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Se muestra una transicion cuando se abre la pestaña
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);

        //Columna que muestra la informaci'on del nombre completo de los participantes registrados
        JFXTreeTableColumn<User, String> colunm = new JFXTreeTableColumn<>("Nombre Completo");
        colunm.setPrefWidth(150);
        colunm.setCellValueFactory((param) -> {
            return param.getValue().getValue().nombre;
        });

        //Columna que muestra la informaci'on del nombre de usuario de los participantes registrados
        JFXTreeTableColumn<User, String> colunm2 = new JFXTreeTableColumn<>("Nombre de Usuario");
        colunm2.setPrefWidth(200);
        colunm2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().nombreUsuario;
            }
        });

        //Columna que muestra la informaci'on del correo electr'onico de los participantes registrados
        JFXTreeTableColumn<User, String> colunm3 = new JFXTreeTableColumn<>("Correo Electrónico");
        colunm3.setPrefWidth(200);
        colunm3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().correo;
            }
        });

        //Columna que muestra la informaci'on del n'umero de tel'efono de los participantes registrados
        JFXTreeTableColumn<User, String> colunm4 = new JFXTreeTableColumn<>("Número de Teléfono");
        colunm4.setPrefWidth(200);
        colunm4.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().telefono;
            }
        });

        //Columna que muestra si el participante está invitado  a una puja en ese momento
//        JFXTreeTableColumn<User, String> colunm5 = new JFXTreeTableColumn<>("Invitación");
//        colunm5.setPrefWidth(200);
//        colunm5.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
//                return param.getValue().getValue().invitado;
//            }
//        });

        try {
            cargarTabla();
        } catch (ListaException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloVerParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final TreeItem<User> root = new RecursiveTreeItem<>(participantes, RecursiveTreeObject::getChildren);
        //Se agregan las columnas a la tabla
        tbv_participantes.getColumns().setAll(colunm, colunm2, colunm3, colunm4);
        tbv_participantes.setRoot(root);
        tbv_participantes.setShowRoot(false);
    }//fin initialize

    //Este metodo debe ir antes de User, de lo contrario tendrá errores
    public void cargarTabla() throws ListaException, IOException, ClassNotFoundException {
        Object part = new Participantes();
        //nombre del archivo
        controlA.setNombre("Participantes.dat");
        //Se carga la lista con los datos de los paraticipantes guardados en un archivo
        listE = controlA.cargarLista();
        if (!listE.isEmpty()) {

            //Se recorre la lista para obtener los datos de los participantes
            for (int i = 1; i <= listE.getSize(); i++) {
                part = listE.getNodo(i).elemento;
                Participantes p = (Participantes) part;
                participantes.add(new User(p.getNombre(), p.getNombreUsuario(), p.getCorreo(), Utilidades.formatTelefono(p.getNumeroTelefono())));
            }

        }//if
    }//Fin m'etodo

    //M'etodo para generar un archivo en formato .pdf
    @FXML
    private void GenerarPDF(ActionEvent event) throws ListaException, WriteException, DocumentException, IOException, ClassNotFoundException {
        Object part = new Participantes();
        String datos = "Datos de los participantes: \n\n";
        //Se optiene la ruta en la que se guardar'a el archivo
        String ruta = guardarArchivo(event);

        for (int i = 1; i <= listE.getSize(); i++) {
            part = listE.getNodo(i).elemento;
            Participantes p = (Participantes) part;

            //Se genera un String con la informaci'on de los participantes registrados           
            datos += "Nombre: " + p.getNombre() + ", Nombre de Usuario: " + p.getNombreUsuario()
                    + ", Correo Electrónico: " + p.getCorreo() + ", Número de Teléfono: " + Utilidades.formatTelefono(p.getNumeroTelefono())
                    + "\n\n\n";
        }//fin for

        GenerarPDF generarPdf = new GenerarPDF();
        generarPdf.setRuta(guardarArchivo(event));
        if (generarPdf.generar(datos)) {//Si se cumple se guarda en el archivo y se muestra la notificaci'on
            Notifications.create().title(":)").text("Se guardó el archivo").showInformation();
            controlA.setNombre("ArchivosPDF.dat");
            controlA.escribir(ruta + ".pdf");
        } else {//si no se cumple se muestra la notificaci'on de error
            Notifications.create().title("¡Ups!").text("Problemas al guardar el archivo").showError();
        }
    }//fin action

    //M'etodo para generar un archivo excel en una ruta especificado por el usuario
    @FXML
    private void GenerarExcel(ActionEvent event) throws WriteException, ListaException, IOException, ClassNotFoundException {

        Object part = new Participantes();
        //Se necesita una matriz para poder llenar las celdas del archivo excel
        String[][] excel = new String[listE.getSize()][5];
        int fila = 0;
        //Se obtiene el valor de la ruta
        String ruta = guardarArchivo(event);

        for (int i = 1; i <= listE.getSize(); i++) {
            part = listE.getNodo(i).elemento;
            Participantes p = (Participantes) part;

            //Se el i-1 va a ser el valor de las columnas en la matriz
            excel[i - 1][fila] = p.getNombre();
            excel[i - 1][++fila] = p.getNombreUsuario();
            excel[i - 1][++fila] = p.getCorreo();
            excel[i - 1][++fila] = Utilidades.formatTelefono(p.getNumeroTelefono());

            //Se reinicia el valor de las filas
            fila = 0;
        }//for

        GenerarExcel generar = new GenerarExcel();
        if (generar.generar(excel, ruta, "Participantes")) {//Si se cumple se guarda el nombre en el archivo y se muestra la notificaci'on
            controlA.setNombre("ArchivosExcel.dat");
            controlA.escribir(ruta + ".xls");
            Notifications.create().title(":)").text("Se guardó el archivo").showInformation();
        } else {//Se muestra la notificacion de error
            Notifications.create().title("¡Ups!").text("Problemas al guardar el archivo").showError();
        }
    }//fin action

    //filechooser para obtener la ruta en la cual el archivo ser'a guardado
    private String guardarArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Archivo");
        String ruta = "";

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showSaveDialog(null);

        if (imgFile != null) {
            //Se obtiene la ruta de archivo
            ruta = imgFile.getAbsolutePath();
        }
        return ruta;
    }//fin m'etodo   

    @FXML
    private void btn_mostrarArchivos(ActionEvent event) {
    }

    @FXML
    private void btn_buscar(ActionEvent event) {
    }

    class User extends RecursiveTreeObject<User> {

        //Atributos de la clase auxiliar para mostrar informaci'on en la tabla
        SimpleStringProperty nombre;
        SimpleStringProperty nombreUsuario;
        SimpleStringProperty correo;
        SimpleStringProperty telefono;

        //Constructor
        public User(String nombre, String nombreUsuario, String correo, String telefono) {
            this.nombre = new SimpleStringProperty(nombre);
            this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
            this.correo = new SimpleStringProperty(correo);
            this.telefono = new SimpleStringProperty(telefono);
        }
        //fin constructor

    }//fin class

}//fin class
