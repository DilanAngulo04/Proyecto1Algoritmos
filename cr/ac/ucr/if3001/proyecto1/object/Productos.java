package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Productos extends Material implements Serializable{
    
    //atributos
    private int cantidad;

    //contructores
    public Productos(String nombre, int precio, String tipo, String descripcion, int cantidad) {
        super(nombre, precio, tipo, descripcion);
        this.cantidad = cantidad;
    }

    public Productos() {
        super();
        this.cantidad = 0;
    }
    //fin contructores
    
}//fin class
