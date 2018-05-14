package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import cr.ac.ucr.if3001.proyecto1.object.Pujas;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InterfazSubModuloModificarParticipanteController implements Initializable {

    ControlArchivos controlA = new ControlArchivos();
    ListaEnlazada listaE = new ListaEnlazada();
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
    private Label lbl_totalPujas;
    @FXML
    private Label lbl_montosTotales;
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
    private TextField tfd_telefono;
    @FXML
    private JFXButton btn_modificar;
    @FXML
    private JFXButton btn_eliminarParticipante;
    @FXML
    private TextField tfd_nombreBuscar;
    @FXML
    private Label lbl_insNombreC;
    @FXML
    private Label lbl_insOtrosD;
    @FXML
    private Label lbl_insCorreo;
    @FXML
    private Label lbl_insNombreU;
    @FXML
    private Label lbl_insPujasTotal;
    @FXML
    private Label lbl_insMontosTotal;
    @FXML
    private Label ins_numeroT;

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
    private void handleModificar(ActionEvent event) {
    }

    @FXML
    private void handleEliminarParticipante(ActionEvent event) {
    }

    public void verificar() throws ListaException, IOException, ClassNotFoundException {
        Object participantes = new Participantes();
        Object pujas = new Pujas();
        controlA.setNombre("Participantes.dat");
        listaE = controlA.cargarLista();
        System.out.println(listaE.toString());

        if (!listaE.isEmpty()) {

            for (int i = 1; i <= listaE.getSize(); i++) {
                participantes = listaE.getNodo(i).elemento;
                Participantes part = (Participantes) participantes;

                if (part.getNombreUsuario().equalsIgnoreCase(nombreBuscar)) {

                    lbl_nombreCompleto.setVisible(true);
                    lbl_nombreUsuario.setVisible(true);
                    lbl_correo.setVisible(true);
                    lbl_numeroT.setVisible(true);
                    lbl_insNombreC.setVisible(true);
                    lbl_insNombreU.setVisible(true);
                    lbl_insCorreo.setVisible(true);
                    lbl_insOtrosD.setVisible(true);
                    lbl_insPujasTotal.setVisible(true);
                    lbl_insMontosTotal.setVisible(true);
                    lbl_montosTotales.setVisible(true);
                    lbl_totalPujas.setVisible(true);
                    lbl_nombreCompleto.setText(part.getNombre());
                    lbl_nombreUsuario.setText(part.getNombreUsuario());
                    lbl_correo.setText(part.getCorreo());
                    lbl_numeroT.setText(Utilidades.formatTelefono(part.getNumeroTelefono()));

//                    this.controlA.setNombre("Pujas.dat");
//                    this.listaE.anular();
//                    this.listaE = controlA.cargarLista();
//                    
//                    if(!listaE.isEmpty()){
//                    
//                        for(int j = 1; j <= listaE.getSize(); j++){
//                            
//                            pujas = listaE.getNodo(j).elemento;
//                            Pujas puj = (Pujas) pujas;
//                            
//                        }
//                    }
                } else {
                    
                    lbl_dontFound.setVisible(true);
                    lbl_dontFound.setText("-" +nombreBuscar+ "-" + " no se encuentra registrado");                    
                    if (nombreBuscar.length() == 0) {
                        lbl_dontFound.setText("No se ingresó ningún valor");
                    } 

                }

            }
        }

    }//fin verificar

    public void invisible() {

        lbl_nombreCompleto.setVisible(false);
        lbl_nombreUsuario.setVisible(false);
        lbl_correo.setVisible(false);
        lbl_numeroT.setVisible(false);
        lbl_insNombreC.setVisible(false);
        lbl_insNombreU.setVisible(false);
        lbl_insCorreo.setVisible(false);
        lbl_insOtrosD.setVisible(false);
        lbl_insPujasTotal.setVisible(false);
        lbl_insMontosTotal.setVisible(false);
        lbl_montosTotales.setVisible(false);
        lbl_totalPujas.setVisible(false);
        lbl_dontFound.setVisible(false);

    }

}//fin class
