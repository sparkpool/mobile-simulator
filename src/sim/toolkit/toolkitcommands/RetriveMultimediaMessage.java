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
public class RetriveMultimediaMessage extends BasicCommand{
    
    private static RetriveMultimediaMessage singletonInstance;
    private String multimediaContentIDTLV;
    private String multimediaMsgIdTLV;
    private String multimediaMsgRefTLV;
    private String fileListTLV;
    
    private RetriveMultimediaMessage(){}
    
    public static RetriveMultimediaMessage getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new RetriveMultimediaMessage();
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
                    
                    
                case MULTIMEDIA_CONTENT_IDENTIFIER_TAG: case (byte)(MULTIMEDIA_CONTENT_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    multimediaContentIDTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(multimediaContentIDTLV);
                    break;
                    
                case MULTIMEDIA_MESSAGE_IDENTIFIER_TAG: case (byte)(MULTIMEDIA_MESSAGE_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    multimediaMsgIdTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(multimediaMsgIdTLV);
                    break;
                    
                case MULTIMEDIA_MESSAGE_REFERENCE_TAG: case (byte)(MULTIMEDIA_MESSAGE_REFERENCE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    multimediaMsgRefTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(multimediaMsgRefTLV);
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

    public String getMMMsgReferenceTLV() {
        return multimediaMsgRefTLV;
    }

    public String getFileListTLV() {
        return fileListTLV;
    }

    public String getMMContentIDTLV() {
        return multimediaContentIDTLV;
    }
}
