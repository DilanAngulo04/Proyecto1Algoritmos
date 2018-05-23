/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.form;

import com.sun.javafx.scene.control.ControlAcceleratorSupport;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import cr.ac.ucr.if3001.proyecto1.object.EventoSubasta;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class InterfazModuloGanadorController implements Initializable {
    @FXML
    private Label lbl_ganador;

    ControlArchivos controlA = new ControlArchivos();
    ControlArchivos controlB = new ControlArchivos();
    
    ListaEnlazada listaE = new ListaEnlazada();
    ListaEnlazada listaE2 = new ListaEnlazada();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            controlA.setNombre("Subastas.dat");
            controlB.setNombre("Participantes.dat");
            //Cargo las listas
            listaE = controlA.cargarLista();
            listaE2 = controlB.cargarLista();
            //Cargo el participante que quedo de primero
            EventoSubasta eventoAux = (EventoSubasta) listaE.ultimo();
            //pongo el nombre del ganador dentro del modulo
            lbl_ganador.setText(eventoAux.getNombre());
            
            Object elementoLista = new Object();
            Participantes pActual = new Participantes();
            //Busco en participantes el participante que concuerde con la puja ganadora
            for (int i = 1; i <= listaE2.getSize(); i++) {
                    elementoLista = listaE2.getNodo(i).elemento;
                    Participantes p = (Participantes) elementoLista;

                    if(p.getNombreUsuario().equalsIgnoreCase(Utilidades.getNombreUsuario())) {
                        pActual = p;
                    }
                }
            //suprime de la lista el ganador con el viejo ranking
            //luego lo modifica y le otorga un ranking ganador
            listaE2.suprimir(pActual);
            pActual.setRanking(pActual.getRanking()+1);
            listaE2.insertarOrdenado(pActual);
            controlB.escribir(pActual);
            
            } catch (IOException | ClassNotFoundException | ListaException ex) {
            Logger.getLogger(InterfazModuloGanadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
