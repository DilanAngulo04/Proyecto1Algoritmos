package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;


public class RegistroInvitaciones implements Serializable{
    private String nombreUsuario;
    private String productos;
    private String dia;
    private String horaIncio;
    private String horaFin;

    public RegistroInvitaciones( String nombreUsuario, String productos, String dia, String horaIncio, String horaFin) {

        this.nombreUsuario = nombreUsuario;
        this.productos = productos;
        this.dia = dia;
        this.horaIncio = horaIncio;
        this.horaFin = horaFin;
    }
    
     public RegistroInvitaciones() {
        this.nombreUsuario = "";
        this.productos = "";
        this.dia = "";
        this.horaIncio = "";
        this.horaFin = "";
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getProducto() {
        return productos;
    }

    public void setProducto(String producto) {
        this.productos = producto;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraIncio() {
        return horaIncio;
    }

    public void setHoraIncio(String horaIncio) {
        this.horaIncio = horaIncio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "RegistroInvitaciones {" + " Nombre de Usuario = " + nombreUsuario 
                + ", Dia = " + dia + ", Hora de Inicio = " + horaIncio + ", Hora Finalizacion=" + horaFin + '}';
    }
    
}//fin class
