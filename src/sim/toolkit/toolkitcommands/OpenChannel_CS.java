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
public class OpenChannel_CS extends BasicCommand
{
    
    private static OpenChannel_CS singletonInstance;
    private String addressTLV;
    private String subAddressTLV;
    private String duration1TLV;
    private String duration2TLV;
    private String bearerDescTLV;
    private String bufferSizeTLV;
    private String otherAddressTLV;
    private String userIdTLV;
    private String passwordTLV;
    private String uiccTransportTLV;
    private String dataDestination;
    
    private OpenChannel_CS(){}
    
    public static OpenChannel_CS getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new OpenChannel_CS();
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
        int durationCount=0;
        int textStringCount =0;
        int dataAddressCount =0;
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
                    
                case ICON_IDENTIFIER_TAG: case (byte)(ICON_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    iconIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(iconIdentifierTLV);
                    break;
                    
                case SUBADDRESS_TAG: case (byte)(SUBADDRESS_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    subAddressTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(subAddressTLV);
                    break;
                    
                case DURATION_TAG: case (byte) (DURATION_TAG | TAG_SET_CR):
                    temp = getDataLength(command, tlvStartOffset);
                     {

                        if (durationCount == 0) {
                            duration1TLV = command.substring(tlvStartOffset, tlvStartOffset + temp);
                            commandTLVList.add(duration1TLV);
                        } else if (durationCount == 1) {
                            duration2TLV = command.substring(tlvStartOffset, tlvStartOffset + temp);
                            commandTLVList.add(duration1TLV);
                        }
                    }
                    durationCount++;
                    break;
                    
                case BEARER_TAG: case (byte)(BEARER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    bearerDescTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(bearerDescTLV);
                    break;
                    
                case BUFFER_SIZE_TAG: case (BUFFER_SIZE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    bufferSizeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(bufferSizeTLV);
                    break;
                    
                case OTHER_ADDRESS_TAG: case (byte)(OTHER_ADDRESS_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    if(dataAddressCount ==0)
                    {
                    otherAddressTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(otherAddressTLV);
                    }
                    else if(dataAddressCount==1)
                    {
                        dataDestination = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(dataDestination);
                    }
                    dataAddressCount++;
                    break;
                    
                case TEXT_STRING_TAG: case (byte) (TEXT_STRING_TAG | TAG_SET_CR):
                    temp = getDataLength(command, tlvStartOffset);
                    if (textStringCount == 0) 
                    {
                        userIdTLV = command.substring(tlvStartOffset, tlvStartOffset + temp);
                        commandTLVList.add(userIdTLV);
                    } else if (textStringCount == 1) 
                    {
                        passwordTLV = command.substring(tlvStartOffset, tlvStartOffset + temp);
                        commandTLVList.add(passwordTLV);
                    }
                    textStringCount++;
                    break;
                    
                case UICC_TRANSPORT_LEVEL: case (byte)(UICC_TRANSPORT_LEVEL|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    uiccTransportTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(uiccTransportTLV);
                    break;
                    
                case FRAME_IDENTIFIER_TAG: case (byte)(FRAME_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    frameIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(frameIdentifierTLV);
                    break;
                        
                case TEXT_ATTRIBUTE_TAG: case (byte)(TEXT_ATTRIBUTE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    textAttributeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(textAttributeTLV);
                    break;
                
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
             tlvStartOffset+=temp;
        }
    }
    public String getSubAddressTLV()
    {
        return subAddressTLV;
    }
    public String getDuration1TLV()
    {
        return duration1TLV;
    }
    public String getDuration2TLV()
    {
        return duration2TLV;
    }
    public String getBearerDescTLV()
    {
        return bearerDescTLV;
    }
    public String getBufferSizeTLV()
    {
        return bufferSizeTLV;
    }
    public String getOtherAddressTLV()
    {
        return otherAddressTLV;
    }
    
    public String getUserIdTLV()
    {
        return userIdTLV;
    }
    public String getPasswordTLV()
    {
        return passwordTLV;
    }
    
    public String getUiccTransportTLV()
    {
        return uiccTransportTLV;
    }
    
    public String getDataDestinationTLV()
    {
        return dataDestination;
    }
    public String getAddressTLV()
    {
        return addressTLV;
    }
}
