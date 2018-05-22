package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Participantes extends Seguridad implements Serializable{
    
    //atributos
    private boolean invitacion;

    public Participantes(String nombre, String correo, String nombreUsuario, String contraseña, 
            int numeroTelefono, boolean invitacion) {
        super(nombre, correo, nombreUsuario, contraseña, numeroTelefono);
        this.invitacion = invitacion;
    }   

    public Participantes() {
        super();
        invitacion = false;
    }    
    //fin contructores

    public boolean isInvitacion() {
        return invitacion;
    }

    public void setInvitacion(boolean invitacion) {
        this.invitacion = invitacion;
    }
    //Fin setters and getters   

    @Override
    public String toString() {
        return "Participantes {" + " Invitación = " + invitacion + '}';
    }
    //fin toString

}//fin class
