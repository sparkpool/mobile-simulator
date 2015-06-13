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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        String tonnpi;
        int cc;
        int number;
        String transID;
        if(TonNpi.getText().length()==2&& CC.getText().length()==2){
            try {
                tonnpi = TonNpi.getText();
                cc = Integer.parseInt(CC.getText());
                number = Integer.parseInt(Number.getText());
                if(TransID.getText().length()%2 == 0 && TransID.getText().matches("-?[0-9a-fA-F]+")){
                    transID = TransID.getText();
                }else{
                    createDialog("Please insert correct Transaction id");
                }
            } catch(Exception e){
                createDialog("CC must be integer of two digit");
            }
        }else{
            createDialog("Ton/NPI and CC must be of two digit");
        }
    }
    
    private void createDialog(String s){
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Alert");
        alert.setContentText(s);
        alert.show();

        
    }
    
    @FXML
    private void handleDisConnectButtonAction(ActionEvent event) throws IOException{
    
   
    }
    
}
