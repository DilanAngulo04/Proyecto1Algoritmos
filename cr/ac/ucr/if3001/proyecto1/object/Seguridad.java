package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Seguridad implements Serializable{

    //atributos
    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String contraseña;

    //contructores
    public Seguridad(String nombre, String correo, String nombreUsuario, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public Seguridad() {
        this.nombre = "";
        this.correo = "";
        this.nombreUsuario = "";
        this.contraseña = "";
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
    //fin setters and getters
    
    

    @Override
    public String toString() {
        return "Seguridad {" + "Nombre=" + nombre + ", Correo=" + correo 
                + ", Nombre de Usuario = " + nombreUsuario + ", Contrase\u00f1a=" + contraseña + '}';
    }//fin toString

}//fin class
