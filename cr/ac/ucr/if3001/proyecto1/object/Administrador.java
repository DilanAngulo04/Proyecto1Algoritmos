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
public class Administrador extends Seguridad{

    public Administrador(String nombre, String apellidos, String correo, String contraseña, String confirContreseña) {
        super(nombre, apellidos, correo, contraseña, confirContreseña);
    }

    public Administrador() {
        super();
    }
   
}
