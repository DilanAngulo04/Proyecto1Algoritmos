package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Bienes extends Material implements Serializable{

    //constructores
    public Bienes() {
        super();
    }

    public Bienes(String nombre, int precio, String tipo, String descripcion) {
        super(nombre, precio, tipo, descripcion);
    }
    //fin contructores
    
}//fin class
