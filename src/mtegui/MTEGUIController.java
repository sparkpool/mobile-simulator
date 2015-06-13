/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtegui;
import card.basic.io.BasicIO;
import java.io.IOException;
import sim.toolkit.tlvparser.TLVConstants;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.ResponseAPDU;

/**
 *
 * @author user
 */
public class MTEGUIController implements Initializable {
    
    @FXML
    TreeView<String> logTree = new TreeView<String>();
    
    @FXML
    ComboBox cardReaderList = new ComboBox();
    
    @FXML 
    ComboBox gsmOpMode = new ComboBox();
    
    @FXML
    TextField passwordField = new TextField();
    
    @FXML
    Label appList = new Label();
    
    @FXML
    TextArea inputText = new TextArea();
    
    @FXML
    TextArea apduText = new TextArea();
    
    @FXML
    AnchorPane screenPane = new AnchorPane();
    
    @FXML
    private Button powerOnButton = new Button();
   
    
    @FXML
    private Button executeButton = new Button();
    
    @FXML
    private Button cancelButton = new Button();
    @FXML
    private Button callButton = new Button();
    @FXML
    private Button homeButton = new Button();
    @FXML
    private Button exitButton = new Button();
       
    private CommunicationInitiator sComm;
    
    private static boolean isPowerOn;
    
    @FXML
    private void handleButtonAction(ActionEvent event) 
    {
        //System.out.println("nagendra");
        if(event.getSource() == powerOnButton)
        {
           //if(!isPowerOn)
           //{
               System.out.println("Powerring on the card");
            isPowerOn = sComm.initCommunication();
           //}
           //else
          // {
            //   isPowerOn = false;
             //  sComm.powerOff();
          // }
        }
        else if(event.getSource() == homeButton)
        {
            
        }
        else if(event.getSource() == cancelButton)
        {
            
        }
        else if(event.getSource() == exitButton)
        {
            
        }
        else if(event.getSource() == callButton)
        {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        sComm = CommunicationInitiator.getInstance();
        sComm.initGUIComponent(logTree, cardReaderList, gsmOpMode, screenPane, appList, inputText, passwordField);
        
        try 
        {
            List<CardTerminal> cardReaders = BasicIO.getBasicIOInstance().getAvailableCardReader();
            //cardReaderList.getItems().addAll(cardReaders);
            ObservableList<String> options = FXCollections.observableArrayList();
            for(int i=0; i<cardReaders.size();i++)
            {
                options.add(cardReaders.get(i).toString());
                System.out.print(cardReaders.get(i).toString());
            }
            cardReaderList.setItems(options);
        } catch (CardException ex) {
            Logger.getLogger(MTEGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    public void handleDropDownSelection(MouseEvent ae)
    {
         try 
         {
            List<CardTerminal> cardReaders = BasicIO.getBasicIOInstance().getAvailableCardReader();
            //cardReaderList.getItems().addAll(cardReaders);
            ObservableList<String> options = FXCollections.observableArrayList();
            for(int i=0; i<cardReaders.size();i++)
            {
                options.add(cardReaders.get(i).toString());
                System.out.print(cardReaders.get(i).toString());
            }
            cardReaderList.setItems(options);
        } catch (CardException ex) {
            Logger.getLogger(MTEGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     @FXML
    private void handleExecuteButtonAction(ActionEvent event) 
    {
         if ((apduText.getText() != null && !apduText.getText().isEmpty())) {
             BasicIO senAPDU = new BasicIO();
             byte[] resp;
             for (String line : apduText.getText().split("\\n")){
                 senAPDU.sendAPDU(TLVConstants.hexStringToByteArray(line));
                 resp = senAPDU.getResponseData();
                 sComm.logTreeManipulator(resp.toString(), null);
             }
         }
    }
    
       @FXML
    private void handleClearAPDUPaneAction(ActionEvent event) 
    {
         if ((apduText.getText() != null && !apduText.getText().isEmpty())) {
                apduText.clear();
          }
         
    }
    
    @FXML
    private void handleCallWindowMenuItem(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CallWindows.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Call Window");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    
    @FXML
    private void handleEnvelopeGeneratorMenuItem(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("EnvelopeGenerator.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Envelope Generator");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    
    @FXML
    private void handleMEConfigurationMenuItem(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MEConfiguration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ME Configuration");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    
    @FXML
    private void handleNetworkConfigurationMenuItem(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("NetworkConfiguration.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Network Configuration");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
