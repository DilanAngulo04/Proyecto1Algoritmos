/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.domain;

import java.util.Date;

/**
 *
 * @author Maria
 */
public interface Objecto {
  
    public void insertar(String date,Object producto);
    public boolean buscar(String date);
    //public boolean buscarN(String nombre);
    public void mostrar();
    public String toString();
    
    
}
