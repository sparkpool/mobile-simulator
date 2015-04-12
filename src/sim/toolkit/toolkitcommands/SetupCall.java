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
public class SetupCall extends BasicCommand{
    
    private static SetupCall singletonInstance;
    
    private String addressTLV;
    
    private String ccpTLV;
    
    private String subAddressTLV;
    
    private String durationTLV;
    
    private String alphaIdCallPhaseTLV;
    
    private String iconIDCallPhaseTLV;
    
    private String textAttributeCallPhase;
    
    private SetupCall(){}
    
    public static SetupCall getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SetupCall();
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
        int iconIdCount =0;
        int textAttCount =0;
        int alphaIdCount =0;
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
                    
                case ADDRESS_TAG:case (byte)(ADDRESS_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    addressTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(addressTLV);
                    break;
                    
                case ALPHA_IDENTIFIER_TAG: case (byte)(ALPHA_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    if(alphaIdCount ==1)
                    {
                        alphaIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(alphaIdentifierTLV);
                    }
                    else if(alphaIdCount ==1)
                    {
                        alphaIdCallPhaseTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(alphaIdCallPhaseTLV);
                    }
                    break;
                    
                case CAPABILITY_CONFIGURATION_PARAMETERS_TAG: case (byte)(CAPABILITY_CONFIGURATION_PARAMETERS_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    ccpTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(ccpTLV);
                    break;
                    
                case ICON_IDENTIFIER_TAG: case (byte)(ICON_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    if(iconIdCount ==0)
                    {
                        iconIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(iconIdentifierTLV);
                        iconIdCount++;
                    }
                    else if(iconIdCount == 1)
                    {
                        iconIDCallPhaseTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(iconIDCallPhaseTLV);
                        iconIdCount++;
                    }
                    break;
                    
                case DURATION_TAG: case (byte)(DURATION_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    durationTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(durationTLV);
                    break;
                    
                case SUBADDRESS_TAG: case (SUBADDRESS_TAG|TAG_SET_CR):
                     temp = getDataLength(command,tlvStartOffset);
                    subAddressTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(subAddressTLV);
                    break;
                    
                case TEXT_ATTRIBUTE_TAG: case (byte)(TEXT_ATTRIBUTE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    if(textAttCount ==0)
                    {
                        textAttributeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(textAttributeTLV);
                        textAttCount++;
                    }
                    else if(textAttCount == 1)
                    {
                        textAttributeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(textAttributeTLV);
                        textAttCount++;
                    }
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
    
    public String getTextAttributeCallPhaseTLV()
    {
        return textAttributeCallPhase;
    }
    
    public String getIconIdCallSetupPhaseTLV()
    {
        return iconIDCallPhaseTLV;
    }
    public String getAlphaIdCallSetupPhaseTLV()
    {
        return alphaIdCallPhaseTLV;
    }
    
    public String getDurationTLV()
    {
        return durationTLV;
    }
    public String getAddressTLV()
    {
        return addressTLV;
    }
    public String getCCPTLV()
    {
        return ccpTLV;
    }
    
    public String getSubAddressTLV()
    {
        return subAddressTLV;
    }
}
