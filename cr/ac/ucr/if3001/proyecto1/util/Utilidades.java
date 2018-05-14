package cr.ac.ucr.if3001.proyecto1.util;

import cr.ac.ucr.if3001.proyecto1.object.Administrador;
import cr.ac.ucr.if3001.proyecto1.object.Bienes;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.object.Productos;
import cr.ac.ucr.if3001.proyecto1.object.Servicios;
import cr.ac.ucr.if3001.proyecto1.object.Subastas;
import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Utilidades {
    
    public static Object instanciaDe(Object a) {
        boolean o;
        if (a instanceof Participantes) {
            a = new Participantes();
        }
        if (a instanceof Productos ) {
            a = new Productos();
        }
        if (a instanceof Servicios ) {
            a = new Servicios();
        }
        if (a instanceof Subastas) {
            a = new Subastas();
        }   
        if (a instanceof Bienes) {
            a = new Bienes();
        } 
        if (a instanceof Administrador) {
            a = new Administrador();
        }         
        return a;
    }//fin me'etodo   
    
    public static boolean igualQ(Object a, Object b) {
        boolean o = false;
        if (a instanceof Participantes && b instanceof Participantes) {
            Participantes x = (Participantes) a;
            Participantes y = (Participantes) b;
            o = x.equals(y);
        }
        if (a instanceof Productos && b instanceof Productos) {
            Productos p1 = (Productos) a;
            Productos p2 = (Productos) b;
            o = p1.equals(p2);
        }
        if (a instanceof Servicios && b instanceof Servicios) {
            Servicios s1 = (Servicios) a;
            Servicios s2 = (Servicios) b;
            o = s1.equals(s2);
        }
        if (a instanceof Subastas && b instanceof Subastas) {
            Subastas s1 = (Subastas) a;
            Subastas s2 = (Subastas) b;
            o = s1.equals(s2);
        }   
        if (a instanceof Bienes && b instanceof Bienes) {
            Bienes b1 = (Bienes) a;
            Bienes b2 = (Bienes) b;
            o = b1.equals(b2);
        } 
        if (a instanceof Administrador && b instanceof Administrador) {
            Administrador a1 = (Administrador) a;
            Administrador a2 = (Administrador) b;
            o = a1.equals(a2);
        } 
        
        return o;
    }//fin m'etodo
    
        public static void transition(AnchorPane anp_root){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anp_root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);       
        fadeTransition.play();
    }//fin transition();
        
        public static String formatTelefono(int numero){
            String numeroS = ""+numero;
            String formato = "";
            
            formato = numeroS.substring(0, 4) + "-" + numeroS.substring(4);
            
            return formato;
        }
    
}//fin class
