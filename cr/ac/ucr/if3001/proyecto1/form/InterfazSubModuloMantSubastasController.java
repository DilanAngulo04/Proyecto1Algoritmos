
package cr.ac.ucr.if3001.proyecto1.form;

import com.itextpdf.text.DocumentException;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import cr.ac.ucr.if3001.proyecto1.object.EventoSubasta;
import cr.ac.ucr.if3001.proyecto1.object.Material;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import cr.ac.ucr.if3001.proyecto1.domain.GenerarPDF;
import java.io.File;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;


public class InterfazSubModuloMantSubastasController implements Initializable {
    //instancias necesarias
    ControlArchivos controlA= new ControlArchivos();
    FilteredList filter;
    
    @FXML
    private TableView<EventoSubasta> tvw_verSubastas;
    @FXML
    private Button btn_pdfSub;
    @FXML
    private TableColumn<EventoSubasta, SimpleStringProperty> clm_usuario;
    @FXML
    private TableColumn<EventoSubasta, SimpleStringProperty> clm_nombre;
    @FXML
    private TableColumn<EventoSubasta, Integer> clm_monto;
    @FXML
    private AnchorPane anp_root;
    @FXML
    private TextField tfd_busca;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            anp_root.setOpacity(0);
            Utilidades.transition(anp_root);
            
            controlA.setNombre("Subastas.dat");
            ObservableList lista= FXCollections.observableArrayList(controlA.readList());
            
            clm_usuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
            clm_nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            clm_monto.setCellValueFactory(new PropertyValueFactory<>("montoPorPuja"));
            
            tvw_verSubastas.setItems(lista);
            
            filter= new FilteredList(lista, e->true);
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloMantSubastasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btn_buscar(ActionEvent event) {
        tfd_busca.textProperty().addListener((observable, oldValue, newValue) -> {
            
            filter.setPredicate((Predicate<EventoSubasta>) (EventoSubasta evento1)->{
            
                if (newValue.isEmpty() || newValue==null) {
                    return true;
                }else if(evento1.getNombre().contains(newValue)){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
    
            return false;
            });
            
            SortedList sort= new SortedList(filter);
            sort.comparatorProperty().bind(tvw_verSubastas.comparatorProperty());
            tvw_verSubastas.setItems(sort);
            
        });
    }

    @FXML
    private void btn_generaPDF(ActionEvent event) throws ListaException, DocumentException, IOException, ClassNotFoundException {
        Object part = new EventoSubasta();
        String datos = "Datos de las Subastas: \n\n";
        //Se optiene la ruta en la que se guardar'a el archivo
        String ruta = guardarArchivo(event);
        controlA.setNombre("Subastas.dat");
        ListaEnlazada listE = controlA.cargarLista();

        for (int i = 1; i <= listE.getSize(); i++) {
            part = listE.getNodo(i).elemento;
            EventoSubasta p = (EventoSubasta) part;

            //Se genera un String con la informaci'on de los participantes registrados           
            datos += "Nombre: " + p.getNombre() + ", Nombre de Usuario: " + p.getNombreUsuario()
                    + ", Monto por puja: " + p.getMontoPorPuja()+ "\n\n\n";
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
        //al finalizar
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
}
