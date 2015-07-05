/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

import sim.toolkit.envelope.TLVPreperator;
import static sim.toolkit.tlvparser.TLVConstants.TEXT_STRING_TAG;

/**
 *
 * @author user
 */
public class SendSS extends BasicCommand{
     private static SendSS singletonInstance;
    private String ssStringTLV;
    
    private SendSS(){}
    
    public static SendSS getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SendSS();
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
                    
                case ALPHA_IDENTIFIER_TAG: case (byte)(ALPHA_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    alphaIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(alphaIdentifierTLV);
                    break;
                    
                case USSD_STRING_TAG: case (byte)(USSD_STRING_TAG| TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    ssStringTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(ssStringTLV);
                    break;
                    
                case ICON_IDENTIFIER_TAG: case (byte)(ICON_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    iconIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(iconIdentifierTLV);
                    break;
                    
                case TEXT_ATTRIBUTE_TAG: case (byte)(TEXT_ATTRIBUTE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    textAttributeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(textAttributeTLV);
                    break;
                    
                case FRAME_IDENTIFIER_TAG: case (byte)(FRAME_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    frameIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(frameIdentifierTLV);
                    break;
                    
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
              tlvStartOffset+= temp;
        }
    }

    public String getSSStrigTLV()
    {
        return ssStringTLV;
    }
    
    public String getSSStringText()
    {
        return null;
    }

    public String prepareTerminalResponse(int tr)
    {
        StringBuilder terminalResponse = new StringBuilder();
        trTLvList.clear();
        terminalResponse.append(commandDetailTLV);
        trTLvList.add(commandDetailTLV);
        trTLvList.add("8202"+deviceIdentitiesTLV.substring(6, 8)+deviceIdentitiesTLV.substring(4, 6));
        terminalResponse.append(trTLvList.get(1));
        switch(tr)
        {
            case 0:
                trTLvList.add("830100");
                terminalResponse.append(trTLvList.get(2)); 
                break;
                
            case 14:
                trTLvList.add("830114");
                terminalResponse.append(trTLvList.get(2)); 
                break;
        }
        return terminalResponse.toString();
    }
}
