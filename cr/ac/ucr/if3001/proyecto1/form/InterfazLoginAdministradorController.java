package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.ucr.if3001.proyecto1.domain.ControlArchivos;
import cr.ac.ucr.if3001.proyecto1.domain.ListaEnlazada;
import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import cr.ac.ucr.if3001.proyecto1.object.Participantes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfazLoginAdministradorController implements Initializable {
    
    private ListaEnlazada listE = new ListaEnlazada();
    private ControlArchivos controlA = new ControlArchivos();

    @FXML    
    private JFXPasswordField tfd_contraseña;
    @FXML
    private JFXTextField tfd_nombreUsuario;
    @FXML
    private JFXButton btn_ingresar;
    @FXML
    private JFXButton btn_registrate;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btn_ingresarInfo(ActionEvent event) throws IOException, ListaException {      
        verificar();        
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazBienvenida.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    private void btn_registrarUsuario(ActionEvent event) throws IOException {
        AnchorPane anchor = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazRegistroAdministrador.fxml"));
        Scene scene = new Scene(anchor);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();       
    }
    
    public void verificar() throws ListaException{   
        Object participantes = new Participantes();
        
        controlA.setNombre("Participantes.dat");        
        listE.insertar(controlA.cargar(new Participantes())); 
        System.out.println(listE.toString());
        if(!listE.isEmpty()){
            for(int i = 0; i < listE.getSize(); i++){
                participantes = (Participantes)listE.getNodo(i);   
            }          
            }
        }
    }


