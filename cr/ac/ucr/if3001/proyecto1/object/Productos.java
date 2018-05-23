package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;
import java.nio.file.Path;
//Modificado
public class Productos extends Material implements Serializable{
    
    //contructores
    public Productos(String nombre, int precio, String tipo, String descripcion, int cantidad ,String pathImage) {
        super(nombre, precio, tipo, descripcion, cantidad ,pathImage);
    }

    public Productos() {
        super();
    }
    //fin contructores
    
}//fin class
