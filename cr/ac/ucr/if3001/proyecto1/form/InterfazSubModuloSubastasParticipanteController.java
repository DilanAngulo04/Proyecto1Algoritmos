
package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.EventoSubasta;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.object.Subastas;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;

public class InterfazSubModuloSubastasParticipanteController implements Initializable {

    private ControlArchivos controlA = new ControlArchivos();
    private ControlArchivos controlB = new ControlArchivos();
    private ListaEnlazada listaPart = new ListaEnlazada();
    private ListaEnlazada listaSub = new ListaEnlazada();
    int nuevaPuja;
    
    @FXML
    private Button btn_puja;
    @FXML
    private TextArea tfd_mejorPrecio;
    @FXML
    private TextField tfd_nuevaPuja;
    @FXML
    private Label lbl_errorPuja;
    @FXML
    private Label lbl_pujaMenor;
    @FXML
    private TextArea txa_info;
    @FXML
    private Label lbl_pujaExitosa;
    @FXML
    private AnchorPane anp_root;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
        
        try {
            // TODO
            controlA.setNombre("Participantes.dat");
            
            listaSub = controlB.cargarLista(); //listaConLasSubastas
            listaPart = controlA.cargarLista(); //ListaConlosParticipantes
            
        } catch (IOException | ClassNotFoundException | ListaException ex) {
            Logger.getLogger(InterfazSubModuloSubastasParticipanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void hazPuja(ActionEvent event) throws IOException, ClassNotFoundException, ListaException {

        try{
            nuevaPuja = Integer.parseInt(tfd_nuevaPuja.getText());
        
        Object elementoLista = new Object();
        Participantes pActual = new Participantes();
        
        if(listaSub.isEmpty()){
            //pregunta si la puja actual es mayor a la ultima puja realizada
            //Busca en la lista participantes el participante que tiene el mismo nombre de usuario
            //del participante para poder crear luego un objeto subasta en la lista enlazada
            if (!listaPart.isEmpty()) {

                for (int i = 1; i <= listaPart.getSize(); i++) {
                    elementoLista = listaPart.getNodo(i).elemento;
                    Participantes p = (Participantes) elementoLista;

                    if(p.getNombreUsuario().equalsIgnoreCase(Utilidades.getNombreUsuario())) {
                        pActual = p;
                    }
                }
            }
            lbl_pujaMenor.setVisible(false);
            lbl_errorPuja.setVisible(false);
            tfd_mejorPrecio.setText(""+nuevaPuja); //se agrega al text area el nuevoMejor 
            //crea un objeto subasta y lo inserta en la listaSub, listaEnlazada que guarda subastas
            EventoSubasta nuevaSubasta = new EventoSubasta(Utilidades.getNombreUsuario(), pActual.getNombre(), nuevaPuja);
            listaSub.insertarOrdenado(nuevaSubasta);
            controlB.setNombre("Subastas.dat");
            controlB.escribir(nuevaSubasta);
            System.out.println(listaSub.toString());
            //hacer que el label indique exito por un periodo de tiempo(segudos)
            lbl_pujaExitosa.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(2));
            visiblePause.setOnFinished(event2 -> lbl_pujaExitosa.setVisible(false));
            visiblePause.play();
        }else{
            EventoSubasta eventSubAux = (EventoSubasta) listaSub.ultimo();
                    
            if(eventSubAux.getMontoPorPuja()<nuevaPuja){
            //Busca en la lista participantes el participante que tiene el mismo nombre de usuario
            //del participante para poder crear luego un objeto subasta en la lista enlazada
            if (!listaPart.isEmpty()) {

                for (int i = 1; i <= listaPart.getSize(); i++) {
                    elementoLista = listaPart.getNodo(i).elemento;
                    Participantes p = (Participantes) elementoLista;

                    if(p.getNombreUsuario().equalsIgnoreCase(Utilidades.getNombreUsuario())) {
                        pActual = p;
                    }
                }
            }
            lbl_pujaMenor.setVisible(false);
            lbl_errorPuja.setVisible(false);
            tfd_mejorPrecio.setText(""+nuevaPuja);
            //crea un objeto subasta y lo inserta en la listaSub, listaEnlazada que guarda subastas
            EventoSubasta nuevaSubasta = new EventoSubasta(Utilidades.getNombreUsuario(), pActual.getNombre(), nuevaPuja);
            listaSub.insertarOrdenado(nuevaSubasta);
            controlB.setNombre("Subastas.dat");
            controlB.escribir(nuevaSubasta);
            System.out.println(listaSub.toString());
            lbl_pujaExitosa.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
            Duration.seconds(2));
            visiblePause.setOnFinished(event2 -> lbl_pujaExitosa.setVisible(false));
            visiblePause.play();
            }else{
                lbl_pujaMenor.setVisible(true);
            }
        }
        }catch(NumberFormatException nfe){
            lbl_errorPuja.setText("Use solo numeros");
            lbl_errorPuja.setVisible(true);
        }
    }
    
}
