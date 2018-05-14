package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Seguridad implements Serializable{

    //atributos
    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String contraseña; 
    private int numeroTelefono;

    //contructores
    public Seguridad(String nombre, String correo, String nombreUsuario, String contraseña, int numeroTelefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.numeroTelefono = numeroTelefono;
    }

    public Seguridad() {
        this.nombre = "";
        this.correo = "";
        this.nombreUsuario = "";
        this.contraseña = "";
        this.numeroTelefono = 0;
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


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    
    //fin setters and getters

    
    
    

    @Override
    public String toString() {
        return "Seguridad {" + "Nombre=" + nombre + ", Correo=" + correo 
                + ", Nombre de Usuario = " + nombreUsuario + ", Contrase\u00f1a=" + contraseña 
                + ", Numero de Teléfono = " + numeroTelefono + '}';
    }//fin toString

}//fin class
