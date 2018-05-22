/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

/**
 *
 * @author Maria
 */
public class Registro implements Serializable {
    
    private Object registro;
    private String variable;

    public Registro(Object registro, String variable) {
        this.registro = registro;
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "Registro{" + "registro=" + registro + ", variable=" + variable + '}';
    }

    
    
}

