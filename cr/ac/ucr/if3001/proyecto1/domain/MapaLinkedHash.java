/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 *
 * @author Maria
 */
public class MapaLinkedHash implements Serializable {
    public LinkedHashMap<String, Object> lista;
    private static final long serialVersionUID=-1204159794749022321L;

    public MapaLinkedHash() {
        this.lista = new LinkedHashMap<>();
    }
    
    public LinkedHashMap<String, Object> getLista() {
        return lista;
    }

    public void setLista(LinkedHashMap<String, Object> lista) {
        this.lista = lista;
    }
    private boolean isEmpty(){
        return lista.isEmpty();
    }
    
//    @Override
    public void insertar(String date, Object objeto){
        if(!lista.containsKey(date)|| lista.isEmpty()){
            lista.put(date, objeto);
        }
    }
        
//    @Override
    public boolean buscar(String date){
        return lista.containsKey(date);
    }

//    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        String result= "Mapa Linked Hash";
                Iterator<String> bitacora = lista.keySet().iterator();
        
            while(bitacora.hasNext()){
                String date= bitacora.next();
                Object objeto= lista.get(date);
                result+= "\n "+date+" , "+objeto.toString();

            }   
            return result;
    }
    


}
