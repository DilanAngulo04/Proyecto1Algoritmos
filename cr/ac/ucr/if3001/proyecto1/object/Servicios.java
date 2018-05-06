/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

/**
 *
 * @author dilan_000
 */
public class Servicios extends Material implements Serializable{
    
    //constructores
    public Servicios() {
        super();
    }

    public Servicios(String nombre, int precio, String tipo, String descripcion) {
        super(nombre,  precio, tipo, descripcion);
    }
    
}//fin class
