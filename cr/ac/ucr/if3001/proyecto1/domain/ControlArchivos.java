package cr.ac.ucr.if3001.proyecto1.domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ControlArchivos {
    private String nombre;
    private String ruta;
    
    //Constructor
    public ControlArchivos(){
        this.ruta = "C:\\Users\\dilan_000\\Desktop\\Proyecto1Algoritmos\\src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
    
    //permite escribir en un archivo
    public void escribir(Object objeto){
        try{
           ObjectOutputStream oos = new ObjectOutputStream(
           new FileOutputStream(ruta+nombre));
           oos.writeObject(objeto);
           oos.close();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //carga en tda el contenido de un archivo 
    public Object cargar(Object tda){
        Object aux=null;
        try{
          //se crea un objeto input stream
          ObjectInputStream ois = new ObjectInputStream
        (new FileInputStream(ruta+nombre));
          //lee el objeto
          aux = ois.readObject();
          ois.close();
        }catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return tda=aux;
    }

//    private String path;
//    private String name;
//    File file = new File(path + name);
//
//    public ControlArchivos() {
//        this.path = "C:\\Users\\dilan_000\\Desktop\\Proyecto1Algoritmos\\src\\cr\\ac\\ucr\\if3001\\proyecto1\\file \\";
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    //Ingresar en el archivo
//    public void escribir(Object s) throws IOException, ClassNotFoundException {
//        File archivo = new File(path + name);
//
//        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path + name));
//        output.writeUnshared(s);
//        output.close();
//    }
//
//    //M'etodo para serializar la lista de objetos
//    public void escribirLista(Object participante) throws IOException, ClassNotFoundException {
//
//        participante = Utilidades.instanciaDe(participante);
//        File archivo = new File(path + name);
//        List<Object> studentArray = new ArrayList<Object>();
//
//        //Validaci'on
//        if (archivo.exists()) {
//            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(path + name));
//            Object aux = objectInput.readObject();
//
//            studentArray = (List<Object>) aux;
//            objectInput.close();
//        }
//
//        studentArray.add(participante);
//
//        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path + name));
//        output.writeUnshared(studentArray);
//
//        output.close();
//    }//Fin del m'etodo
//
//    public Object cargar(Object tda) {
//        Object aux = null;
//        try {
//            //se crea un objeto input stream
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + name));
//            //lee el objeto
//            aux = ois.readObject();
//            ois.close();
//        } catch (IOException | ClassNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return tda = aux;
//    }

    //M'etodo para leer la lista de objetos (lista que ha sido serializada)
//    public List<Object> readList() throws IOException, ClassNotFoundException {
//
//        File studenFile = new File(path + name);
//        List<Object> studentArray = new ArrayList<Object>();
//
//        //Validaci'on
//        if (studenFile.exists()) {
//            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(path + name));
//            Object aux = objectInput.readObject();
//
//            studentArray = (List<Object>) aux;
//            objectInput.close();
//        }
//
//        return studentArray;
//    }//Fin del m'etodo
//    //Retorna la informaci'on del participante para prueba
//    public String studentInfo(String idStudent) throws FileNotFoundException, ClassNotFoundException, OptionalDataException, IOException {
//        String info = "";
//
//        for (int i = 0; i < readList().size(); i++) {
//            if (readList().get(i).get.equalsIgnoreCase(idStudent)) {
//                info = "Student Data:\n\nName: " + readList().get(i).getName()
//                        + "\nEntry year: " + readList().get(i).getEntryYear()
//                        + "\nCareer: " + readList().get(i).getCareer()
//                        + "\nPhone Number: " + readList().get(i).getPhoneNumber();
//            }
//        }//for
//        return info;
//    }//Fin del m'etodo
//    public String getStudentId(String career, String year, int number) {
//        String id = String.valueOf(career.charAt(0)
//                + String.valueOf(year.charAt(3))
//                + String.format("%03d", number));
//
//        return id;
//    }
//    public boolean checkStudentRecord(String idStudent) throws IOException, ClassNotFoundException {
//
//        for (int i = 0; i < readList().size(); i++) {
//            if (readList().get(i).getId().equalsIgnoreCase(idStudent)) {
//                return true;
//            }
//        }//for
//        return false;
//    }
//    public String[] autocompleteOptions() throws IOException, ClassNotFoundException {
//
//        String[] options = new String[readList().size()];
//
//        for (int i = 0; i < readList().size(); i++) {
//            options[i] = readList().get(i).getId();
//        }
//
//        return options;
//    }
}
