/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaCircularEnlazadaDoble;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Material;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Maria
 */
public class InterfazSubModuloVerProductoController implements Initializable {

    //instancias necesarias
    ControlArchivos controlA= new ControlArchivos();
    FilteredList filter;  
    ListaCircularEnlazadaDoble lista= new ListaCircularEnlazadaDoble();
    
    @FXML
    private ListView<String> lv_productos;
    @FXML
    private AnchorPane ap_infoProducto;
    @FXML
    private ImageView iv_produto;
    @FXML
    private Label lb_costo;
    @FXML
    private Label lb_descripcion;
    @FXML
    private AnchorPane anp_infoProducto;
    @FXML
    private AnchorPane anp_root;
    @FXML
    private Label lb_cantidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
        try {
            ObservableList<String> productos= FXCollections.observableArrayList(getNombres());
            lv_productos.setItems(productos);
            anp_infoProducto.setVisible(false);
        } catch (ListaException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubModuloVerProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    //Obtener lista
    public List<String> getNombres() throws ListaException, IOException, ClassNotFoundException {
        
        List<String> nombres= new ArrayList<>();
        Object objeto = new Material();
        controlA.setNombre("Material.dat");
        lista= controlA.cargarListaCircularDobleEnlazada();
        
        if (!lista.isEmpty()) {
                //Se recorre el archivo para obtener los datos
                for (int i = 1; i <= lista.getSize(); i++) {
                    objeto = lista.getNodo(i).elemento;
                    Material producto = (Material) objeto;
                    nombres.add(producto.getNombre());            
        }

        }
        
        return nombres;

    }//fin getNombres
    
    //Mostrar la informacion del producto consultado
    @FXML
    public void getDetalles() throws ListaException, IOException, ClassNotFoundException {
        anp_infoProducto.setVisible(true);
        String opcion = lv_productos.getSelectionModel().getSelectedItem();

        List<String> nombres = new ArrayList<>();
        Object objeto = new Material();
        controlA.setNombre("Material.dat");
        lista = controlA.cargarListaCircularDobleEnlazada();

        if (!lista.isEmpty()) {
            //Se recorre el archivo para obtener los datos
            for (int i = 1; i <= lista.getSize(); i++) {
                objeto = lista.getNodo(i).elemento;
                Material producto = (Material) objeto;
                System.out.println(producto.toString());

                if (producto.getNombre().equalsIgnoreCase(opcion)) {
                    lb_costo.setText("Precio: " + producto.getPrecio());
                    lb_cantidad.setText("Cantidad: "+producto.getCantidad());
                    lb_descripcion.setText("Descripción: " + producto.getDescripcion());
                    
                    if(!"".equals(producto.getPathImage())){  
                    BufferedImage image = ImageIO.read(new FileInputStream(new File(producto.getPathImage())));
                    Image img = SwingFXUtils.toFXImage(image, null );
                    iv_produto.setImage(img);
                    }else{
                    iv_produto.setImage(null);
                    }
                }
            }
        }
    }//fin getDetalles

    
    public BufferedImage getScaledInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            //Object hint,
            boolean higherQuality) {
        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }    
}
