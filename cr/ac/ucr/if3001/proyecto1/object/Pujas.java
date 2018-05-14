package cr.ac.ucr.if3001.proyecto1.object;


public class Pujas {
    private String nombreUsuario;

    public Pujas(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Pujas() {
        nombreUsuario = "";
    }
        
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "Pujas {" + "Nombre de Usuario = " + nombreUsuario + '}';
    }
    
}//fin class
