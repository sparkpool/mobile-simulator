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
public class SetFrames extends BasicCommand{
   
    private static SetFrames singletonInstance;
    private String frameLayoutTLV;
    private String defaultFrameIdentifierTLV;
    
    private SetFrames(){}
    
    public static SetFrames getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SetFrames();
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
        int frameIdCount =0;
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
                    
                case FRAME_LAYOUT_TAG: case (byte)(FRAME_LAYOUT_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    frameLayoutTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(frameLayoutTLV);
                    break;
                    
                case FRAME_IDENTIFIER_TAG: case (byte)(FRAME_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    if(frameIdCount==0)
                    {
                    frameIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(frameIdentifierTLV);
                    }
                    else if(frameIdCount==1)
                    {
                        defaultFrameIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                        commandTLVList.add(defaultFrameIdentifierTLV);
                    }
                    frameIdCount++;
                    break; 
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String getFrameLayput()
    {
        return frameLayoutTLV;
    }
    
    public String getDefaultFrameIdentifer()
    {
        return defaultFrameIdentifierTLV;
    }
}
