package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class InterfazSubModuloModificarParticipanteController implements Initializable {

    ControlArchivos controlA = new ControlArchivos();
    ListaEnlazada listaE = new ListaEnlazada();
    String ruta = "src\\cr\\ac\\ucr\\if3001\\proyecto1\\file\\";
    String nombre = "Participantes.dat";
    String nombreBuscar;

    @FXML
    private AnchorPane anp_root;
    @FXML
    private JFXButton btn_buscar;
    @FXML
    private Label lbl_nombreCompleto;
    @FXML
    private Label lbl_nombreUsuario;
    @FXML
    private Label lbl_correo;
    @FXML
    private Label lbl_numeroT;
    @FXML
    private JFXButton btn_modificarDatos;
    @FXML
    private Label lbl_dontFound;
    @FXML
    private TextField tfd_newNombre;
    @FXML
    private TextField tfd_newNombreUsuario;
    @FXML
    private TextField tfd_newEmail;
    @FXML
    private JFXButton btn_modificar;
    @FXML
    private JFXButton btn_eliminarParticipante;
    @FXML
    private TextField tfd_nombreBuscar;
    @FXML
    private Label lbl_insNombreC;
    @FXML
    private Label lbl_insCorreo;
    @FXML
    private Label lbl_insNombreU;
    @FXML
    private Label lbl_newNombreC;
    @FXML
    private Label lbl_newNUsuario;
    @FXML
    private Label lbl_newCorreo;
    @FXML
    private Label lbl_newTelefono;
    @FXML
    private TextField tfd_newTelefono;
    @FXML
    private Label ins_numeroT;
    @FXML
    private Label lbl_errorUsuario;
    @FXML
    private Label lbl_errorTelefono;
    @FXML
    private Label lbl_errorCorreo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);
    }//fin initialize

    @FXML
    private void handleBuscarParticipantes(ActionEvent event) throws ListaException, IOException, ClassNotFoundException {
        nombreBuscar = tfd_nombreBuscar.getText().trim();
        invisible();
        verificar();
    }

    @FXML
    private void handleEliminarParticipante(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {

        File file = new File(ruta + nombre);
        List<Participantes> array = new ArrayList<Participantes>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Participantes>) aux;

            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getNombreUsuario().equals(nombreBuscar)) {
                    array.remove(i);
                }

                objectInput.close();
            }

            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
            output.writeUnshared(array);
        }
    }//fin eliminares

    @FXML
    private void btn_modificarDatos(ActionEvent event) {
        lbl_newNUsuario.setVisible(true);
        lbl_newCorreo.setVisible(true);
        lbl_newNombreC.setVisible(true);
        lbl_newTelefono.setVisible(true);
        tfd_newNombreUsuario.setVisible(true);
        tfd_newEmail.setVisible(true);
        tfd_newNombre.setVisible(true);
        tfd_newTelefono.setVisible(true);
        btn_modificar.setVisible(true);

    }//fin action

    //Se modifican los valores y se vuelven a cargar
    @FXML
    private void handleModificar(ActionEvent event) throws IOException, ClassNotFoundException, ListaException {

        String newNombreU = tfd_newNombreUsuario.getText().trim();
        String newCorreo = tfd_newEmail.getText().trim();
        String newNombre = tfd_newNombre.getText().trim();
        String newTelfono = tfd_newTelefono.getText().trim();

        File file = new File(ruta + nombre);
        List<Participantes> array = new ArrayList<Participantes>();

        //Validaci'on
        if (file.exists()) {
            ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(ruta + nombre));
            Object aux = objectInput.readObject();

            array = (List<Participantes>) aux;

            //Se verifica que no hayan campos sin llenar 
            if (newCorreo.length() != 0 || newNombre.length() != 0
                    || newNombreU.length() != 0 || newTelfono.length() != 0) {
                if (Utilidades.verificarCorreo(newCorreo)) {
                    //Se comprueba que sea ingrese un numero válido
                    try {
                        for (int i = 0; i < array.size(); i++) {

                            //Se comprueba que exista (no va pasar porque si est'a)
                            //Solo necesito la posicion dada por "i"
                            if (array.get(i).getNombreUsuario().equals(nombreBuscar)) {
                                Participantes newParticipantes = new Participantes(newNombre, newCorreo, newNombreU,
                                        array.get(i).getContraseña(), Integer.parseInt(newTelfono),
                                        array.get(i).getMontosAdjudicados(),
                                        array.get(i).getRanking());
                                array.remove(i);
                                

                                //Se comprueba que se haya añadido de nuevo a la lista
                                if (array.add(newParticipantes)) {
                                    lbl_nombreCompleto.setText(newNombre);
                                    lbl_nombreUsuario.setText(newNombreU);
                                    lbl_correo.setText(newCorreo);
                                    lbl_numeroT.setText(Utilidades.formatTelefono(Integer.parseInt(newTelfono)));
                                    lbl_errorCorreo.setVisible(true);
                                    lbl_errorCorreo.setText("Se modificaron los datos");
                                } else {
                                    System.err.println("Error al insertar en la lista");
                                }

                                objectInput.close();
                                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(ruta + nombre));
                                output.writeUnshared(array);

                            } else {
                                //no encontrado (no va pasar porque si está)
                            }

                        }//fin for

                    } catch (NumberFormatException ex) {
                        lbl_errorTelefono.setVisible(true);
                        lbl_errorTelefono.setText("No es un número de teléfono válido");
                    }

                } else {
                    lbl_errorCorreo.setVisible(true);
                    lbl_errorCorreo.setText("No es un correo válido");

                }
            } else {
                lbl_errorTelefono.setVisible(true);
                lbl_errorTelefono.setText("Hay campos sin llenar");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo");
        }

    }//fin action

    //Se verifica que los valores esten
    public boolean verificar() throws ListaException, IOException, ClassNotFoundException {
        Object participantes = new Participantes();
        controlA.setNombre("Participantes.dat");
        listaE = controlA.cargarLista();

        //se verifica que la lista no esté vacía
        if (!listaE.isEmpty()) {
            System.out.println(nombreBuscar.length());

            //Se verifica que se hayan ingresado valores en el textfield
            if (nombreBuscar.length() != 0) {

                //Se recorre el archivo para obtener los datos
                for (int i = 1; i <= listaE.getSize(); i++) {
                    participantes = listaE.getNodo(i).elemento;
                    Participantes part = (Participantes) participantes;

                    //Se verifica que el valor ingresado coincida con el registrado
                    if (part.getNombreUsuario().equalsIgnoreCase(nombreBuscar)) {

                        lbl_dontFound.setVisible(false);
                        lbl_nombreCompleto.setVisible(true);
                        lbl_nombreUsuario.setVisible(true);
                        lbl_correo.setVisible(true);
                        lbl_numeroT.setVisible(true);
                        lbl_insNombreC.setVisible(true);
                        lbl_insNombreU.setVisible(true);
                        lbl_insCorreo.setVisible(true);
                        btn_modificarDatos.setVisible(true);
                        btn_eliminarParticipante.setVisible(true);
                        ins_numeroT.setVisible(true);
                        lbl_nombreCompleto.setText(part.getNombre());
                        lbl_nombreUsuario.setText(part.getNombreUsuario());
                        lbl_correo.setText(part.getCorreo());
                        lbl_numeroT.setText(Utilidades.formatTelefono(part.getNumeroTelefono()));
                        tfd_newEmail.setText(part.getCorreo());
                        tfd_newNombre.setText(part.getNombre());
                        tfd_newTelefono.setText("" + part.getNumeroTelefono());
                        tfd_newNombreUsuario.setText(part.getNombreUsuario());

                        return true;

                    } else {

                        lbl_dontFound.setVisible(true);
                        lbl_dontFound.setText("-" + nombreBuscar + "-" + " no se encuentra registrado");

                    }
                }
            } else {

                lbl_dontFound.setVisible(true);
                lbl_dontFound.setText("No se ingresó ningún valor");

            }
        }

        return false;

    }//fin verificar

    //Hacer invisibles los valores
    public void invisible() {

        lbl_nombreCompleto.setVisible(false);
        lbl_nombreUsuario.setVisible(false);
        lbl_correo.setVisible(false);
        lbl_numeroT.setVisible(false);
        lbl_insNombreC.setVisible(false);
        lbl_insNombreU.setVisible(false);
        lbl_insCorreo.setVisible(false);
        lbl_dontFound.setVisible(false);
        btn_modificarDatos.setVisible(false);
        btn_eliminarParticipante.setVisible(false);
        lbl_newNUsuario.setVisible(false);
        lbl_newCorreo.setVisible(false);
        lbl_newNombreC.setVisible(false);
        lbl_newTelefono.setVisible(false);
        ins_numeroT.setVisible(false);
        tfd_newEmail.setVisible(false);
        tfd_newNombre.setVisible(false);
        tfd_newTelefono.setVisible(false);
        tfd_newNombreUsuario.setVisible(false);
        btn_modificar.setVisible(false);
        lbl_errorTelefono.setVisible(false);
        lbl_errorCorreo.setVisible(false);

    }

    //Se reinician cuando se quiere buscar nueva un valor    
    @FXML
    private void hadleInvisible(MouseEvent event) {
        invisible();
    }

    //Buscar con enter
    @FXML
    private void hadleBuscar(ActionEvent event) throws ListaException, IOException, ClassNotFoundException {
        nombreBuscar = tfd_nombreBuscar.getText().trim();
        invisible();
        verificar();
    }

    @FXML
    private void tfd_newNombre(MouseEvent event) {
        lbl_errorTelefono.setVisible(false);
        lbl_errorCorreo.setVisible(false);
    }

    @FXML
    private void tfd_newNombreUsuario(MouseEvent event) {
        lbl_errorTelefono.setVisible(false);
        lbl_errorCorreo.setVisible(false);
    }

    @FXML
    private void tfd_newEmail(MouseEvent event) {
        lbl_errorTelefono.setVisible(false);
        lbl_errorCorreo.setVisible(false);
    }

    @FXML
    private void tfd_newTelefono(MouseEvent event) {
        lbl_errorTelefono.setVisible(false);
        lbl_errorCorreo.setVisible(false);
    }

}//fin class
