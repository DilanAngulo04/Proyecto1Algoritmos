package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.Bitacora;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaCircularEnlazadaDoble;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Material;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;

public class InterfazSubModuloModificarProductosController implements Initializable {

    //instancias necesarias
    ControlArchivos controlA = new ControlArchivos();
    Bitacora controlB= new Bitacora();
    ListaCircularEnlazadaDoble lista = new ListaCircularEnlazadaDoble();
    String ruta = "src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\";
    String nombre = "Material.dat";
    String productoBuscar;
    String path="";
    

    @FXML
    private Label lb_nombre;
    @FXML
    private Label lb_costo;
    @FXML
    private Label lb_categoria;
    @FXML
    private RadioButton tb_modificar;
    @FXML
    private Button btn_eliminar;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_costo;
    @FXML
    private TextField txt_buscar;
    @FXML
    private AnchorPane anp_modificar;
    @FXML
    private Label lb_excepcion;
    @FXML
    private Label lb_descripcion;
    @FXML
    private Button btn_modificar;
    @FXML
    private TextField txt_descripcion;
    @FXML
    private Label lb_modifExcepcion;
    @FXML
    private ImageView iv_nuevaImagen;
    @FXML
    private Button btn_nuevaImagen;
    @FXML
    private ComboBox<String> cb_categoria;
    @FXML
    private ComboBox<Integer> cb_cantidad;
    @FXML
    private Label lb_cantidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_modificar.setVisible(false);
        try {
            TextFields.bindAutoCompletion(txt_buscar, controlA.getNombres());
            cb_categoria.getItems().addAll("Servicios", "Productos");
            cb_cantidad.getItems().addAll(1, 2, 3, 4, 5);
        } catch (ListaException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloModificarProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //Mostrar opciones de modificar
    @FXML
    private void modificar(ActionEvent event) {
        anp_modificar.setVisible(true);
            
    }//fin modificar
    
    //Eliminar un registro material
    @FXML
    private void eliminar(ActionEvent event) throws IOException, ClassNotFoundException, ListaException {
        File file = new File(ruta + nombre);
        List<Material> array = new ArrayList<>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Material>) aux;

            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getNombre().equals(productoBuscar) && array.get(i).getCantidad()==1) {
                    
                //Control Bitacora
                array.remove(i);
                    
                }else if(array.get(i).getNombre().equals(productoBuscar) && array.get(i).getCantidad()>1){
                    array.get(i).setCantidad(array.get(i).getCantidad()-1);
                }

                objectInput.close();
            }

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
            output.writeUnshared(array);

        }
                
        lb_excepcion.setText("Producto "+ productoBuscar+ " ha sido eliminado");
        limpiarCampos();

    }// fin eliminar
    
    //Accion enter
    @FXML
    private void buscar(ActionEvent event) throws ListaException, IOException, ClassNotFoundException {
        productoBuscar = txt_buscar.getText().trim();
        lb_excepcion.setText("");
        lb_modifExcepcion.setText("");
        verificar();
    }//fin buscar

    //Verificar si existe el material en el archivo
    public boolean verificar() throws ListaException, IOException, ClassNotFoundException {
        Object objeto = new Material();
        controlA.setNombre("Material.dat");
        lista = controlA.cargarListaCircularDobleEnlazada();

        //se verifica que la lista no esté vacía
        if (!lista.isEmpty()) {
            System.out.println(productoBuscar.length());

            //Se verifica que se hayan ingresado valores en el textfield
            if (productoBuscar.length() != 0) {

                //Se recorre el archivo para obtener los datos
                for (int i = 1; i <= lista.getSize(); i++) {
                    objeto = lista.getNodo(i).elemento;
                    Material producto = (Material) objeto;

                    //Se verifica que el valor ingresado coincida con el registrado
                    if (producto.getNombre().equalsIgnoreCase(productoBuscar)) {

                        lb_nombre.setText(producto.getNombre());
                        lb_costo.setText("" + producto.getPrecio());
                        lb_categoria.setText(producto.getTipo());
                        lb_descripcion.setText(producto.getDescripcion());
                        lb_cantidad.setText(""+producto.getCantidad());
                        
                        txt_nombre.setText(producto.getNombre());
                        txt_costo.setText(""+ producto.getPrecio());
                        txt_descripcion.setText("Bla"+producto.getDescripcion());
                        
                        //Validacion
                        if (!"".equals(producto.getPathImage())) {
                        BufferedImage image = ImageIO.read(new FileInputStream(new File(producto.getPathImage())));
                        Image img = SwingFXUtils.toFXImage(image, null);
                        iv_nuevaImagen.setImage(img);                            
                        }

                        return true;

                    }

                }
                lb_excepcion.setVisible(true);
                lb_excepcion.setText("-" + productoBuscar + "-" + " no se encuentra registrado");
            } else {

                lb_excepcion.setVisible(true);
                lb_excepcion.setText("No se ingresó ningún valor");

            }
        }
        
        return false;

    }//fin verificar 

    //Accion modificar
    @FXML
    public void handleModificar() throws FileNotFoundException, IOException, ClassNotFoundException, ListaException {

        String newNombre = txt_nombre.getText().trim();
        int newCosto = Integer.parseInt(txt_costo.getText().trim());
        String newTipo = cb_categoria.getValue();
        String newDescripcion = txt_descripcion.getText().trim();
        int newCantidad= cb_cantidad.getValue();

        File file = new File(ruta + nombre);
        List<Material> array = new ArrayList<>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Material>) aux;

            //Se verifica que no hayan campos sin llenar 
            if (newNombre.length() != 0 || newTipo.length() != 0 || txt_costo.getText().length() != 0) {
                //Se comprueba que sea ingrese un numero válido

                for (int i = 0; i < array.size(); i++) {

                    //Se comprueba que exista (no va pasar porque si est'a)
                    //Solo necesito la posicion dada por "i"
                    if (array.get(i).getNombre().equals(productoBuscar)) {
                        Material newMaterial = new Material(newNombre, newCosto, newTipo, newDescripcion, newCantidad, path);
                        
                        //Control Bitacora
                        //controlB.escribir(Calendar.getInstance().getTime().toString(), new Registro(array.get(i), "Modificacion"));
                        //System.out.println("LEER "+controlB.cargarBitacora().toString());                        
                        
                        array.remove(i);
                        array.add(newMaterial);

                        objectInput.close();
                        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
                        output.writeUnshared(array);

                    }

                }//fin for                            

            } else {
                lb_excepcion.setText("Hay campos sin llenar");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }
        
        lb_modifExcepcion.setText("Producto modificado");
        limpiarCampos();

    }//fin handleModificar

    //Cargar nueva imagen para el producto
    @FXML
    private void buscarImagen(ActionEvent event) {
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
            path= imgFile.getPath();
            
            //Mostrar imagen
            iv_nuevaImagen.setImage(image);
        }        
    }//fin buscarImagen
    
    public void limpiarCampos(){
        txt_buscar.clear();
        txt_nombre.clear();
        txt_costo.clear();
        txt_descripcion.clear();
        cb_cantidad.getSelectionModel().clearSelection();
        cb_categoria.getSelectionModel().clearSelection();
        iv_nuevaImagen.setImage(null);
    }    

}