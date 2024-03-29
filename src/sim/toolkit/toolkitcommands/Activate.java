/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

import static sim.toolkit.tlvparser.TLVConstants.ACTIVATE_DESCRIPTOR_TAG;
import static sim.toolkit.tlvparser.TLVConstants.COMMAND_DETAILS_TAG;
import static sim.toolkit.tlvparser.TLVConstants.DEVICE_IDENTITY_TAG;
import static sim.toolkit.tlvparser.TLVConstants.TAG_SET_CR;

/**
 *
 * @author user
 */
public class Activate extends BasicCommand{
    private static Activate activateInstance;
    private String activateDescriptorTLV;
    
    private Activate(){}
    public static Activate getInstance()
    {
        if(activateInstance == null)
        {
            activateInstance = new Activate();
        }
        return activateInstance;
    }

    @Override
    public void parseCommand(String command) {
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
                    
                case ACTIVATE_DESCRIPTOR_TAG: case (byte)(ACTIVATE_DESCRIPTOR_TAG|TAG_SET_CR): 
                    temp = getDataLength(command,tlvStartOffset);
                    activateDescriptorTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(activateDescriptorTLV);
                    break;
                    
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String getActivateDescriptorTLV()
    {
        return activateDescriptorTLV;
    }
}
