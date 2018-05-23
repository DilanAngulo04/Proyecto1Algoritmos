package cr.ac.ucr.if3001.proyecto1.form;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.MessagingException;

public class Proyecto1Algoritmos extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazSubModuloProductos.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazPrincipalAdministrador.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("InterfazPrincipalUsuario.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazSubModuloVerParticipantes.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazSubModuloInvitarParticipantes.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazLoginParticipantes.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazPrincipalUsuario.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("InterfazCorreo.fxml"));
        Scene scene = new Scene(root);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws MessagingException {
        launch(args);
    }
}
