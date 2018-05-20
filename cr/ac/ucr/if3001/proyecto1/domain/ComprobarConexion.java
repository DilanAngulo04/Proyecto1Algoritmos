package cr.ac.ucr.if3001.proyecto1.domain;

import java.net.Socket;

public class ComprobarConexion {

    public static boolean comprobarConexion() {
        String dirWeb = "www.youtube.com";//link de prueba para establecer una coneccion en internet
        int puerto = 80;//se establece un puerto para la coneccion

        try {
            Socket s = new Socket(dirWeb, puerto);//Objeto
            if (s.isConnected()) { //se comprueba si está conectado
               return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }//fin m'etodo

}//fin class
