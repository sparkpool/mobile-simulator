/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtegui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author sandesh
 */
public class EnvelopeGeneratorController implements Initializable {

    @FXML
    private ComboBox EnvelopeTypes = new ComboBox();
    
    @FXML
    private ComboBox TimerID = new ComboBox();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EnvelopeTypes.getItems().addAll("SMS PP data download","Cell Broadcast data download",
                "MENU SELECTION","(CALL CONTROL",
                "MO SHORT MESSAGE CONTROL","TIMER EXPIRATION",
                "EVENT DOWNLOAD - MT call","EVENT DOWNLOAD - call connected",
                "EVENT DOWNLOAD - Call disconnected","EVENT DOWNLOAD - Location status",
                "EVENT DOWNLOAD - User activity","EVENT DOWNLOAD - Idle screen available",
                "EVENT DOWNLOAD - card reader status","language selection",
                "browser termination","EVENT DOWNLOAD – Data available",
                "EVENT DOWNLOAD – Channel status");
        
        TimerID.getItems().addAll("01","02","03","04","05","06","07","08");
    }    
    
}
