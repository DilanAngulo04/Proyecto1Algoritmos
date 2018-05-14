package cr.ac.ucr.if3001.proyecto1.object;

public class Administrador extends Seguridad{

    //contructores
    public Administrador(String nombre, String apellidos, String correo, String contraseña, int numeroTelefono) {
        super(nombre, apellidos, correo, contraseña, numeroTelefono);
    }

    public Administrador() {
        super();
    }
    //fin contructores
   
}//fin class
