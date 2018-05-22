/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.form;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        long endTime = 50000;
        DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
        final Timeline timeline = new Timeline(
        new KeyFrame(
            Duration.millis( 500 ),
            event -> {
                final long diff = System.currentTimeMillis() - 1000;
                if ( diff < 0 ) {
                //  timeLabel.setText( "00:00:00" );
                    lbl_tiempoFalta.setText( timeFormat.format( 0 ) );
                } else {
                    lbl_tiempoFalta.setText( timeFormat.format( diff ) );
                }
                System.out.println(""+diff);
            }
        )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }    
    
}
