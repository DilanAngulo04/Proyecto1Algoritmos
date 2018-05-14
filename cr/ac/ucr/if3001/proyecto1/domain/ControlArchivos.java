package cr.ac.ucr.if3001.proyecto1.domain;

import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ControlArchivos {

    private String nombre;
    private String ruta;

    //Constructor
    public ControlArchivos() {
        this.ruta = "src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void escribir(Object objeto) throws IOException, ClassNotFoundException {

        File studentFile = new File(ruta + nombre);
        List<Object> array = new ArrayList<Object>();

        //Validaci'on
        if (studentFile.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Object>) aux;
            objectInput.close();
        }

        array.add(objeto);

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
        output.writeUnshared(array);

        output.close();
    }//Fin del m'etodo

    //M'etodo para leer la lista de estudiantes (lista que ha sido serializada)
    public List<Object> readList() throws IOException, ClassNotFoundException {

        File studenFile = new File(ruta + nombre);
        List<Object> studentArray = new ArrayList<Object>();

        //Validaci'on
        if (studenFile.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            studentArray = (List<Object>) aux;
            objectInput.close();
        }

        return studentArray;
    }//Fin del m'etodo

    public ListaEnlazada cargarLista() throws IOException, ClassNotFoundException, ListaException {

        ListaEnlazada listaE = new ListaEnlazada();
        for(int i = 0; i < readList().size(); i++){
            listaE.insertar(readList().get(i));
        }
        
        return listaE;
    }//fin m'etodo
    
    public void eliminar(Object objeto) throws IOException, ClassNotFoundException {

        File studentFile = new File(ruta + nombre);
        List<Object> array = new ArrayList<Object>();

        //Validaci'on
        if (studentFile.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Object>) aux;
            for(int i = 0; i < array.size(); i++){
                if(Utilidades.igualQ(array.get(i), objeto)){
                    array.remove(i);
                }
            }
            
            objectInput.close();
        }
        
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
        output.writeUnshared(array);
        
    }//Fin del m'etodo

}//fin class
