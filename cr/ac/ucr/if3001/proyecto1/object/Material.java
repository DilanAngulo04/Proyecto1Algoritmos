/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.object;

/**
 *
 * @author dilan_000
 */
public class Material {

    //atributos
    private String nombre;
//    
    private int precio;
    private String tipo;
    private String descripcion;

    //constructor vacio 
    public Material() {
        this.nombre = "";
        
        this.precio = 0;
        this.tipo = "";
        this.descripcion = "";
    }

    //constructor normal
    public Material(String nombre, int precio, String tipo, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.descripcion = descripcion;
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
    //fin setters and getters

    //toString
    @Override
    public String toString() {
        return "Material {" + "Nombre=" + nombre + ", Precio=" + precio + ", Tipo=" + tipo + ", Descripcion=" + descripcion + '}';
    }//fin toString

}//fin class
