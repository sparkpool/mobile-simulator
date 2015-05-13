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
public class ProvideLocalInfo extends BasicCommand{
    
    private static ProvideLocalInfo singletonInstance;
    
    private ProvideLocalInfo(){}
    
    public static ProvideLocalInfo getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new ProvideLocalInfo();
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
                    
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String prepareTerminalResponse()
    {
        StringBuilder terminalResponse = new StringBuilder();
        trTLvList.clear();
        terminalResponse.append(commandDetailTLV);
        trTLvList.add(commandDetailTLV);
        trTLvList.add("8202"+deviceIdentitiesTLV.substring(6, 8)+deviceIdentitiesTLV.substring(4, 6));
        terminalResponse.append(trTLvList.get(1));
        trTLvList.add("830100");
        terminalResponse.append(trTLvList.get(2)); 
        trTLvList.add("9307324F4523344556");
        terminalResponse.append(trTLvList.get(3));
        return terminalResponse.toString();
    }
}
