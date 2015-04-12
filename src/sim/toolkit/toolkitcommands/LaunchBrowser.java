/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class LaunchBrowser extends BasicCommand{
    
    private static LaunchBrowser singletonInstance;
    private String browserIdentityTLV;
    private String urlTLV;
    private String bearerTLV;
    private String gatewayTLV;
    private String userIdTLV;
    private String passwordTLV;
    private ArrayList<String> provisioningTLVList= new ArrayList<>();
    private LaunchBrowser(){}
    
    public static LaunchBrowser getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new LaunchBrowser();
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
        int textStringCount =0;
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
                    
                case ALPHA_IDENTIFIER_TAG: case (byte)(ALPHA_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    alphaIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(alphaIdentifierTLV);
                    break;
                    
                case BROWSER_IDENTITY_TAG:case (byte)(BROWSER_IDENTITY_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    browserIdentityTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(browserIdentityTLV);
                    break;
                    
                case URL_TAG:case (byte)(URL_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    urlTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(urlTLV);
                    break;
                    
                case BEARER_TAG:case (byte)(BEARER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    bearerTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(bearerTLV);
                    break;
                    
                case PROVISIONING_FILE_TAG:case (byte)(PROVISIONING_FILE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    String protlv  = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    provisioningTLVList.add(protlv);
                    commandTLVList.add(protlv);
                    break;
                    
                case TEXT_STRING_TAG: case (byte)(TEXT_STRING_TAG|TAG_SET_CR):
                    if(textStringCount == 0)
                    {
                        temp = getDataLength(command,tlvStartOffset);
                        gatewayTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(gatewayTLV);
                    }
                    else if(textStringCount ==1)
                    {
                        temp = getDataLength(command,tlvStartOffset);
                        userIdTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(userIdTLV);
                    }
                    else if(textStringCount ==2)
                    {
                        temp = getDataLength(command,tlvStartOffset);
                        passwordTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(passwordTLV);
                    }
                    textStringCount++;
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
            tlvStartOffset+=temp;
        }
    }
    
    public String getBrowserIdentityTLV()
    {
        return browserIdentityTLV;
    }
    
    public String URLTLV()
    {
        return urlTLV;
    }
    public String getProxyGatewayTLV()
    {
        return gatewayTLV;
    }
    
    public String getBearerTLV()
    {
        return bearerTLV;
    }
    
    public ArrayList<String> getProvisionReferenceTLVList()
    {
        return provisioningTLVList;
    }
    
    public String getUserIdTLV()
    {
        return userIdTLV;
    }
    public String getUserPassTLV()
    {
        return passwordTLV;
    }
}
