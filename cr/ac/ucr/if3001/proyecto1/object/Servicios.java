package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Servicios extends Material implements Serializable{
    
    //constructores
    public Servicios() {
        super();
    }

    public Servicios(String nombre, int precio, String tipo, String descripcion) {
        super(nombre,  precio, tipo, descripcion);
    }
    //fin contructores
    
}//fin class
