package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.RegistroInvitaciones;
import cr.ac.ucr.if3001.proyecto1.util.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class InterfazParticipanteUsuarioController implements Initializable {

    //Intancia de clases necesarias
    ControlArchivos controlA = new ControlArchivos();
    ListaEnlazada listaE = new ListaEnlazada();

    @FXML
    private Label lbl_mensaje;
    @FXML
    private AnchorPane anp_invitado;
    @FXML
    private Label lbl_invitado;
    @FXML
    private AnchorPane anp_root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        anp_root.setOpacity(0);
        Utilidades.transition(anp_root);

        lbl_mensaje.setText("¡Saludos " + Utilidades.getNombreUsuario() + "!");
        try {
//            if (verificar()) {
                anp_invitado.setVisible(true);

                Object subasta = new RegistroInvitaciones();
                controlA.setNombre("RegistroInvitaciones.dat");
                listaE = controlA.cargarLista();

                for (int i = 1; i <= listaE.getSize(); i++) {
                    subasta = listaE.getNodo(i).elemento;
                    RegistroInvitaciones registro = (RegistroInvitaciones) subasta;
                    
                    if(Utilidades.getNombreUsuario().equalsIgnoreCase(registro.getNombreUsuario())){
                        lbl_invitado.setText(":D Estás invitad@ a la subasta del día " + registro.getDia()
                                + " de " + registro.getHoraIncio() + " a " + registro.getHoraFin()
                                + ", \ndonde podrás participar por los productos: "
                                + registro.getProducto());
                    }
                }

                
//            }
        } catch (IOException | ClassNotFoundException | ListaException ex) {
            Logger.getLogger(InterfazParticipanteUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//fin initialize

//    public boolean verificar() throws IOException, ClassNotFoundException, ListaException {
//        Object participantes = new Participantes();
//        
//
//        if (!listaE.isEmpty()) {
//
//            for (int i = 1; i <= listaE.getSize(); i++) {
//                
//                participantes = listaE.getNodo(i).elemento;
//                Participantes p = (Participantes) participantes;
//
//                if (p.getNombreUsuario().equalsIgnoreCase(Utilidades.getNombreUsuario())) {
//                    if (p.isInvitacion()) {
//                        return true;
//                    }
//                }
//            }//for
//        }
//        return false;
//    }// fin m'etodo

}//fin class
