package cr.ac.ucr.if3001.proyecto1.domain;

import java.net.Socket;

public class ComprobarConexion {

    public void comprobarConexion() {
        String dirWeb = "www.youtube.com";//link de prueba para establecer una coneccion en internet
        int puerto = 80;//se establece un puerto para la coneccion

        try {
            Socket s = new Socket(dirWeb, puerto);//Objeto
            if (s.isConnected()) { //se comprueba si está conectado
                System.out.println("Conexión establecida con la dirección: " + dirWeb + " a travéz del puerto: " + puerto);
            }
        } catch (Exception e) {
            System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
        }
    }//fin m'etodo

}//fin class
