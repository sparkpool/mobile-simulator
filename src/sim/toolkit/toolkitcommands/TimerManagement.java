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
public class TimerManagement extends BasicCommand{
     
    private static TimerManagement singletonInstance;
    
    private String timerIdentiferTLV;
    private String timerValueTLV;
    
    private TimerManagement(){}
    
    public static TimerManagement getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new TimerManagement();
        }
        return singletonInstance;
    }

    @Override
    public void parseCommand(String command) {
         //check the Length of the BER TLV
        int tlvStartOffset =4;
        int tlvendOffset = Integer.parseInt(command.substring(2, 4),16)*2;
        if(command.substring(2,4).equals("81"))
        {
            tlvStartOffset = 6;
            tlvendOffset = Integer.parseInt(command.substring(4, 6),16)*2;
        }
        commandTLVList.clear();
        byte tagValue;
        int temp=0;
        while(tlvStartOffset<tlvendOffset)
        {
            tagValue = Byte.parseByte(command.substring(tlvStartOffset, tlvStartOffset+2), 16);
            
            switch (tagValue) 
            {
                case COMMAND_DETAILS_TAG:case (byte) (COMMAND_DETAILS_TAG | TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    commandDetailTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(commandDetailTLV);
                    break;
                    
                case DEVICE_IDENTITY_TAG: case (byte)(DEVICE_IDENTITY_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    deviceIdentitiesTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(deviceIdentitiesTLV);
                    break;
                    
                case TIMER_IDENTIFIER_TAG: case (byte)(TIMER_IDENTIFIER_TAG|TAG_SET_CR):
                     temp = getDataLength(command,tlvStartOffset);
                    timerIdentiferTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(timerIdentiferTLV);
                    break;
                    
                case TIMER_VALUE_TAG: case (byte)(TIMER_VALUE_TAG|TAG_SET_CR):
                     temp = getDataLength(command,tlvStartOffset);
                    timerValueTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(timerValueTLV);
                    break;
                    
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String gettimerIdTLV()
    {
        return timerIdentiferTLV;
    }
    
    public String getTimerValueTLV()
    {
        return timerValueTLV;
    }
}
