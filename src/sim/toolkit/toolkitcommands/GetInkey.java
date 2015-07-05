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
public class GetInkey extends BasicCommand{
    
    private static GetInkey singletonInstance;
     
    private String textStringTLV;
    private String durationTLV;
    private GetInkey(){}
    
    public static GetInkey getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new GetInkey();
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
        byte tagValue;
        int temp=0;
        while(tlvStartOffset<tlvendOffset)
        {
           // tagValue = (byte)Integer.parseInt(command.substring(tlvStartOffset, tlvStartOffset+2), 16);
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
                    
                case TEXT_STRING_TAG:case (byte)(TEXT_STRING_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    textStringTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(textStringTLV);
                    break;
                    
                case ICON_IDENTIFIER_TAG: case (byte)(ICON_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    iconIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(iconIdentifierTLV);
                    break;
                    
                case DURATION_TAG: case (byte)(DURATION_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    durationTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(durationTLV);
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
             tlvStartOffset+=temp;
        }
       
    }
    
   
    public String getDrationTLV()
    {
        return durationTLV;
    }
    public String getTextStringTLV()
    {
        return textStringTLV;
    }
    
    public String getTextString()
    {
        if(textStringTLV.length()>6)
        {
            return new String(convertToByteArray(textStringTLV.substring(6)));
        }
        else
        {
            return "";
        }
    }
    public String prepareTerminalResponse(String inputText, String dcs, int tr) 
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
                
                trTLvList.add(TLVPreperator.getInstance().prepareTextStringTLV(TEXT_STRING_TAG, dcs, inputText));
                terminalResponse.append(trTLvList.get(3));
                break;
            case 10:
                trTLvList.add("830110");
                terminalResponse.append(trTLvList.get(2));
                break;
            case 11:
                trTLvList.add("830111");
                terminalResponse.append(trTLvList.get(2));
                break;
        }
        return terminalResponse.toString();
    }
}
