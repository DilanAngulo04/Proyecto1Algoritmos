/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import cr.ac.ucr.if3001.proyecto1.domain.EnviarCorreo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author dilan_000
 */
public class InterfazCorreoController implements Initializable {
    EnviarCorreo enviarC = new EnviarCorreo();

    @FXML
    private Label lbl_mensaje;
    @FXML
    private Label lbl_asunto;
    @FXML
    private TextArea txa_mensaje;
    @FXML
    private TextField tfd_asunto;
    @FXML
    private TextField tfd_para;
    @FXML
    private Label lbl_para;
    @FXML
    private JFXButton btn_enviar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEnviar(ActionEvent event) throws MessagingException {
        String asunto = tfd_asunto.getText();
        String destinatario = tfd_para.getText();
        String mensaje = txa_mensaje.getText();
        enviarC.enviarConGMail(destinatario, asunto, mensaje);
    }
    
}
