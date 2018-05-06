/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.ucr.if3001.proyecto1.form;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author dilan_000
 */
public class InterfazBienvenidaController implements Initializable {

    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private JFXListView<String> listViewMaterial;
    @FXML
    private TabPane tab_ventanas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadListView();
        selectmenu();
    }    

    private void loadListView(){
        ObservableList<String> ols = FXCollections.observableArrayList();
        ols.add("Productos");
        ols.add("Bienes");
        ols.add("Servicios");
        listViewMaterial.setItems(ols);
    }
    
    private void selectmenu(){
        listViewMaterial.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                int i = listViewMaterial.getSelectionModel().getSelectedIndex();
                System.out.print(i);
                if(i==0){
                    try{
                        Node node = (AnchorPane) FXMLLoader.load(getClass().getResource("InterfazElegirLogin.fxml"));
                        System.out.println("yes");
                        Tab td = new Tab("Productos", node);
                        tab_ventanas.getTabs().add(td);
                    }catch(IOException ioe){
                        Logger.getLogger(InterfazBienvenidaController.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }
            }
        });
    }
    
    
}
//AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        anchorRoot.getChildren().setAll(pane);