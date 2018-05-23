package cr.ac.ucr.if3001.proyecto1.form;

import com.itextpdf.text.DocumentException;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.GenerarExcel;
import cr.ac.ucr.if3001.proyecto1.domain.GenerarPDF;
import cr.ac.ucr.if3001.proyecto1.domain.ListaCircularEnlazadaDoble;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Material;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.File;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import jxl.write.WriteException;
import org.controlsfx.control.Notifications;

public class InterfazSubModuloConsultarElementoController implements Initializable {

    //instancias necesarias
    ControlArchivos controlA= new ControlArchivos();
    FilteredList filter;
    private ListaCircularEnlazadaDoble listaC = new ListaCircularEnlazadaDoble();
    
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
    @FXML
    private Button btn_pdf;
    @FXML
    private Button btn_excel;
    @FXML
    private AnchorPane anp_root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      anp_root.setOpacity(0);
       Utilidades.transition(anp_root);
        
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
            
            listaC= controlA.cargarListaCircularDobleEnlazada();
            
        } catch (IOException | ClassNotFoundException | ListaException ex) {
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

    @FXML
    private void generarPDF(ActionEvent event) throws ListaException, WriteException, IOException, ClassNotFoundException, DocumentException {
        Object objetos = new Material();
        String datos = "Datos de los participantes: \n\n";
        //Se optiene la ruta en la que se guardar'a el archivo
        String ruta = guardarArchivo(event);

        for (int i = 1; i <= listaC.getSize(); i++) {
            objetos = listaC.getNodo(i).elemento;
            Material material = (Material) objetos;

            //Se genera un String con la informaci'on de los participantes registrados           
            datos += "Nombre: " + material.getNombre() + ", Precio: " + material.getPrecio()
                    + ", Cantidad " + material.getCantidad() + ", Categoría " + material.getTipo() +", Descripción"+ material.getDescripcion()
                    + "\n\n\n";
        }//fin for

        GenerarPDF generarPdf = new GenerarPDF();
        generarPdf.setRuta(ruta);
        if (generarPdf.generar(datos)) {//Si se cumple se guarda en el archivo y se muestra la notificaci'on
            Notifications.create().title(":)").text("Se guardó el archivo").showInformation();
            controlA.setNombre("ArchivosPDF.dat");
            controlA.escribir(ruta + ".pdf");
        } else {//si no se cumple se muestra la notificaci'on de error
            Notifications.create().title("¡Ups!").text("Problemas al guardar el archivo").showError();
        }    
    }

    @FXML
    private void generarExcel(ActionEvent event) throws WriteException, IOException, ClassNotFoundException, ListaException {
        Object objeto = new Material();
        //Se necesita una matriz para poder llenar las celdas del archivo excel
        
        String[][] excel = new String[listaC.getSize()][9];
        int fila = 0;
        //Se obtiene el valor de la ruta
        String ruta = guardarArchivo(event);

        for (int i = 1; i <= listaC.getSize(); i++) {
            objeto = listaC.getNodo(i).elemento;
            Material material = (Material) objeto;

            //Se el i-1 va a ser el valor de las columnas en la matriz
            excel[i - 1][fila] = material.getNombre();
            excel[i - 1][++fila] = ""+material.getPrecio();
            excel[i - 1][++fila] = ""+material.getCantidad();
            excel[i - 1][++fila] = material.getTipo();
            excel[i - 1][++fila] = material.getDescripcion();

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
    }
 
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
    
}//fin class
