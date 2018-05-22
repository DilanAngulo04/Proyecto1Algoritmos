/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.domain;

import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Maria
 */
public class Bitacora {
   
    private String ruta;

    //Constructor
    public Bitacora() {
        this.ruta = "src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\Bitacora.dat";
    }

    public void escribir(String date, Object objeto) throws IOException, ClassNotFoundException {

        File studentFile = new File(ruta);
        MapaLinkedHash array = new MapaLinkedHash();

        //Validaci'on
        if (studentFile.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta));
            Object aux = objectInput.readObject();

            array = (MapaLinkedHash) aux;
            objectInput.close();
        }

        array.insertar(date, objeto);

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta))) {
            output.writeUnshared(array);
        }
    }//Fin del m'etodo

    //M'etodo para leer la lista de estudiantes (lista que ha sido serializada)
    public MapaLinkedHash readList() throws IOException, ClassNotFoundException {

        File studenFile = new File(ruta);
        MapaLinkedHash studentArray = new MapaLinkedHash();

        //Validaci'on
        if (studenFile.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta));
            Object aux = objectInput.readObject();

            studentArray = (MapaLinkedHash) aux;
            objectInput.close();
        }

        return studentArray;
    }//Fin del m'etodo
    
    public MapaLinkedHash cargarBitacora() throws IOException, ClassNotFoundException, ListaException {

        MapaLinkedHash bitacora = new MapaLinkedHash();
        bitacora= readList();
        
        return bitacora;
    }//fin m'etodo 
        
}
