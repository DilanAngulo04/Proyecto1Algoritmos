package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Material implements Serializable{
    
private static final long serialVersionUID=-1204159794749022321L;
    //atributos
    private String nombre;
    private int precio;
    private String tipo;
    private String descripcion;
    private String pathImage;
    private int cantidad;

    //constructor vacio 
    public Material() {
        this.nombre = "";        
        this.precio = 0;
        this.tipo = "";
        this.descripcion = "";
        this.pathImage= "";
        this.cantidad=0;
    }

    //constructor normal
    public Material(String nombre, int precio, String tipo, String descripcion,int cantidad,String pathImage) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.pathImage= pathImage;
        this.cantidad= cantidad;
    }//fin constructor

    //setter and getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
    
    //fin setters and getters

    //toString
    @Override
    public String toString() {
        return "Material{" + "nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + ", descripcion=" + descripcion + ", pathImage=" + pathImage + '}';
    }//fin toString

}//fin class
