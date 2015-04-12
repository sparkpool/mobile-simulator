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
public class SendSMS extends BasicCommand {
    private static SendSMS singletonInstance;
    
    private String addressTLV;
    private String smsTPDUTLV;
    private String cdmaTPDUTLV;
    
    private SendSMS(){}
    
    public static SendSMS getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SendSMS();
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
                case ADDRESS_TAG: case (byte)(ADDRESS_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    addressTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(addressTLV);
                    break;
                case SMS_TPDU_TAG: case (byte)(SMS_TPDU_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    addressTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(addressTLV);
                    break;
                    
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String getAddressTLV()
    {
        return addressTLV;
    }
    
    public String getSMSTPDUTLV()
    {
        return smsTPDUTLV;
    }
    
}
