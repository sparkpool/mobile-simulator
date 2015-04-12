/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

import java.util.ArrayList;
import sim.toolkit.tlvparser.TLVConstants;

/**
 *
 * @author user
 */
public abstract class BasicCommand implements TLVConstants
{
    String commandDetailTLV;
    String deviceIdentitiesTLV;
    String alphaIdentifierTLV;
    String iconIdentifierTLV;
    String textAttributeTLV;
    String frameIdentifierTLV;
    ArrayList<String> commandTLVList = new ArrayList<>();
    
    /**
     * This method used to get all the TLv List of the command
     * @return 
     */
    public ArrayList<String> getCommandTLVList()
    {
        return commandTLVList;
    }
    
    abstract public void parseCommand(String command);
    
    /**
     * This method used to calculate the length of the data and including the length part of the TLV
     * @param command: Full command
     * @param tlvStartOffset: starting offset of the TLV in the command
     * @return : Return the length
     */
    int getDataLength(String command, int tlvStartOffset)
    {
        byte paddingCount = 4;
        int length = Integer.parseInt(command.substring(tlvStartOffset + 2, tlvStartOffset + 4), 16);
        if (command.substring(tlvStartOffset + 2, tlvStartOffset + 4).equals("81")) {
            length = Integer.parseInt(command.substring(tlvStartOffset + 4, tlvStartOffset + 6), 16);
            paddingCount = 6;
        }
        return length*2+paddingCount;
    }
    
    public String getCommandDetailTLV()
    {
        return commandDetailTLV;
    }
    
    public String getDeviceIdentitesTLV()
    {
        return deviceIdentitiesTLV;
    }
    
    public String getAlphaIdentiferTLV()
    {
        return alphaIdentifierTLV;
    }
    
    public String getIconIdentiferTLV()
    {
        return iconIdentifierTLV;
    }
    
    public String textAttributeTLV()
    {
        return textAttributeTLV;
    }
    
    public String getFrameIdentifierTLV()
    {
        return frameIdentifierTLV;
    }
}
