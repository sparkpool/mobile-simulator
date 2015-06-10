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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static sun.security.krb5.KrbException.errorMessage;
/**
 * FXML Controller class
 *
 * @author sandesh
 */

public class MEConfigurationController implements Initializable {

    @FXML
    TextField TwoGTerminalProfile = new TextField();
    
    @FXML
    TextField ThreeGTerminalProfile = new TextField();
    
    @FXML
    TextField IMEI = new TextField();
    
    @FXML
    TextField DCS = new TextField();
    
    @FXML
    TextField ResponseString = new TextField();
    
    @FXML
    TextField LangCode = new TextField();
    
    @FXML
    TextField PollDuration = new TextField();
     
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    @FXML
    public void handle2GDetailViewButtonAction(ActionEvent event) throws IOException{
        String str = TwoGTerminalProfile.getText();
        if(str.length()%2 == 0){
            
        }else{
            
            
           //Dialogs.showErrorDialog(Stage object, errorMessage,  "Main line", "Name of Dialog box");
           /*Parent root = FXMLLoader.load(getClass().getResource("ErrorDialogBox.fxml"));
            Label lblData= (Label) root.lookup("#errorLabel");
            lblData.setText("Error: Length must be even");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Error");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();*/
        }
    } 
    
    @FXML
    public void handle3GDetailViewButtonAction(ActionEvent event){
        
    }
    @FXML
    public void handleUpdateButtonAction(ActionEvent event){
        
    }
    
    @FXML
    public void handleCancelButtonAction(ActionEvent event){
        
    }
}
