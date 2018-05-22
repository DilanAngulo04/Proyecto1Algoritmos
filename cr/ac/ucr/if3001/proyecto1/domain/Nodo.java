 package cr.ac.ucr.if3001.proyecto1.domain;

public class Nodo {
    public Object elemento; //objeto almacenado en el nodo
    public Nodo sgte; //apuntador al sgte nodo
    public Nodo ant; //apuntador al nodo anterior;

    //Constructor
    public Nodo(Object elemento){
        this.elemento = elemento;
        this.sgte = null; //el nuevo nodo apunta a nulo
    }

    public Nodo() {
        this.elemento = null;
        this.ant = this.sgte = null;
    } 
    
}//fin clase nodo