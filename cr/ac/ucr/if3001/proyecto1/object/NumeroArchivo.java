package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;


public class NumeroArchivo implements Serializable{
    private int numeroArchivo;

    public NumeroArchivo(int numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    public NumeroArchivo() {
        this.numeroArchivo = 0;
    }

    public int getNumeroArchivo() {
        return numeroArchivo;
    }

    public void setNumeroArchivo(int numeroArchivo) {
        this.numeroArchivo = numeroArchivo;
    }

    @Override
    public String toString() {
        return "Numero de Archivo{" + "Nuumero Archivo=" + numeroArchivo + '}';
    }
    
}
