package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.Bitacora;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaCircularEnlazadaDoble;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Productos;
import cr.ac.ucr.if3001.proyecto1.object.Registro;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class InterfazModuloMaterialController implements Initializable {

    //instancias necesarias
    public static ListaCircularEnlazadaDoble lista = new ListaCircularEnlazadaDoble();
    private String path = "";
    private final ControlArchivos controlA = new ControlArchivos();
    String nombre; 

    @FXML
    private AnchorPane anp_root;
    @FXML
    private Button btn_registrar;
    @FXML
    private TextField tfd_nombre;
    @FXML
    private TextField tfd_costo;
    @FXML
    private TextArea ta_descripcion;
    @FXML
    private Button btn_cargar;
    @FXML
    private ImageView iv_Image;
    @FXML
    private Label lbl_registrado;
    @FXML
    private ComboBox<String> cb_categoria;
    @FXML
    private Label lb_numberFormat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
        cb_categoria.getItems().addAll("Servicios", "Productos");

    }//fin initialize

    //Accion de registrar un nuevo producto en el archivo
    @FXML
    private void registroProducto(ActionEvent event) throws IOException, ClassNotFoundException, ListaException {
        lbl_registrado.setText("");
        
        //Crear un producto
        int precio = 0;
            try {
                precio = Integer.parseInt(tfd_costo.getText().trim());
            } catch (NumberFormatException nfe) {
                lb_numberFormat.setText("Ingresar un número válido");
                tfd_costo.clear();
            }
        
        nombre = tfd_nombre.getText().trim();
        String tipo = cb_categoria.getValue();
        String descripcion = ta_descripcion.getText().trim();
        if (!existe()) {
            if (nombre.length() > 0 && descripcion.length() > 0 && tfd_costo.getText().length() > 0 && !tipo.isEmpty()) {

                Productos p = new Productos(nombre, precio, tipo, descripcion, path);

                //Escribir en archivo
                controlA.setNombre("Material.dat");
                controlA.escribir(p);
                
                //Control Bitacora
                //Bitacora.bitacora.insertar(Calendar.getInstance().getTime().toString(), new Registro(p, "Insercion"));
                //System.out.println("BITACORA "+Bitacora.bitacora.toString());
                
                lbl_registrado.setText("Producto registrado con éxito");

                //Cargar lista
                lista = controlA.cargarListaCircularDobleEnlazada();
            } else {
                lbl_registrado.setText("Hay campos sin llenar");
            }
        }else{
        lbl_registrado.setText(nombre+" ha sido registrado");
        }
        limpiarCampos();
        System.out.println(lista.toString());

    }//fin action
    
    //Cargar imagen para el producto
    @FXML
    private void cargarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            path = imgFile.getPath();

            //Mostrar imagen
            iv_Image.setImage(image);
        }
    }//fin cargarImagen
    
    //Validar existencia del producto
    public boolean existe() throws ListaException, IOException, ClassNotFoundException{
    
        for (int i = 0; i < controlA.getNombres().size(); i++) {
            if (controlA.getNombres().get(i).equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }//fin existe

    public void limpiarCampos(){
        tfd_nombre.clear();
        tfd_costo.clear();
        ta_descripcion.clear();
        iv_Image.setImage(null);
    }//fin limpiarCampos
    
}//fin class
