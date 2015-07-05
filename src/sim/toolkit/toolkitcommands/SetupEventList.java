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
public class SetupEventList extends BasicCommand {
    
     private static SetupEventList singletonInstance;
    
     private String eventListTLV;
     
    private SetupEventList(){}
    
    public static SetupEventList getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SetupEventList();
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
             tagValue = (byte)Integer.parseInt(command.substring(tlvStartOffset, tlvStartOffset+2), 16);
            
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
                case EVENT_LIST_TAG: case (byte)(EVENT_LIST_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    eventListTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(eventListTLV);
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String getEventListTLV()
    {
        return eventListTLV;
    }
    
    
}
