/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtegui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author sandesh
 */
public class CallWindowController implements Initializable {

    
    UserInformation userInfo;
    
    @FXML
    TextArea TonNpi = new TextArea();
    @FXML
    TextArea CC = new TextArea();
    @FXML
    TextArea Number = new TextArea();
    @FXML
    TextArea TransID = new TextArea();
    @FXML
    TextArea SubAddress = new TextArea();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void handleCallButtonAction(ActionEvent event) throws IOException{
    
    }
    
    @FXML
    private void handleDisConnectButtonAction(ActionEvent event) throws IOException{
    
   
    }
    
}
