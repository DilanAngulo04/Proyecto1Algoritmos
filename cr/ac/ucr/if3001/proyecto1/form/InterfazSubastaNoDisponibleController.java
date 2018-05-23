
package cr.ac.ucr.if3001.proyecto1.form;

import cr.ac.ucr.if3001.proyecto1.exception.ListaException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class InterfazSubastaNoDisponibleController implements Initializable {
    @FXML
    private Label lbl_tiempoFalta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { //hacer commit de esto
        try {
            // TODO
            long endTime = 50000;
            DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:MM");
            Date fecha = dateFormat.parse(InterfazPrincipalUsuarioController.subastaEntrante().getHoraIncio());
            final Timeline timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis( 500 ),
                            event -> {
                                final long diff = fecha.getTime()*1000 - System.currentTimeMillis();
                                if ( diff < 0 ) {
                                    //  timeLabel.setText( "00:00:00" );
                                    lbl_tiempoFalta.setText( timeFormat.format( 0 ) );
                                } else {
                                    lbl_tiempoFalta.setText( timeFormat.format( diff ) );
                                }
                            }
                    )
            );
            timeline.setCycleCount( Animation.INDEFINITE );
            timeline.play();
        } catch (ParseException | ListaException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(InterfazSubastaNoDisponibleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
