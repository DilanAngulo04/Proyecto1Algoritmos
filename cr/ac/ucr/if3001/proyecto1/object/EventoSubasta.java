
package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;


public class EventoSubasta implements Serializable{ 
    //private static final long serialVersionUID = 1L;
    private String nombreUsuario;
    private String nombre;
    private int montoPorPuja;

    public EventoSubasta(String nombreUsuario, String nombre, int montoPorPuja) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.montoPorPuja = montoPorPuja;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMontoPorPuja() {
        return montoPorPuja;
    }

    public void setMontoPorPuja(int montoPorPuja) {
        this.montoPorPuja = montoPorPuja;
    }


    @Override
    public String toString() {
        return "Subasta: " + "Usuario: " + nombreUsuario + "\nNombre: " + nombre + "\nMonto: " + montoPorPuja +'.';
    }
    
    
}
