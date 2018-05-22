/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.domain;

import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;

/**
 *
 * @author Maria
 */
public class ListaCircularEnlazadaDoble extends Utilidades implements Lista {
    private Nodo inicio; //apunta al inicio de la lista
    private Nodo fin; //apunta al ultimo nodo de la lista
    
    public ListaCircularEnlazadaDoble(){
        this.inicio=this.fin=null;
    }

    @Override
    public int getSize() throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        int contador=0;
        while(aux!=fin){
            contador++;
            aux = aux.sgte; //lo movemos al sgte nodo
        }
        return contador+1;//+1 pq cuente el ultimo
    }

    @Override
    public void anular() {
        inicio=fin=null;
    }

    @Override
    public boolean isEmpty() {
        return inicio==null;
    }

    @Override
    public int getPosicion(Object elemento) throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        int posicion=1;
        while(aux!=fin){
            if(igualQ(aux.elemento, elemento)){
                return posicion;
            }
            posicion++;
            aux = aux.sgte; //lo movemos al sgte nodo
        }
        //se sale cuando aux apunta a fin (al ultimo nodo)
        if(igualQ(aux.elemento, elemento)){
            return posicion;
        }
        return -1; //indica q el elemento no existe
    }

    @Override
    public void insertar(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if(isEmpty()){
            inicio=fin=nuevoNodo;
        }
        else{
            fin.sgte = nuevoNodo;
            //hago el doble enlace
            nuevoNodo.ant = fin;
            fin=nuevoNodo;
            
            //hago el enlace circular
            fin.sgte=inicio;
            //hago el doble enlace
            inicio.ant = fin;
        }
    }

    @Override
    public void insertarOrdenado(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        //caso1. La lista esta vacia
        if(isEmpty()){
            inicio = nuevoNodo;
        }
        else{
            //caso2. inicio.sgte sea nulo
            //o incio.sgte no sea nulo
            //y el elemento a insertar es menor al del inicio 
            if(mayorQ(inicio.elemento, elemento)){
               nuevoNodo.sgte = inicio;
               //hago el doble enlace
               inicio.ant = nuevoNodo;
               inicio = nuevoNodo;
            }
            //para todos los otros casos
            else{
                Nodo auxAnt = inicio;
                Nodo aux = inicio.sgte;
                boolean insertado=false;
                while(aux!=fin&&!insertado){
                    if(!mayorQ(elemento, aux.elemento)){
                        auxAnt.sgte = nuevoNodo;
                        //hago el doble enlace
                        nuevoNodo.ant = auxAnt;
                        nuevoNodo.sgte = aux;
                        //hago el doble enlace
                        aux.ant = nuevoNodo;
                        insertado=true;
                    }
                    auxAnt=aux;
                    aux=aux.sgte;
                }
                
                //se sale cuando aux=fin
                //se enlaza al final
                if(!mayorQ(elemento, aux.elemento)&&!insertado){
                    auxAnt.sgte = nuevoNodo;
                    //hago el doble enlace
                    nuevoNodo.ant = auxAnt;
                    nuevoNodo.sgte = aux;
                    //hago el doble enlace
                    aux.ant = nuevoNodo;
                }//if
                else{ //se enlaza al final
                    aux.sgte = nuevoNodo;
                    //hago el doble enlace
                    nuevoNodo.ant = aux;
                    //muevo fin al ultimo nodo
                    fin = nuevoNodo;
                }//else
            }//else
        }//else
        //hago el enlace circular
        fin.sgte = inicio;
    }

    @Override
    public void suprimir(Object elemento) throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        //caso1. El elemento a suprimir es el primero
        if(igualQ(inicio.elemento, elemento)){
            inicio = inicio.sgte;
            inicio.ant = null; //para que no quede apuntando al nodo suprimido
        }
        //caso2. Elemento puede estar en medio o al final
        else{
            Nodo auxAnt = inicio;
            Nodo aux = inicio.sgte;
            while(aux!=fin&&!igualQ(aux.elemento, elemento)){
                auxAnt = aux;
                aux = aux.sgte;
            }
            //se sale cuando aux==fin
            //o cuando encuentra el elemento a suprimir
            if(igualQ(aux.elemento, elemento)){
                //desenlanza el nodo
                auxAnt.sgte = aux.sgte;
                //mantengo el doble enlace
                if(aux.sgte!=null){
                    aux.sgte.ant = auxAnt;
                }
            }
            
            //debo asegurarme q fin apunte al ultimo nodo
            if(aux==fin){ //estamos en el ultimo nodo
                fin=auxAnt;
            }
        }
        //mantengo el enlace circular
        fin.sgte = inicio;
        
        //q pasa si solo queda un nodo
        //y es el q quiero eliminar
        if(inicio==fin&&igualQ(inicio.elemento, elemento)){
            anular(); //anulo la lista
        }
    }

    @Override
    public boolean existe(Object elemento) throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        while(aux!=fin){
            if(igualQ(aux.elemento, elemento)){
                return true;
            }
            aux = aux.sgte; //lo movemos al sgte nodo
        }
        //se sale cuando aux==fin       
        return igualQ(aux.elemento, elemento); 
    
    }

    @Override
    public Object primero() throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        return inicio.elemento;
    }

    @Override
    public Object ultimo() throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        return fin.elemento; //es el ultimo en la lista
    }

    @Override
    public void ordenar() throws ListaException {
        if(isEmpty())
            throw new ListaException("La lista esta vacia");
        Nodo auxAnt = inicio;
        Nodo aux1 = inicio;
        Nodo aux2 = inicio.sgte;
        //creo un nuevo nodo para duplicar el elemento en el nodo fin
        //utilizarlo como aux para ordenar todos los elementos
        Nodo nuevoNodo = new Nodo(fin.elemento);
        fin.sgte = nuevoNodo;
        nuevoNodo.sgte=inicio;
        fin=nuevoNodo;
        while(aux1!=fin){
            while(aux2!=fin){
                if(mayorQ(aux1.elemento, aux2.elemento)){
                    //El elemento del primer nodo es el apuntado por inicio
                    if(aux1==inicio){
                        inicio = cambiaEnlaces(inicio, aux2);
                        auxAnt=aux1=inicio; //para actualizar
                    }//if
                    else{ //significa que no es el primer nodo de la lista
                        aux1 = cambiaEnlaces(aux1, aux2);
                        auxAnt.sgte=aux1; //para mantener todo conectado
                    }//else
                    aux2=aux1; //actualizo enlaces
                }//if
                aux2 = aux2.sgte;
            }//while aux2
            auxAnt = aux1; //dejamos un rastro
            aux1 = aux1.sgte;
            if(aux1==null) break; //rompe el while aux1
            aux2 = aux1.sgte;
       }//while aux1
        
       //elimino el nuevoNodo
       Nodo aux=inicio;
       while(aux.sgte!=nuevoNodo){
           aux=aux.sgte;
       }
       //se sale cuando aux.sgte==nuevoNodo
       fin=aux;
       //hago el enlace circular
       fin.sgte=inicio;
    }
    
     private Nodo cambiaEnlaces(Nodo nodo1, Nodo nodo2){
        //si nodo1 y nodo2 son consecutivos
        if(nodo1.sgte==nodo2){
            nodo1.sgte = nodo2.sgte;
            //hago el doble enlace
            nodo2.sgte.ant = nodo1;
            nodo2.sgte = nodo1;
            //hago el doble enlace
            nodo1.ant = nodo2;
        }else{
            Nodo aux=nodo1;
            while(aux.sgte!=nodo2){
                aux=aux.sgte;
            }
            aux.sgte=nodo2.sgte;
            //hago el doble enlace
            nodo2.sgte.ant = aux;
            nodo2.sgte=nodo1;
            //hago el doble enlace
            nodo1.ant = nodo2;
        }
        return nodo2; 
    }

    @Override
    public Object anterior(Object elemento) throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        if(igualQ(inicio.elemento, elemento))
            return "Es el inicio, no tiene anterior";
        Nodo ant = inicio; //dejar un rastro
        Nodo aux = inicio.sgte;
        while(aux!=fin){
            if(igualQ(aux.elemento, elemento)){
                return ant.elemento; //el elemento anterior
            }
            ant = aux;
            aux = aux.sgte; //lo movemos al sgte nodo
        }
        
        //se sale cuando aux==fin
        if(igualQ(aux.elemento, elemento)){
            return ant.elemento; //el elemento anterior
        }
        return "El elemento no existe en la lista";
    }
    /**
    @Override
    public Object posterior(Object elemento) throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio; //dejar un rastro
        while(aux!=fin){
            if(igualQ(aux.elemento, elemento)){
                return aux.sgte.elemento; //el elemento posterior
            }
            aux = aux.sgte;  //lo movemos al sgte nodo
        }
        //se sale cuando aux==fin
        if(igualQ(aux.elemento, elemento)){
            return aux.sgte.elemento; //el elemento anterior
        }
        return "El elemento no existe en la lista";
    }
**/
    @Override
    public Nodo getNodo(int posicion) throws ListaException {
        if(isEmpty()){
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        int posLista = 1;
        while(aux!=fin){
            if(igualQ(posLista, posicion)){
                return aux;
            }
            posLista++;
            aux = aux.sgte; //lo movemos al sgte nodo
        }
        
        //se sale cuyando aux==fin
        if(igualQ(posLista, posicion)){
            return aux;
        }
        
        return null; //si llega aqui no encontro el nodo
    }

    @Override
    public String toString() {
        String result = "\nLista Circular Enlazada Doble\n";
        Nodo aux = inicio;
        int cont=1;
        while(aux!=fin){
            if(cont++>=30){
                result+="\n";
                cont=1;
            }
            result+=aux.elemento+", ";
            aux = aux.sgte; //lo movemos al sgte nodo
        }
        //se sale cuando aux==fin
        //agregamos la info del ultimo nodo
        return result+", "+aux.elemento;
    }
    
    
    
}

