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
public class DisplayMultimediaMessage extends BasicCommand{
    private static DisplayMultimediaMessage singletonInstance;
    private String fileListTLV;
    private String multimediaMsgIdTLV;
    private String immediateResponseTLV;
    
    private DisplayMultimediaMessage(){}
    
    public static DisplayMultimediaMessage getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new DisplayMultimediaMessage();
        }
        return singletonInstance;
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
                    
                case ICON_IDENTIFIER_TAG: case (byte)(ICON_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    iconIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(iconIdentifierTLV);
                    break;
                  
                case ALPHA_IDENTIFIER_TAG: case (byte)(ALPHA_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    alphaIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(alphaIdentifierTLV);
                
                case MULTIMEDIA_MESSAGE_IDENTIFIER_TAG: case (byte)(MULTIMEDIA_MESSAGE_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    multimediaMsgIdTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(multimediaMsgIdTLV);
                    break;
                    
                case IMMEDIATE_RESPONSE_TAG: case (byte)(IMMEDIATE_RESPONSE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    immediateResponseTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(immediateResponseTLV);
                    break;
                case TEXT_ATTRIBUTE_TAG: case (byte)(TEXT_ATTRIBUTE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    textAttributeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(textAttributeTLV);
                    break;
                    
                case FILE_LIST_TAG: case (byte)(FILE_LIST_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    fileListTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(fileListTLV);
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
    
    
     public String getMMMsgIdTLV() {
        return multimediaMsgIdTLV;
    }
     
     
    public String getFileListTLV() {
        return fileListTLV;
    }
    
    public String getImmediateResponseTLV()
    {
        return immediateResponseTLV;
    }
}