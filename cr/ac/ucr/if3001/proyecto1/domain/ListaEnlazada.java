package cr.ac.ucr.if3001.proyecto1.domain;

import cr.ac.ucr.if3001.proyecto1.exception.ListaException;

public class ListaEnlazada implements Lista {

    Nodo inicio;

    public ListaEnlazada() {
        this.inicio = null;
    }

    @Override
    public int getSize() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La pila esta vacia");
        }
        Nodo aux = inicio;
        int contador = 0;
        while (aux != null) {
            contador++;
            aux = aux.sgte;//Lo movemos al siguiente nodo
        }
        return contador;
    }

    @Override
    public void anular() {
        inicio = null;
    }

    @Override
    public boolean isEmpty() {
        return inicio == null;
    }

    @Override
    //**
    public int getPosicion(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        int posicion = 1;
        while (aux != null) {
            if (igualQ(aux.elemento, elemento)) {
                return posicion;
            }
            aux = aux.sgte;//Lo movemos al siguiente nodo
            posicion++;
        }
        return -1;//Indica que el elemento no eiste 
    }

    public boolean igualQ(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            Integer x = (Integer) a;
            Integer x2 = (Integer) b;
            return x == x2;
        }

        if (a instanceof String && b instanceof String) {
            String s = (String) a;
            String s2 = (String) b;
            return s.equalsIgnoreCase(s2);
        }
        return false;
    }

    @Override
    public void insertar(Object elemento) {
        Nodo nuevoNodo = new Nodo(elemento);
        if (isEmpty()) {
            inicio = nuevoNodo;
        } else {
            Nodo aux = inicio;
            while (aux.sgte != null) {
                aux = aux.sgte;
            }
            aux.sgte = nuevoNodo;
        }
    }

    @Override
    public void insertarOrdenado(Object elemento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void suprimir(Object elemento) throws ListaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La pila esta vacia");
        }
        Nodo aux = inicio;
        int posicion = 1;
        while (aux != null) {
            if (igualQ(aux.elemento, elemento)) {
                return true;
            }
            aux = aux.sgte;//Lo movemos al siguiente nodo
        }

        return false;//Indica que el elemento no eiste 
    }

    @Override
    public Object primero() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La pila esta vacia");
        }
        return inicio.elemento;
    }

    @Override
    public Object ultimo() throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        while (aux.sgte != null) {
            aux = aux.sgte;//Lo movemos al siguiente nodo
        }
        return aux.elemento;
    }

    @Override
    public void ordenar() throws ListaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object anterior(Object elemento) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        if (igualQ(inicio.elemento, elemento)) {
            return "Es el inicio, no tiene anterior";
        }
        Nodo aux = inicio.sgte;//dejar un rastro
        Nodo ant = inicio;
        while (aux != null) {
            if (igualQ(aux.elemento, elemento)) {
                return ant.elemento;
            } else {
                ant = aux;
                aux = aux.sgte;
            }

        }
        return "El elemento no existe en la lista";
    }

    @Override
    public Object getNodo(int posicion) throws ListaException {
        if (isEmpty()) {
            throw new ListaException("La lista esta vacia");
        }
        Nodo aux = inicio;
        int posLista = 1;
        while (aux != null) {
            if (igualQ(posLista, posicion)) {
                return aux.elemento;
            }
            posLista++;
            aux = aux.sgte;//lo movemos al sgte nodo

        }
        return null;//si llega aqui no encontro el elemento
    }

    @Override
    public String toString() {
        String result = "\nLista enlazada Simple";
        Nodo aux = inicio;
        while (aux != null) {
            result += aux.elemento + " ,";
            aux = aux.sgte;//Lo movemos al siguiente nodo
        }
        return result;
    }
}
