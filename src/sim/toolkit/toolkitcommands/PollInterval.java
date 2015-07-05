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
public class PollInterval extends BasicCommand{
    
    private static PollInterval singletonInstance;
     
    private String durationTLV;
    
    private PollInterval(){}
    
    public static PollInterval getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new PollInterval();
        }
        return singletonInstance;
    }

    @Override
    public void parseCommand(String command) {
        //To change body of generated methods, choose Tools | Templates.
        
         //check the Length of the BER TLV
        int tlvStartOffset =4;
        int tlvendOffset = Integer.parseInt(command.substring(2, 4),16)*2;
        if(command.substring(2,4).equals("81"))
        {
            tlvStartOffset = 6;
            tlvendOffset = Integer.parseInt(command.substring(4, 6),16)*2;
        }
        commandTLVList.clear();
        int tagValue;
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
                    System.out.println(commandDetailTLV);
                    break;
                    
                case DEVICE_IDENTITY_TAG: case (byte)(DEVICE_IDENTITY_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    deviceIdentitiesTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(deviceIdentitiesTLV);
                    System.out.println(deviceIdentitiesTLV);
                    break;
                    
                case DURATION_TAG: case (byte)(DURATION_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    durationTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(durationTLV);
                    System.out.println(durationTLV);
                    break;
                 
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+= temp;
        }
    }
    
    
    public String getDurationTLV()
    {
        return durationTLV;
    }

}
