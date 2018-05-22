
package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.EventoSubasta;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class ModuloMejoresPujasController implements Initializable { //pushear esta clase

    private ControlArchivos controlA = new ControlArchivos();
    private ListaEnlazada listaTop = new ListaEnlazada();
    
    @FXML
    private AnchorPane anchPane_3;
    @FXML
    private TextArea txaTop_3;
    @FXML
    private AnchorPane anchPane_1;
    @FXML
    private TextArea txaTop_1;
    @FXML
    private AnchorPane anchPane_2;
    @FXML
    private TextArea txaTop_2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void actualizaPuja(ActionEvent event) {
        //animacion de transicion en el primer nodo que es el nuevo
        FadeTransition ft = new FadeTransition(Duration.millis(900), anchPane_1);
        ft.setFromValue(2.0);
        ft.setToValue(0.1);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
 
        
        
        try {
            // TODO
            controlA.setNombre("Subastas.dat");
            listaTop = controlA.cargarLista();
            
            if(listaTop.getSize()>=0){
                EventoSubasta evSubAux = ((EventoSubasta) listaTop.ultimo());
                String top1 = "Usuario: "+evSubAux.getNombreUsuario()+"\nNombre: "+evSubAux.getNombre()+"\nMonto de la puja: "+evSubAux.getMontoPorPuja();
                ft.play();
                txaTop_1.setText(top1);
                System.out.println(top1);
            }
            
            if(listaTop.getSize()>=2){
                EventoSubasta evSubAux = ((EventoSubasta) listaTop.getNodo(listaTop.getSize()-1).elemento);
                String top2 = "Usuario: "+evSubAux.getNombreUsuario()+"\nNombre: "+evSubAux.getNombre()+"\nMonto de la puja: "+evSubAux.getMontoPorPuja();
                ft.play();
                txaTop_2.setText(top2);
                System.out.println(top2);
            }
            
            if(listaTop.getSize()>=3){
                EventoSubasta evSubAux = ((EventoSubasta) listaTop.getNodo(listaTop.getSize()-2).elemento);
                String top3 = "Usuario: "+evSubAux.getNombreUsuario()+"\nNombre: "+evSubAux.getNombre()+"\nMonto de la puja: "+evSubAux.getMontoPorPuja();
                ft.play();
                txaTop_3.setText(top3);
                System.out.println(top3);
            }
            
        } catch (IOException | ClassNotFoundException | ListaException ex) {
            Logger.getLogger(ModuloMejoresPujasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
