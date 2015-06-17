/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtegui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author sandesh
 */
public class NetworkConfigurationController implements Initializable {
    
    
    
    UserInformation userInfo;
    
    @FXML
    TextField MCC = new TextField();
    @FXML
    TextField MNC = new TextField();
    @FXML
    TextField LAC = new TextField();
    @FXML
    TextField CellID1 = new TextField();
    @FXML
    TextField CellID2 = new TextField();
    @FXML
    private ComboBox LocationStatus = new ComboBox();
    @FXML
    private ComboBox AccessTech = new ComboBox();
     @FXML
    TextField NMR = new TextField();

    public static final int maxLength = 4;

     
     
     
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocationStatus.getItems().addAll("Normal Service","Limited Service","No Service");
        LocationStatus.setValue("Normal");
        AccessTech.getItems().addAll("GSM","TIA/EIA-553","TIA/EIA-136-C",
                                    "UTRAN","TETRA","TIA/EIA-95","cdma2000 1x",
                                    "cdma2000 HRPD","E-UTRAN","eHRPD");
        AccessTech.setValue("GSM");
        CellID1.textProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue<? extends String> observable,
            String oldValue, String newValue) {
                try {
                    // force numeric value by resetting to old value if exception is thrown
                    Integer.parseInt(newValue);
                    // force correct length by resetting to old value if longer than maxLength
                    if(newValue.length() > maxLength)
                        CellID1.setText(oldValue);
                } catch (Exception e) {
                    CellID1.setText(oldValue);
                }
            }

          
        });
        CellID2.textProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue<? extends String> observable,
            String oldValue, String newValue) {
                try {
                    // force numeric value by resetting to old value if exception is thrown
                    Integer.parseInt(newValue);
                    // force correct length by resetting to old value if longer than maxLength
                    if(newValue.length() > maxLength)
                        CellID2.setText(oldValue);
                } catch (Exception e) {
                    CellID2.setText(oldValue);
                }
            }
        });        
    }    
    
     @FXML
    private void handleUpdateButtonAction(ActionEvent event) throws IOException{
      
        String locstat;
        String AccTech;
        int locSta =0 ;
        int accTech = 0;
        if(MCC.getText().length()==3 && (MNC.getText().length()==2 ||MNC.getText().length()==3)){
            try {
                userInfo.setMCC(MCC.getText());
                userInfo.setMNC(MNC.getText());
                if(LAC.getText().length() == 4 && LAC.getText().matches("-?[0-9a-fA-F]+")){
                    userInfo.setLAC(LAC.getText());
                    locstat = (String)LocationStatus.getValue();
                    if(locstat.equals("Normal Service")){
                        locSta = 0;
                    }else if(locstat.equals("Limited Service")){
                        locSta =1;
                    }else if(locstat.equals("No Service")){
                        locSta =2 ;
                    }
                    userInfo.setLocationStatus(locSta);
                   
                    AccTech = (String)AccessTech.getValue();
                    if(AccTech.equals("GSM")){
                        accTech = 0;
                    }else if(AccTech.equals("TIA/EIA-553")){
                        accTech =1;
                    }else if(AccTech.equals("TIA/EIA-136-C")){
                        accTech =2 ;
                    }else if(AccTech.equals("UTRAN")){
                        accTech =3;
                    }else if(AccTech.equals("TETRA")){
                        accTech =4 ;
                    }else if(AccTech.equals("TIA/EIA-95")){
                        accTech =5;
                    }else if(AccTech.equals("cdma2000 1x")){
                        accTech =6 ;
                    }else if(AccTech.equals("cdma2000 HRPD")){
                        accTech =7;
                    }else if(AccTech.equals("E-UTRAN")){
                        accTech =8 ;
                    }else if(AccTech.equals("eHRPD")){
                        accTech =9 ;
                    }
                    userInfo.setAccessTechnology(accTech);
                    if(NMR.getText().length()%2 == 0 && NMR.getText().matches("-?[0-9a-fA-F]+")){
                     userInfo.setNMR(NMR.getText());
                     userInfo.setCellID(CellID1.getText()+CellID2.getText());
                    }else {
                        createDialog("NMR must be of even digit hex value");
                    }
                }else{
                    createDialog("Please insert correct LAC");
                }
            } catch(Exception e){
                createDialog("LAC must be 4 digit hex value");
            }
        }else{
            createDialog("MCC must be of 3 digit, MNC must be of 2 or 3 digit");
        }
       
    }
    
    private void createDialog(String s){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error Alert");
        alert.setContentText(s);
        alert.show();
    }
    
    
}
