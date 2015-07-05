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
import com.sun.javafx.css.Declaration;
import com.sun.javafx.embed.AbstractEvents;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import sim.toolkit.envelope.EnvelopePreparator;
import sim.toolkit.tlvparser.FileSystemConstants;
import sim.toolkit.tlvparser.TLVConstants;
import sim.toolkit.tlvparser.TLVParser;
import sim.toolkit.toolkitcommands.BasicCommand;
import sim.toolkit.toolkitcommands.CloseChannel;
import sim.toolkit.toolkitcommands.CommandParser;
import sim.toolkit.toolkitcommands.DeclareService;
import sim.toolkit.toolkitcommands.DisplayText;
import sim.toolkit.toolkitcommands.GeographicalLocation;
import sim.toolkit.toolkitcommands.GetChannelStatus;
import sim.toolkit.toolkitcommands.GetFrameStatus;
import sim.toolkit.toolkitcommands.GetInkey;
import sim.toolkit.toolkitcommands.GetInput;
import sim.toolkit.toolkitcommands.GetReaderStatus;
import sim.toolkit.toolkitcommands.GetServiceInformation;
import sim.toolkit.toolkitcommands.LanguageNotification;
import sim.toolkit.toolkitcommands.LaunchBrowser;
import sim.toolkit.toolkitcommands.MoreTime;
import sim.toolkit.toolkitcommands.OpenChannel;
import sim.toolkit.toolkitcommands.PerformCardAPDU;
import sim.toolkit.toolkitcommands.PlayTone;
import sim.toolkit.toolkitcommands.PollInterval;
import sim.toolkit.toolkitcommands.PollingOff;
import sim.toolkit.toolkitcommands.PowerOffCard;
import sim.toolkit.toolkitcommands.PowerOnCard;
import sim.toolkit.toolkitcommands.ProvideLocalInfo;
import sim.toolkit.toolkitcommands.ReceiveData;
import sim.toolkit.toolkitcommands.Refresh;
import sim.toolkit.toolkitcommands.RunATCommand;
import sim.toolkit.toolkitcommands.SelectItem;
import sim.toolkit.toolkitcommands.SendDTMF;
import sim.toolkit.toolkitcommands.SendData;
import sim.toolkit.toolkitcommands.SendSMS;
import sim.toolkit.toolkitcommands.SendSS;
import sim.toolkit.toolkitcommands.SendUSSD;
import sim.toolkit.toolkitcommands.ServiceSearch;
import sim.toolkit.toolkitcommands.SetFrames;
import sim.toolkit.toolkitcommands.SetupCall;
import sim.toolkit.toolkitcommands.SetupEventList;
import sim.toolkit.toolkitcommands.SetupIdleModeText;
import sim.toolkit.toolkitcommands.SetupMenu;
import sim.toolkit.toolkitcommands.TimerManagement;

/**
 *
 * @author user
 */
public class CommunicationInitiator  implements EventHandler<javafx.scene.input.MouseEvent>,FileSystemConstants,TLVConstants
{
    private CommandParser cmdParser;
    
    private int currentlySelectedCommand =0;
    private static final int SETUP_MENU_COMMAND = 1;
    private static final int SELECT_ITEM_COMMAND = 2;
    
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
    
    @FXML
    TreeView<String> logTree = new TreeView<String>();
    
    private static ListView<String> menuList = new ListView<>();
    private static ObservableList<String> items;
    private TreeItem<String> firstLevelItem = new TreeItem<>();
    private TreeItem<String> secondLevelItem = new TreeItem<>();
    private TreeItem<String> thirdLevelItem = new TreeItem<>();
    private TreeItem<String> rootTreeItem = new TreeItem<>("SIM Toolkit Session");
    private static CommunicationInitiator instance;
    List secondLevelTreeItem;
    List thirtLevelTreeItem;
    
    private static FileSystemCommands fsysCmd;
    private static SecurityCommands secCmd;
    private static ToolkitCommands tkCmd;
    private boolean isTRPending;
    
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
     * This method used to initialize the GUI Component and so  that at the run time these can be rendered and manipulated
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
        secondLevelTreeItem = new ArrayList();
        thirtLevelTreeItem = new ArrayList();
        menuList.widthProperty();
        menuList.setLayoutX(13.0);
        menuList.setLayoutY(75.0);
        menuList.setFixedCellSize(0.0);
        menuList.setPrefHeight(258.0);
        menuList.setPrefWidth(225.0);
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
         if(sw1.equals("90"))
         {
             SetupMenu smenuInstance = SetupMenu.getInstance();
             //tkCmd.terminalResponse(smenuInstance.prepareTerminalResponse(0));
             secondLevelTreeItem.clear();
             items.clear();
             for (int i = 0; i < smenuInstance.getMenuItemListTLV().size(); i++) {
                 items.add(smenuInstance.getMenuItemText(smenuInstance.getMenuItemListTLV().get(i)));
             }
             menuList.setItems(items);
             appList.setText("SIM Toolkit");
             rendorScreenComponent(true, true, false, false);
             currentlySelectedCommand = SETUP_MENU_COMMAND;
         }
        if(sw1.equals("91") && !sw2.equalsIgnoreCase("00"))
        {
            List<String> parsedTLV;
            while(true)
            {
                tkCmd.fetch(Integer.parseInt(sw2, 16));
                secondLevelTreeItem.clear();
                secondLevelTreeItem.add("Fetched Data : " + tkCmd.getResponseData());
                secondLevelTreeItem.add("Status word : " + tkCmd.getSW1SW2());
                this.logTreeManipulator("Fetch  : " + tkCmd.getLastExecutedCommand(), secondLevelTreeItem);
                cmdParser.parseCommand(tkCmd.getResponseData());
                inputText.setText("");
                inputText.setEditable(false);
                switch(getTypeOfCommand(tkCmd.getResponseData()))
                {
                   
                    case TOC_REFRESH:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Refresh");
                        Refresh refreshInstance = Refresh.getInstance();
                        parsedTLV =refreshInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Refresh : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        handleCommandSleep();
                        tkCmd.terminalResponse(refreshInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), refreshInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_MORE_TIME:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: More Time");
                        MoreTime moreTimeInstance = MoreTime.getInstance();
                        parsedTLV =moreTimeInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: More Time : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        handleCommandSleep();
                        tkCmd.terminalResponse(moreTimeInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), moreTimeInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_POLL_INTERVAL:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Poll Interval");
                        PollInterval pollIntervalInstance = PollInterval.getInstance();
                        parsedTLV =pollIntervalInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Poll Interval : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        handleCommandSleep();
                        tkCmd.terminalResponse(pollIntervalInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), pollIntervalInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_POLLING_OFF:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Polling Off");
                        PollingOff pollingOffInstance = PollingOff.getInstance();
                        parsedTLV =pollingOffInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Polling Off : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        handleCommandSleep();
                        tkCmd.terminalResponse(pollingOffInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), pollingOffInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_SETUP_EVENT_LIST:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Setup Event List");
                        SetupEventList eventLsitInstance = SetupEventList.getInstance();
                        parsedTLV =eventLsitInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Setup Event List : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //System.out.println(SetupMenu.getInstance().getCommandTLVList());
                        tkCmd.terminalResponse(eventLsitInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), eventLsitInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_SETUP_CALL:
                        SetupCall setupCallInstance = SetupCall.getInstance();
                        parsedTLV =setupCallInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Setup Call : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        if(setupCallInstance.getAlphaText().equals(""))
                        {
                            appList.setText("Setup Call...");
                        }
                        else
                        {
                            appList.setText(setupCallInstance.getAlphaText());
                        }
                        inputText.setText(setupCallInstance.getDailedString());
                        
                        rendorScreenComponent(true, false, false, true);
                        currentlySelectedCommand = TOC_SETUP_CALL;
                        break;
                        
                    case TOC_SEND_SS:
                        SendSS sendSSInstance = SendSS.getInstance();
                        parsedTLV =sendSSInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Send Supplementory Service : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        appList.setText(sendSSInstance.getAlphaText());
                        inputText.setText(sendSSInstance.getSSStringText());
                        rendorScreenComponent(true, false, false, true);
                        currentlySelectedCommand = TOC_SEND_SS;
                        break;
                        
                    case TOC_SEND_USSD:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Send USSD Command");
                        SendUSSD sendUssdInstance = SendUSSD.getInstance();
                        parsedTLV =sendUssdInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Send USSD Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        appList.setText(sendUssdInstance.getAlphaText());
                        inputText.setText(sendUssdInstance.getUSSDTextString());
                        rendorScreenComponent(true, false, false, true);
                        currentlySelectedCommand = TOC_SEND_USSD;
                        break;
                        
                    case TOC_SEND_SMS:
                        secondLevelTreeItem.clear();
                        SendSMS sendSMSInstance = SendSMS.getInstance();
                        parsedTLV =sendSMSInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Send Short Message Service : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //tkCmd.terminalResponse(sendSMSInstance.prepareTerminalResponse());
                        if(sendSMSInstance.getAlphaText().equals(""))
                        {
                            appList.setText("Sending SMS...");
                        }
                        else
                        {
                            appList.setText(sendSMSInstance.getAlphaText());
                        }
                        currentlySelectedCommand = TOC_SEND_SMS;
                        rendorScreenComponent(true, false, false, false);
                        break;
                        
                    case TOC_SEND_DTMF:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Send DTMF Command");
                        SendDTMF sendDTMFInstance = SendDTMF.getInstance();
                        parsedTLV = sendDTMFInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Send DTMF Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_SEND_DTMF;
                        //tkCmd.terminalResponse(sendSMSInstance.prepareTerminalResponse());
                        if(sendDTMFInstance.getAlphaText().equals(""))
                        {
                            appList.setText("Sending DTMF...");
                        }
                        else
                        {
                            appList.setText(sendDTMFInstance.getAlphaText());
                        }
                        inputText.setText(sendDTMFInstance.getDTMFString());
                        rendorScreenComponent(true, false, false, true);
                        break;
                        
                    case TOC_LAUNCH_BROWSER:
                        LaunchBrowser lbInstance = LaunchBrowser.getInstance();
                        parsedTLV =lbInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Launch Browser : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_LAUNCH_BROWSER;
                        appList.setText("Launching browser...");
                        inputText.setText(lbInstance.getURL());
                        rendorScreenComponent(true, false, false, true);
                        break;
                        
                    case TOC_GEO_LOCATION_REQUEST:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Geo Location Request");
                        GeographicalLocation geoInstance = GeographicalLocation.getInstance();
                        parsedTLV = geoInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Geo Location Request : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        handleCommandSleep();
                        tkCmd.terminalResponse(geoInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), geoInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_PLAY_TONE:
                        PlayTone ptInstance = PlayTone.getInstance();
                        parsedTLV = ptInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Play Tone Commnad : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_PLAY_TONE;
                        if(ptInstance.getAlphaText().equals(""))
                        {
                            appList.setText("Playing Tone...");
                        }
                        else
                        {
                            appList.setText(ptInstance.getAlphaText());
                        }
                        rendorScreenComponent(true, false, false, false);
                        break;
                        
                    case TOC_DISPLAY_TEXT:
                        DisplayText dtInstance = DisplayText.getInstance();
                        parsedTLV = dtInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Display Text : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_DISPLAY_TEXT;
                        inputText.setText(dtInstance.getTextString());
                        rendorScreenComponent(false, false, false, true);
                        break;
                        
                    case TOC_GET_INKEY:
                        GetInkey inkeyInstance = GetInkey.getInstance();
                        parsedTLV = inkeyInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Get Inkey Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_GET_INKEY;
                        if(inkeyInstance.getTextString().equals(""))
                        {
                            appList.setText("Get Inkey...");
                        }
                        else
                        {
                            appList.setText(inkeyInstance.getTextString());
                        }
                        inputText.setEditable(true);
                        rendorScreenComponent(true, false, false, true);
                        break;
                        
                    case TOC_GET_INPUT:
                        GetInput giInstance = GetInput.getInstance();
                        parsedTLV = giInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Get Input Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_GET_INPUT;
                        if(giInstance.getTextString().equals(""))
                        {
                            appList.setText("Get Input");
                        }
                        else
                        {
                            appList.setText(giInstance.getTextString());
                        }
                        inputText.setEditable(true);
                        rendorScreenComponent(true, false, false, true);
                        break;
                        
                    case TOC_SELECT_ITEM:
                        secondLevelTreeItem.clear();
                        SelectItem siInstance = SelectItem.getInstance();
                        //logTreeManipulator("Proactive Command: Select Item Command : "+tkCmd.getResponseData(),siInstance.getTRTLVList(), thirtLevelTreeItem);
                        items = FXCollections.observableArrayList();
                        menuList.setItems(items);
                        for(int i =0; i<siInstance.getMenuItemList().size();i++)
                        {
                            items.add(siInstance.getItemText(siInstance.getMenuItemList().get(i)));
                        }
                        this.appList.setText(siInstance.getAlphaText());
                        menuList.setItems(items);
                        parsedTLV = siInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Select Item Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand  = TOC_SELECT_ITEM;
                        isTRPending = true;
                        rendorScreenComponent(true, true, false, false);
                        //TODO
                        break;
                        
                    case TOC_SETUP_MENU:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Setup Menu Command");
                        SetupMenu smenuInstance = SetupMenu.getInstance();
                        parsedTLV =smenuInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Setup Menu Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        tkCmd.terminalResponse(smenuInstance.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        items.clear();
                        for(int i=0;i<smenuInstance.getMenuItemListTLV().size();i++)
                        {
                            items.add(smenuInstance.getMenuItemText(smenuInstance.getMenuItemListTLV().get(i)));
                        }
                        currentlySelectedCommand  = SETUP_MENU_COMMAND;
                        menuList.setItems(items);
                        rendorScreenComponent(true, true, false, false);
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), smenuInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_PLI:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Provide Local Information");
                        ProvideLocalInfo pliInstance = ProvideLocalInfo.getInstance();
                        parsedTLV = pliInstance.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Provide Local Information : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        List<String> tr = preparePLIResponse(pliInstance.getCommandDetailTLV());
                        tkCmd.terminalResponse(pliInstance.prepareTerminalResponse(tr));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), pliInstance.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_TIMER_MANAGEMENT:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Timer Management");
                        TimerManagement tmgmt = TimerManagement.getInstance();
                        parsedTLV = tmgmt.getCommandTLVList();
                        logTreeManipulator("Proactive Command:  Timer Management : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        tkCmd.terminalResponse(tmgmt.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), tmgmt.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_SETUP_IDLE_MODE_TEXT:
                        SetupIdleModeText idleModeText = SetupIdleModeText.getInstance();
                        parsedTLV = idleModeText.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Setup Idle Mode Text : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        currentlySelectedCommand = TOC_SETUP_IDLE_MODE_TEXT;
                        inputText.setText(idleModeText.getTextString());
                        rendorScreenComponent(false, false, false, true);
                        
                        break;
                        
                    case TOC_PERFORM_CARD_APDU:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Perform Card APDU");
                        PerformCardAPDU perfromCardAPDU = PerformCardAPDU.getInstance();
                        parsedTLV = perfromCardAPDU.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Provide Local Information : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //TODO
                        String rapduTLV=null;
                        tkCmd.terminalResponse(perfromCardAPDU.prepareTerminalResponse(rapduTLV));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), perfromCardAPDU.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_POWER_ON_CARD:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Power ON Card");
                        PowerOnCard poserOnCard = PowerOnCard.getInstance();
                        parsedTLV = poserOnCard.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Power ON Card : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //TODO: get the Card ATR detail
                        String cardATR = null;
                        tkCmd.terminalResponse(poserOnCard.prepareTerminalResponse(cardATR));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), poserOnCard.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_POWER_OFF_CARD:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Power OFF Card");
                        PowerOffCard powerOffCard = PowerOffCard.getInstance();
                        parsedTLV = powerOffCard.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Power OFF Card : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        tkCmd.terminalResponse(powerOffCard.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), powerOffCard.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_GET_READER_STATUS:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Get Reader Status");
                        GetReaderStatus getReaderStatus = GetReaderStatus.getInstance();
                        parsedTLV = getReaderStatus.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Power OFF Card : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //TODO
                        String cardReaderStatusTLV = null;
                        String cardReaderIdentifier = null;
                        tkCmd.terminalResponse(getReaderStatus.prepareTerminalResponse(cardReaderStatusTLV,cardReaderIdentifier));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), getReaderStatus.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_RUN_AT_COMMAND:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Run AT Command");
                        RunATCommand runAtCommand = RunATCommand.getInstance();
                        parsedTLV = runAtCommand.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Run AT Command : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        String atResponseTLV = null;
                        tkCmd.terminalResponse(runAtCommand.prepareTerminalResponse(atResponseTLV));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), runAtCommand.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_LANGUAGE_NOTIFICATION:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Language Notification");
                        LanguageNotification langNotification = LanguageNotification.getInstance();
                        parsedTLV = langNotification.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Language Notification : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        tkCmd.terminalResponse(langNotification.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), langNotification.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_OPEN_CHANNEL:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Open Channel");
                        OpenChannel openChannel = OpenChannel.getInstance();
                        parsedTLV = openChannel.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Open Channel : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        String openChannelResponse = generateOpenChannelResponseData(openChannel.getCommandDetailTLV());
                        tkCmd.terminalResponse(openChannel.prepareTerminalResponse(openChannelResponse));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), openChannel.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_CLOSE_CHANNEL:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Close Channel");
                        CloseChannel closeChannel = CloseChannel.getInstance();
                        parsedTLV = closeChannel.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Close Channel : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        tkCmd.terminalResponse(closeChannel.prepareTerminalResponse(0));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), closeChannel.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_RECEIVE_DATA:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Receive Data");
                        ReceiveData receiveData = ReceiveData.getInstance();
                        parsedTLV = receiveData.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Receive Data : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        String channelDataTLV = null;
                        String channelDataLengthTLV = null;
                        tkCmd.terminalResponse(receiveData.prepareTerminalResponse(channelDataTLV, channelDataLengthTLV));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), receiveData.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_SEND_DATA:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Send Data");
                        SendData sendData = SendData.getInstance();
                        parsedTLV = sendData.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Send Data : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //TODO
                        String channelDataLengthTLVSD = null;
                        tkCmd.terminalResponse(sendData.prepareTerminalResponse(channelDataLengthTLVSD));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), sendData.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    case TOC_GET_CHANNEL_STATUS:
                        secondLevelTreeItem.clear();
                        secondLevelTreeItem.add("Proactive Command: Get Channel Status");
                        GetChannelStatus channelStatus = GetChannelStatus.getInstance();
                        parsedTLV = channelStatus.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Get Channel Status : "+tkCmd.getResponseData(), parsedTLV, thirtLevelTreeItem);
                        //TODO
                        String channelStatusTLV = null;
                        tkCmd.terminalResponse(channelStatus.prepareTerminalResponse(channelStatusTLV));
                        secondLevelTreeItem.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), channelStatus.getTRTLVList(), thirtLevelTreeItem);
                        break;
                        
                    /**case TOC_SERVICE_SEARCH:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Service Search");
                        ServiceSearch serviceSearch = ServiceSearch.getInstance();
                        parsedTLV = serviceSearch.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Service Search : "+tkCmd.getResponseData(), parsedTLV, null);
                        tkCmd.terminalResponse(serviceSearch.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), serviceSearch.getTRTLVList(), null);
                        break;
                        
                    case TOC_GET_SERVICE_INFO:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Get Service Information");
                        GetServiceInformation getServiceInfo = GetServiceInformation.getInstance();
                        parsedTLV = getServiceInfo.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Get Service Information : "+tkCmd.getResponseData(), parsedTLV, null);
                        tkCmd.terminalResponse(getServiceInfo.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), getServiceInfo.getTRTLVList(), null);
                        break;
                        
                    case TOC_DECLARE_SERVICE:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Declare Service");
                        DeclareService declareService = DeclareService.getInstance();
                        parsedTLV = declareService.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Declare Service : "+tkCmd.getResponseData(), parsedTLV, null);
                        tkCmd.terminalResponse(declareService.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), declareService.getTRTLVList(), null);
                        break;
                        
                    case TOC_SET_FRAME:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Set Frame");
                        SetFrames setFrames = SetFrames.getInstance();
                        parsedTLV = setFrames.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Set Frame : "+tkCmd.getResponseData(), parsedTLV, null);
                        tkCmd.terminalResponse(setFrames.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), setFrames.getTRTLVList(), null);
                        break;
                        
                    case TOC_GET_FRAME_STATUS:
                        secondLevel.clear();
                        secondLevel.add("Proactive Command: Get Frame Status");
                        GetFrameStatus getFrameStatus = GetFrameStatus.getInstance();
                        parsedTLV = getFrameStatus.getCommandTLVList();
                        logTreeManipulator("Proactive Command: Get Frame Status : "+tkCmd.getResponseData(), parsedTLV, null);
                        tkCmd.terminalResponse(getFrameStatus.prepareTerminalResponse());
                        secondLevel.clear();
                        logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), getFrameStatus.getTRTLVList(), null);
                        break;*/
                        
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
        thirdLevel.clear();
        if(secondLevel!=null)
        {
            for(int i=0; i<secondLevel.size();i++)
            {
                String tlvName = tlvParser.getTLVName(secondLevel.get(i).toString());
                secondLevelItem = new TreeItem<String>(tlvName + " ( "+secondLevel.get(i).toString()+" ) ");
                firstLevelItem.getChildren().add(secondLevelItem);
                thirdLevel = tlvParser.parseTLV(secondLevel.get(i).toString());
                //System.out.println(thirdLevel);
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

    public void handleCommandSleep()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }
    @Override
    public void handle(javafx.scene.input.MouseEvent event) 
    {
        if(event.getSource() == menuList)
        {
            int index = menuList.getSelectionModel().getSelectedIndex();
            if(currentlySelectedCommand == SETUP_MENU_COMMAND)
            {  
                EnvelopePreparator envPrep = EnvelopePreparator.getInstance();
                SetupMenu smenuIns = SetupMenu.getInstance();
                String selectedMenuItem = smenuIns.getMenuItemListTLV().get(index).substring(4,6);
                tkCmd.envelope(envPrep.prepareEnvMenuSelection(selectedMenuItem, false));
                System.out.println(envPrep.prepareEnvMenuSelection(selectedMenuItem, false));
                secondLevelTreeItem.clear();
                secondLevelTreeItem.add("Status Word(SW1 SW2) : "+tkCmd.getSW1SW2());
                System.out.println("Last executed comand"+tkCmd.getLastExecutedCommand());
                System.out.println("Last executed comand"+envPrep.getEnvTLVList());
                logTreeManipulator("Envelope Menu Selection :"+tkCmd.getLastExecutedCommand(), envPrep.getEnvTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
            }
            else if(currentlySelectedCommand == TOC_SELECT_ITEM)
            {
                SelectItem sItem = SelectItem.getInstance();
                String selectedMenuItem = sItem.getMenuItemList().get(index).substring(4,6);
                String tr = sItem.prepareTerminalResponse(selectedMenuItem,0);
                tkCmd.terminalResponse(tr);
                logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), sItem.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
            }
            //Handle the Main Menu List in the main menu list
            else
            {
                
            }
        }
    }

    private List<String> preparePLIResponse(String commandDetailTLV) {
        byte cq = (byte)Integer.parseInt(commandDetailTLV.substring(8, 10),16);
        return null;
    }

    private String generateOpenChannelResponseData(String commandDetailTLV) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void handleHomeButtonAction() 
    {
        switch(currentlySelectedCommand)
        {
            case TOC_SEND_DTMF:
                SendDTMF sendDTMFInstance = SendDTMF.getInstance();
                tkCmd.terminalResponse(sendDTMFInstance.prepareTerminalResponse(0));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), sendDTMFInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_LAUNCH_BROWSER:
                secondLevelTreeItem.clear();
                LaunchBrowser lbInstance = LaunchBrowser.getInstance();
                tkCmd.terminalResponse(lbInstance.prepareTerminalResponse(0));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), lbInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SEND_SMS:
                secondLevelTreeItem.clear();
                SendSMS ss= SendSMS.getInstance();
                tkCmd.terminalResponse(ss.prepareTerminalResponse(0));
                logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), ss.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_INPUT:
                GetInput giInstance = GetInput.getInstance();
                tkCmd.terminalResponse(giInstance.prepareTerminalResponse(inputText.getText(),"04",0));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), giInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_INKEY:
                GetInkey inkeyInstance = GetInkey.getInstance();
                tkCmd.terminalResponse(inkeyInstance.prepareTerminalResponse(inputText.getText(),"04",0));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), inkeyInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SETUP_CALL:
                SetupCall setupCallInstance = SetupCall.getInstance();
                tkCmd.terminalResponse(setupCallInstance.prepareTerminalResponse(0));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), setupCallInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SEND_USSD:
                SendUSSD sendUssdInstance = SendUSSD.getInstance();
                tkCmd.terminalResponse(sendUssdInstance.prepareTerminalResponse("04", 0,"Response is being sent"));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), sendUssdInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SEND_SS:
                SendSS ssInstance = SendSS.getInstance();
                tkCmd.terminalResponse(ssInstance.prepareTerminalResponse(0));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), ssInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_DISPLAY_TEXT:
                DisplayText dt = DisplayText.getInstance();
                tkCmd.terminalResponse(dt.prepareTerminalResponse(0));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), dt.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SETUP_IDLE_MODE_TEXT:
                SetupIdleModeText siModeInstance = SetupIdleModeText.getInstance();
                tkCmd.terminalResponse(siModeInstance.prepareTerminalResponse());
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), siModeInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_PLAY_TONE:
                PlayTone ptInstance = PlayTone.getInstance();
                tkCmd.terminalResponse(ptInstance.prepareTerminalResponse(0));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), ptInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
        }
      
    }

    void handleCancelButtonAction() 
    { 
        switch(currentlySelectedCommand)
        {    
            case TOC_GET_INPUT:
                GetInput giInstance = GetInput.getInstance();
                tkCmd.terminalResponse(giInstance.prepareTerminalResponse(inputText.getText(),"04",11));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), giInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_INKEY:
                GetInkey inkeyInstance = GetInkey.getInstance();
                tkCmd.terminalResponse(inkeyInstance.prepareTerminalResponse(inputText.getText(),"04",11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), inkeyInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_DISPLAY_TEXT:
                DisplayText dt = DisplayText.getInstance();
                tkCmd.terminalResponse(dt.prepareTerminalResponse(11));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), dt.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SELECT_ITEM:
                SelectItem siInstance = SelectItem.getInstance();
                tkCmd.terminalResponse(siInstance.prepareTerminalResponse(null,11));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), siInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
        }
    }

    
    void handleExitButtonAction() 
    { 
        switch(currentlySelectedCommand)
        {
            case TOC_SETUP_CALL:
                SetupCall setupCallInstance = SetupCall.getInstance();
                tkCmd.terminalResponse(setupCallInstance.prepareTerminalResponse(10));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : "+tkCmd.getLastExecutedCommand(), setupCallInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SEND_DTMF:
                break;
                
            case TOC_PLAY_TONE:
                PlayTone ptInstance = PlayTone.getInstance();
                tkCmd.terminalResponse(ptInstance.prepareTerminalResponse(10));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), ptInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_DISPLAY_TEXT:
                DisplayText dt = DisplayText.getInstance();
                tkCmd.terminalResponse(dt.prepareTerminalResponse(10));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), dt.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_INPUT:
                GetInput giInstance = GetInput.getInstance();
                tkCmd.terminalResponse(giInstance.prepareTerminalResponse(11));
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), giInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_INKEY:
                GetInkey inkeyInstance = GetInkey.getInstance();
                tkCmd.terminalResponse(inkeyInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), inkeyInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SELECT_ITEM:
                SelectItem siInstance = SelectItem.getInstance();
                tkCmd.terminalResponse(siInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), siInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_OPEN_CHANNEL:
                OpenChannel ocInstance = OpenChannel.getInstance();
                tkCmd.terminalResponse(ocInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), ocInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_CLOSE_CHANNEL:
                CloseChannel ccInstance = CloseChannel.getInstance();
                tkCmd.terminalResponse(ccInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), ccInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_RECEIVE_DATA:
                ReceiveData rdInstance = ReceiveData.getInstance();
                tkCmd.terminalResponse(rdInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), rdInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SEND_DATA:
                SendData sdInstance = SendData.getInstance();
                tkCmd.terminalResponse(sdInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), sdInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_CHANNEL_STATUS:
                GetChannelStatus gcStatus = GetChannelStatus.getInstance();
                tkCmd.terminalResponse(gcStatus.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), gcStatus.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_SERVICE_SEARCH:
                ServiceSearch ssInstance = ServiceSearch.getInstance();
                tkCmd.terminalResponse(ssInstance.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), ssInstance.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_GET_SERVICE_INFO:
                GetServiceInformation gsInfo = GetServiceInformation.getInstance();
                tkCmd.terminalResponse(gsInfo.prepareTerminalResponse(11));
                secondLevelTreeItem.clear();
                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), gsInfo.getTRTLVList(), thirtLevelTreeItem);
                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
                break;
                
            case TOC_DISPLAY_MULTIMEDIA_MESSAGE:
//                 gsInfo = GetServiceInformation.getInstance();
//                tkCmd.terminalResponse(gsInfo.prepareTerminalResponse(11));
//                secondLevelTreeItem.clear();
//                logTreeManipulator("Terminal Response : " + tkCmd.getLastExecutedCommand(), gsInfo.getTRTLVList(), thirtLevelTreeItem);
//                startSKTFlow(tkCmd.getSW1(), tkCmd.getSW2());
               break;
        }
    }

    void handleCallButtonAction() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
