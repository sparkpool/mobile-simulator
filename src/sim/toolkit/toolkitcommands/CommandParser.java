/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

/**
 *
 * @author user
 */
public class CommandParser 
{
    private static CommandParser cpInstance;
    
    public static CommandParser getCommandParserInstance()
    {
        if(cpInstance == null)
        {
            cpInstance = new CommandParser();
        }
        return cpInstance;
    }
    
    public void parseCommand(String commandData)
    {
        //check the Length of the BER TLV
        int typeOfCommandOffset =10;
        if(commandData.substring(2,4).equals("81"))
        {
            typeOfCommandOffset = 12;
        }
        byte commandNumber = Byte.parseByte(commandData.substring(typeOfCommandOffset, typeOfCommandOffset+2), 16);
        switch(commandNumber)
        {
            //Refresh
            case 0x01:
                Refresh.getInstance().parseCommand(commandData);
                break;
            //More Time
            case 0x02:
                MoreTime.getInstance().parseCommand(commandData);
                break;
            //Poll Interval
            case 0x03:
                PollInterval.getInstance().parseCommand(commandData);
                break;
            //Polling Off    
            case 0x04:
                PollingOff.getInstance().parseCommand(commandData);
                break;
            //Setup Event List
            case 0x05:
                SetupEventList.getInstance().parseCommand(commandData);
                break;
            //Setup Call
            case 0x10:
                SetupCall.getInstance().parseCommand(commandData);
                break;
            //Send SS
            case 0x11:
                SendSS.getInstance().parseCommand(commandData);
                break;
            //Send USSD
            case 0x12:
                SendUSSD.getInstance().parseCommand(commandData);
                break;
            //Send SMS
            case 0x13:
                SendSMS.getInstance().parseCommand(commandData);
                break;
            //Send DTMF
            case 0x14:
                SendDTMF.getInstance().parseCommand(commandData);
                break;
            //Launch Broswer
            case 0x15:
                LaunchBrowser.getInstance().parseCommand(commandData);
                break;
            //Geographical Location request 
            case 0x16:
                GeographicalLocation.getInstance().parseCommand(commandData);
                break;
            //Play Tone
            case 0x20:
                PlayTone.getInstance().parseCommand(commandData);
                break;
            //Display Text
            case 0x21:
                DisplayText.getInstance().parseCommand(commandData);
                break;
            //Get Inkey
            case 0x22:
                GetInkey.getInstance().parseCommand(commandData);
                break;
            //Get Input
            case 0x23:
                GetInput.getInstance().parseCommand(commandData);
                break;
            //Select Item
            case 0x24:
                SelectItem.getInstance().parseCommand(commandData);
                break;
            //Setup Menu
            case 0x25:
                SetupMenu.getInstance().parseCommand(commandData);
                break;
            //Provide Local Information
            case 0x26:
                ProvideLocalInfo.getInstance().parseCommand(commandData);
                break;
            //Timer Management
            case 0x27:
                TimerManagement.getInstance().parseCommand(commandData);
                break;
            //Setup Idle Mode Text
            case 0x28:
                SetupIdleModeText.getInstance().parseCommand(commandData);
                break;
            //Perform Card APDU
            case 0x30:
                PerformCardAPDU.getInstance().parseCommand(commandData);
                break;
            //Power On Card
            case 0x31:
                PowerOnCard.getInstance().parseCommand(commandData);
                break;
            //Power Off card
            case 0x32:
                PowerOffCard.getInstance().parseCommand(commandData);
                break;
            //Get Reader Status
            case 0x33:
                GetReaderStatus.getInstance().parseCommand(commandData);
                break;
            //Run At Command
            case 0x34:
                RunATCommand.getInstance().parseCommand(commandData);
                break;
            //Language Notification
            case 0x35:
                LanguageNotification.getInstance().parseCommand(commandData);
                break;
            //Open channel
            case 0x40:
                OpenChannel_CS.getInstance().parseCommand(commandData);
                break;
            //close Channel
            case 0x41:
                CloseChannel.getInstance().parseCommand(commandData);
                break;
            //receive Data
            case 0x42:
                ReceiveData.getInstance().parseCommand(commandData);
                break;
            //Send Data
            case 0x43:
                SendData.getInstance().parseCommand(commandData);
                break;
            //Get Channel Status
            case 0x44:
                GetChannelStatus.getInstance().parseCommand(commandData);
                break;
            //Service Search
            case 0x45:
                ServiceSearch.getInstance().parseCommand(commandData);
                break;
            //Get Service Information
            case 0x46:
                GetServiceInformation.getInstance().parseCommand(commandData);
                break;
            //Declare Service
            case 0x47:
                DeclareService.getInstance().parseCommand(commandData);
            //Set Frames
            case 0x50:
                SetFrames.getInstance().parseCommand(commandData);
                break;
            //Get Frame status
            case 0x51:
                GetFrameStatus.getInstance().parseCommand(commandData);
                break;
            //Retrieve Multimedia Message
            case 0x60:
                RetriveMultimediaMessage.getInstance().parseCommand(commandData);
                break;
            //Submit Multimedia message
            case 0x61:
                SubmitMultimediaMessage.getInstance().parseCommand(commandData);
                break;
            //Display Multimedia message
            case 0x62:
                DisplayMultimediaMessage.getInstance().parseCommand(commandData);
                break;
            //Activate
            case 0x70:
                Activate.getInstance().parseCommand(commandData);
                break;
            //CONTACTLESS STATE CHANGED
            /**case 0x71:
                ContactlessStateChanged.getInstance().parseCommand(commandData);
                break;*/
            //COMMAND CONTAINER
            /**case 0x72:
                CommandContainer.getInstance().parseCommand(commandData);
                break;*/
            //ENCAPSULATED SESSION CONTROL
            /**case 0x73:
                EncapsulatedSessionControl.getInstance().parseCommand(commandData);
                break;*/
            //End of the proactive UICC session
            case (byte)0x81:
                break;
        }
    }
}
