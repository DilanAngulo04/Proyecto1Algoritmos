package cr.ac.ucr.if3001.proyecto1.domain;

import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
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
    
    //permite escribir en un archivo
    //reemplaza el contenido del archivo
    public void escribirNuevo(Object objeto){
        try{
           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta+nombre));
           oos.writeUnshared(objeto);
           oos.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void escribir(Object objeto) throws IOException, ClassNotFoundException {

        File file = new File(ruta + nombre);
        List<Object> array = new ArrayList<Object>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readUnshared();

            array = (List<Object>) aux;
            objectInput.close();
        }

        array.add(objeto);

        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
        output.writeUnshared(array);

        output.close();
    }//Fin del m'etodo

    //M'etodo para leer la lista de objetos (lista que ha sido serializada)
    public List<Object> readList() throws IOException, ClassNotFoundException {

        File file = new File(ruta + nombre);
        List<Object> studentArray = new ArrayList<Object>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readUnshared();

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

}//fin class
