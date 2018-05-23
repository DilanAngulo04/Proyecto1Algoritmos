package cr.ac.ucr.if3001.proyecto1.util;

import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Administrador;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.object.Productos;
import cr.ac.ucr.if3001.proyecto1.object.Subastas;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Utilidades {
    
    //Se intancia el atributo nombre de Usuario para obtener el nombre de la persona que ingresa al sistema
    private static String nombreUsuario;
    private static String nombreAdministrador;

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        Utilidades.nombreUsuario = nombreUsuario;
    }

    public static String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public static void setNombreAdministrador(String nombreAdministrador) {
        Utilidades.nombreAdministrador = nombreAdministrador;
    }    
    //fin setters and getters
    
    
    public static Object instanciaDe(Object a) {
        boolean o;
        if (a instanceof Participantes) {
            a = new Participantes();
        }
        if (a instanceof Productos) {
            a = new Productos();
        }

        if (a instanceof Subastas) {
            a = new Subastas();
        }

        if (a instanceof Administrador) {
            a = new Administrador();
        }
        return a;
    }//fin me'etodo   

    public static boolean igualQ(Object a, Object b) {
        boolean o = false;
        
        if (a instanceof Integer && b instanceof Integer) {
            Integer x = (Integer) a;
            Integer y = (Integer) b;
            o = x == y;
        }
        if (a instanceof String && b instanceof String) {
            String x = (String) a;
            String y = (String) b;
            o = x.equalsIgnoreCase(y);
        }
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
        if (a instanceof Subastas && b instanceof Subastas) {
            Subastas s1 = (Subastas) a;
            Subastas s2 = (Subastas) b;
            o = s1.equals(s2);
        }
        if (a instanceof Administrador && b instanceof Administrador) {
            Administrador a1 = (Administrador) a;
            Administrador a2 = (Administrador) b;
            o = a1.equals(a2);
        }

        return o;
    }//fin m'etodo

        public static boolean mayorQ(Object a, Object b){
        if(a instanceof Integer&&b instanceof Integer){
            int x = (Integer) a;
            int y = (Integer) b;
            return (x>y);
        }
        
        if(a instanceof String&&b instanceof String){
            String s1 = (String) a;
            String s3 = (String) b;
            
            return s1.length()>s3.length();
        }
        
//        if(a instanceof Vector&&b instanceof Vector){
//            Vector v1 = (Vector) a;
//            Vector v2 = (Vector) b;
//            
//            return v1.getArreglo().length>v2.getArreglo().length;
//        }
        if(a instanceof Float&&b instanceof Float){
            float f1 = (Float) a;
            float f2 = (Float) b;
            
            return f1>f2;
        }
        if(a instanceof Double&&b instanceof Double){
            double d1 = (Double) a;
            double d2 = (Double) b;
            
            return d1>d2;
        }
        if(a instanceof Integer&&b instanceof String){
            int x = (Integer) a;
            String y = (String) b;
            return x>y.length();
        }
        if(a instanceof String&&b instanceof Integer){
            String x = (String) a;
            int y = (Integer) b;
            return x.length()>y;
        }
        return false;
    }
    
    public static void transition(AnchorPane anp_root) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(anp_root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }//fin transition();

    public static String formatTelefono(int numero) {
        String numeroS = "" + numero;
        String formato = "";

        formato = numeroS.substring(0, 4) + "-" + numeroS.substring(4);

        return formato;
    }

    public static boolean verificarCorreo(String email) {

        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);
        return mather.find() == true;

    }
    

}//fin class
