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
public class DisplayText extends BasicCommand{
    private static DisplayText singletonInstance;
    
    private String textStringTLV;
    private String immediateResponseTLV;
    private String durationTLV;
    
    private DisplayText(){}
    
    public static DisplayText getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new DisplayText();
        }
        return singletonInstance;
    }

    /**
     * This method used to parse the display Command
     * @param command 
     */    
    @Override
    public void parseCommand(String command) 
    {
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
                    //
                case IMMEDIATE_RESPONSE_TAG: case (byte)(IMMEDIATE_RESPONSE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    immediateResponseTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(immediateResponseTLV);
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
              tlvStartOffset+= temp;
        }
    }
    
    public String getImmediateResponseTLV()
    {
        return immediateResponseTLV;
    }
    public String getDrationTLV()
    {
        return durationTLV;
    }
    public String getTextStringTLV()
    {
        return textStringTLV;
    }
}
