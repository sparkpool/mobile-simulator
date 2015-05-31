/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtegui;

import card.basic.io.BasicIO;
import card.basic.io.FileSystemCommands;
import card.basic.io.SecurityCommands;
import card.basic.io.ToolkitCommands;
import com.sun.javafx.embed.AbstractEvents;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.MouseEvent;
import sim.toolkit.tlvparser.FileSystemConstants;
import sim.toolkit.tlvparser.TLVConstants;
import sim.toolkit.tlvparser.TLVParser;
import sim.toolkit.toolkitcommands.BasicCommand;
import sim.toolkit.toolkitcommands.CommandParser;
import sim.toolkit.toolkitcommands.ProvideLocalInfo;
import sim.toolkit.toolkitcommands.SetupEventList;
import sim.toolkit.toolkitcommands.SetupMenu;

/**
 *
 * @author user
 */
public class CommunicationInitiator  implements EventHandler<javafx.scene.input.MouseEvent>,FileSystemConstants,TLVConstants
{
    CommandParser cmdParser;
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
    AnchorPane screenPane = new AnchorPane();
    
    private static ListView<String> menuList = new ListView<>();
    private static ObservableList<String> items;
    private TreeItem<String> firstLevelItem = new TreeItem<>();
    private TreeItem<String> secondLevelItem = new TreeItem<>();
    private TreeItem<String> thirdLevelItem = new TreeItem<>();
    private TreeItem<String> rootTreeItem = new TreeItem<>("SIM Toolkit Session");
    private static CommunicationInitiator instance;
    
    private static FileSystemCommands fsysCmd;
    private static SecurityCommands secCmd;
    private static ToolkitCommands tkCmd;
    
    /**
     * This method used to start the SIM-Mobile communication
     * @return : Return true if communication established otherwise false
     */
    boolean initCommunication()
    {
        FileSystemCommands.setOperationMode("2G");
        init2G();
        return true;
    }
    
    /**
     * This method used to initialize the GUI Component and so  that at the run time these can be rendored and manipulated
     */
    public void initGUIComponent(TreeView<String> logTree, ComboBox readerList, ComboBox gsmOpMode, AnchorPane screenPane, Label appList, TextArea inputText, TextField passwordField) 
    {
        this.logTree = logTree;
        this.cardReaderList = readerList;
        this.gsmOpMode = gsmOpMode;
        this.screenPane = screenPane;
        this.appList = appList;
        this.inputText = inputText;
        this.passwordField = passwordField;
        this.screenPane.setVisible(true);
        menuList.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, this);
        items = FXCollections.observableArrayList();
        
        AnchorPane.setTopAnchor(menuList, 75.0);
        AnchorPane.setLeftAnchor(menuList, 15.0);
        AnchorPane.setRightAnchor(menuList, 20.0);
        
        menuList.widthProperty();
        menuList.setLayoutX(13.0);
        menuList.setLayoutY(75.0);
        menuList.setFixedCellSize(0.0);
        menuList.setPrefHeight(258.0);
        menuList.setPrefWidth(223.0);
        screenPane.getChildren().add(menuList);
    }
    
    
    boolean init2G()
    {
        BasicIO.getBasicIOInstance().connetWithCard(cardReaderList.getSelectionModel().getSelectedIndex());
        BasicIO.getBasicIOInstance().getAtr();
        List secondLevel = new ArrayList();
        secondLevel.add(BasicIO.getBasicIOInstance().getATR());
        this.logTreeManipulator("Power ON", null);
        this.logTreeManipulator("ATR", secondLevel );
        fsysCmd.selectFile(DF_MF);
        secondLevel.clear();
        secondLevel.add("Status word : "+fsysCmd.getSW1SW2());
        this.logTreeManipulator("Select File(MF) : "+fsysCmd.getLastExecutedCommand(), secondLevel);
        
        fsysCmd.getResponse(0x17);
        secondLevel.clear();
        secondLevel.add("Response Data : "+fsysCmd.getResponseData());
        secondLevel.add("Status Word : "+fsysCmd.getSW1SW2());
        this.logTreeManipulator("Get Response : "+fsysCmd.getLastExecutedCommand(), secondLevel);
        
        fsysCmd.selectFile(DF_GSM);
        secondLevel.clear();
        secondLevel.add("Status word : "+fsysCmd.getSW1SW2());
        this.logTreeManipulator("Select File(DF GSM) : "+fsysCmd.getLastExecutedCommand(), secondLevel);
        
        
        fsysCmd.selectFile(EF_IMSI);
        secondLevel.clear();
        secondLevel.add("Status word : "+fsysCmd.getSW1SW2());
        this.logTreeManipulator("Select File(IMSI) : "+fsysCmd.getLastExecutedCommand(), secondLevel);
        
        fsysCmd.readBinary(0, 9);
        secondLevel.clear();
        secondLevel.add("Data : "+fsysCmd.getResponseData());
        secondLevel.add("Status word : "+fsysCmd.getSW1SW2());
        this.logTreeManipulator("Read Binary  : "+fsysCmd.getLastExecutedCommand(), secondLevel);
        
        tkCmd.terminalprofile("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        secondLevel.clear();
        secondLevel.add("Status word : "+tkCmd.getSW1SW2());
        this.logTreeManipulator("Terminal Profile  : "+tkCmd.getLastExecutedCommand(), secondLevel);
        startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
        

        return true;
    }
    
    public void startSKTFlow(String sw1, String sw2)
    {
        cmdParser = CommandParser.getCommandParserInstance();
        if(sw1.equals("91") && !sw2.equalsIgnoreCase("00"))
        {
            List<String> parsedTLV;
            while(true)
            {
                List secondLevel = new ArrayList();
                tkCmd.fetch(Integer.parseInt(sw2, 16));
                secondLevel.clear();
                //System.out.println(tkCmd.getResponseData());
                secondLevel.add("Fetched Data : " + tkCmd.getResponseData());
                secondLevel.add("Status word : " + tkCmd.getSW1SW2());
                this.logTreeManipulator("Fetch  : " + tkCmd.getLastExecutedCommand(), secondLevel);
                cmdParser.parseCommand(tkCmd.getResponseData());
                switch(getTypeOfCommand(tkCmd.getResponseData()))
                {
                   
                    case TOC_REFRESH:
                        break;
                    case TOC_MORE_TIME:
                        break;
                    case TOC_POLL_INTERVAL:
                        break;
                    case TOC_POLLING_OFF:
                        break;
                    case TOC_SETUP_EVENT_LIST:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Setup Event List");
                        SetupEventList eventLsitInstance = SetupEventList.getInstance();
                        parsedTLV =eventLsitInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Setup Event List : "+tkCmd.getResponseData(), parsedTLV, null);
                        //System.out.println(SetupMenu.getInstance().getCommandTLVList());
                        tkCmd.terminalResponse(eventLsitInstance.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), eventLsitInstance.getTRTLVList(), null);
                        break;
                    case TOC_SETUP_CALL:
                        break;
                    case TOC_SEND_SS:
                        break;
                    case TOC_SEND_USSD:
                        break;
                    case TOC_SEND_SMS:
                        break;
                    case TOC_SEND_DTMF:
                        break;
                    case TOC_LAUNCH_BROWSER:
                        break;
                    case TOC_GEO_LOCATION_REQUEST:
                        break;
                    case TOC_PLAY_TONE:
                        break;
                    case TOC_DISPLAY_TEXT:
                        break;
                    case TOC_GET_INKEY:
                        break;
                    case TOC_GET_INPUT:
                        break;
                    case TOC_SELECT_ITEM:
                        break;
                    case TOC_SETUP_MENU:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Setup Menu Command");
                        SetupMenu smenuInstance = SetupMenu.getInstance();
                        parsedTLV =smenuInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Setup Menu Command : "+tkCmd.getResponseData(), parsedTLV, null);
                        //System.out.println(SetupMenu.getInstance().getCommandTLVList());
                        tkCmd.terminalResponse(smenuInstance.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), smenuInstance.getTRTLVList(), null);
                        items.clear();
                        for(int i=0;i<smenuInstance.getMenuItemListTLV().size();i++)
                        {
                            items.add(smenuInstance.getMenuItemText(smenuInstance.getMenuItemListTLV().get(i)));
                        }
                        
                        menuList.setItems(items);
                        rendorScreenComponent(true, true, false, false);
                        //return;
                        break;
                    case TOC_PLI:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Provide Local Information");
                        ProvideLocalInfo pliInstance = ProvideLocalInfo.getInstance();
                        parsedTLV = pliInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Provide Local Information : "+tkCmd.getResponseData(), parsedTLV, null);
                        //System.out.println(SetupMenu.getInstance().getCommandTLVList());
                        tkCmd.terminalResponse(pliInstance.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), pliInstance.getTRTLVList(), null);
                        break;
                        
                    case TOC_TIMER_MANAGEMENT:
                        break;
                    case TOC_SETUP_IDLE_MODE_TEXT:
                        break;
                    case TOC_PERFORM_CARD_APDU:
                        break;
                    case TOC_POWER_ON_CARD:
                        break;
                    case TOC_POWER_OFF_CARD:
                        break;
                    case TOC_GET_READER_STATUS:
                        break;
                    case TOC_RUN_AT_COMMAND:
                        break;
                    case TOC_LANGUAGE_NOTIFICATION:
                        break;
                    case TOC_OPEN_CHANNEL:
                        break;
                    case TOC_CLOSE_CHANNEL:
                        break;
                    case TOC_RECEIVE_DATA:
                        break;
                    case TOC_SEND_DATA:
                        break;
                    case TOC_GET_CHANNEL_STATUS:
                        break;
                    case TOC_SERVICE_SEARCH:
                        break;
                    case TOC_GET_SERVICE_INFO:
                        break;
                    case TOC_DECLARE_SERVICE:
                        break;
                    case TOC_SET_FRAME:
                        break;
                    case TOC_GET_FRAME_STATUS:
                        break;
                    default:
                        return;
                }
                if(tkCmd.getSW2().equalsIgnoreCase("00"))
                {
                    return;
                }
                else
                {
                    sw1 = tkCmd.getSW1();
                    sw2 = tkCmd.getSW2();
                }
            }
        }
    }
    boolean init3G()
    {
         return true;
    }
    
    boolean initAuto()
    {
         return true;
    }

    /**
     * This method used to get the singleton instance of the class
     * @return 
     */
    public static CommunicationInitiator getInstance() 
    {
        if(instance == null)
        {
            instance = new CommunicationInitiator();
            secCmd = SecurityCommands.getSecurityInstance();
            fsysCmd = FileSystemCommands.getFileSystemInstance();
            tkCmd = ToolkitCommands.getToolkitCommandsInstance();
        }
        return instance;
    }

    
    
    /**
     * This method used to rendor he screen component as per the need
     * @param isApplist  
     * @param isMenuList
     * @param isTextField
     * @param isTextArea 
     */
    public void rendorScreenComponent(boolean isApplist, boolean isMenuList, boolean isTextField, boolean isTextArea)
    {
        this.appList.setVisible(isApplist);
        this.passwordField.setVisible(isTextField);
        this.inputText.setVisible(isTextArea);
        this.menuList.setVisible(isMenuList);
    }
    
    void logTreeManipulator(String  firstLevel, List secondLevel, List thirdLevel)
    {
        firstLevelItem = new TreeItem<>(firstLevel);
        rootTreeItem.getChildren().add(firstLevelItem);
        TLVParser tlvParser = TLVParser.getTLVParserInstance();
        
        if(secondLevel!=null)
        {
            for(int i=0; i<secondLevel.size();i++)
            {
                String tlvName = tlvParser.getTLVName(secondLevel.get(i).toString());
                secondLevelItem = new TreeItem<String>(tlvName + " ( "+secondLevel.get(i).toString()+" ) ");
                firstLevelItem.getChildren().add(secondLevelItem);
                thirdLevel = tlvParser.parseTLV(secondLevel.get(i).toString());
                System.out.println(thirdLevel);
                for(int j =0; j<thirdLevel.size();j++)
                {
                    thirdLevelItem = new TreeItem<String>(thirdLevel.get(j).toString());
                    secondLevelItem.getChildren().add(thirdLevelItem);
                }
                thirdLevel.clear();
            }
            secondLevelItem = new TreeItem<>("Status Word (SW1 SW2) : "+tkCmd.getSW1SW2());
            firstLevelItem.getChildren().add(secondLevelItem);
        }
        logTree.setRoot(rootTreeItem);
        logTree.setEditable(true);
    }
    
    void logTreeManipulator(String  firstLevel, List secondLevel)
    {
        firstLevelItem = new TreeItem<>(firstLevel);
        rootTreeItem.getChildren().add(firstLevelItem);
        if(secondLevel!=null)
        {
            for(int i=0; i<secondLevel.size();i++)
            {
                secondLevelItem = new TreeItem<>(secondLevel.get(i).toString());
                firstLevelItem.getChildren().add(secondLevelItem);
            }
        }
        logTree.setRoot(rootTreeItem);
        logTree.setEditable(true);
    }

    
    /**
     * This method used to power of the mobile and terminate the card SIM-ME communications
     */
    void powerOff()
    {
        BasicIO.getBasicIOInstance().disconnectwithCard(cardReaderList.getSelectionModel().getSelectedIndex());
        logTreeManipulator("Power OFF", null);
    }
    
    private byte getTypeOfCommand(String fetchedCommand)
    {
        //check if proactive command length is in the 2 byte format
        byte typeOfCommand = 0;
        if(fetchedCommand.substring(2,4).equalsIgnoreCase("81"))
        {
            typeOfCommand = (byte)(Integer.parseInt(fetchedCommand.substring(12,14), 16));
        }
        else
        {
            typeOfCommand = (byte)(Integer.parseInt(fetchedCommand.substring(10,12), 16));
        }
        return typeOfCommand;
    }

    @Override
    public void handle(javafx.scene.input.MouseEvent event) 
    {
        if(event.getSource() == menuList)
        {
            int index =0;
            
        }
    }

   
}
