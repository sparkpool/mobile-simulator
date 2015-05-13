/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

import java.util.ArrayList;
import java.util.List;
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
    
    protected List<String> trTLvList = new ArrayList<String>();
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
    
    public List<String> getTRTLVList()
    {
        return trTLvList;
    }
    
    byte[] convertToByteArray(String data)
    {
        byte b1=0;
        byte b2=0;
        byte cmd[] = new byte[data.length()/2];
        int count =0;
        for(int i=0; i< data.length(); i=i+2)
        {
             switch(data.charAt(i))
             {
                 case '0':case '1':case '2': case '3':case '4':case '5':case '6':
                 case '7': case '8': case '9':
                     b1=(byte)((byte)(data.charAt(i)&0x00FF)-0x0030);
                     break;
                     
                 case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                     b1=(byte)((byte)(data.charAt(i)&0x00FF)-0x0037);
                     break;
             }
             switch(data.charAt(i+1))
             {
                 case '0':case '1':case '2': case '3':case '4':case '5':case '6':
                 case '7': case '8': case '9':
                     b2=(byte)((byte)(data.charAt(i+1)&0x00FF)-0x0030);
                     break;
                     
                 case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                     b2=(byte)((byte)(data.charAt(i+1)&0x00FF)-0x0037);
                     break;
             }
             cmd[count++]=(byte)(b1<<4|b2);
        }
        return cmd;
    }
}
