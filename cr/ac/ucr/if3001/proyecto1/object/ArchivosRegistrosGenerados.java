package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class ArchivosRegistrosGenerados implements Serializable{
    
    //Atributos
    private String nombreArchivo;

    //Constructores
    public ArchivosRegistrosGenerados(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
        
    public ArchivosRegistrosGenerados() {
        this.nombreArchivo = "";
    }

    //Setter and Getter
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String toString() {
        return "Archivos Generados {" + "Nombre del Archivo = " + nombreArchivo + '}';
    }//fin toString
    
}//fin class
