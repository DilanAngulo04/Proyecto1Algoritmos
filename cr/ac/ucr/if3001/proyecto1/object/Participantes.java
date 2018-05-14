package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Participantes extends Seguridad implements Serializable{

    public Participantes(String nombre, String correo, String nombreUsuario, String contraseña, int numeroTelefono) {
        super(nombre, correo, nombreUsuario, contraseña, numeroTelefono);
    }   

    public Participantes() {
        super();
    }    

}//fin class
