/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

import java.util.ArrayList;
import java.util.List;
import sim.toolkit.envelope.TLVPreperator;
import static sim.toolkit.tlvparser.TLVConstants.COMMAND_DETAILS_TAG;
import static sim.toolkit.tlvparser.TLVConstants.DEVICE_IDENTITY_TAG;
import static sim.toolkit.tlvparser.TLVConstants.FRAME_IDENTIFIER_TAG;
import static sim.toolkit.tlvparser.TLVConstants.ICON_IDENTIFIER_TAG;
import static sim.toolkit.tlvparser.TLVConstants.ITEMS_NEXT_ACTION_INDICATOR_TAG;
import static sim.toolkit.tlvparser.TLVConstants.ITEM_ICON_IDENTIFIER_LIST_TAG;
import static sim.toolkit.tlvparser.TLVConstants.ITEM_TAG;
import static sim.toolkit.tlvparser.TLVConstants.TAG_SET_CR;
import static sim.toolkit.tlvparser.TLVConstants.TEXT_ATTRIBUTE_TAG;

/**
 *
 * @author user
 */
public class SelectItem extends BasicCommand{
    
    private static SelectItem singletonInstance;
    
    private ArrayList<String> menuItemListTLV = new ArrayList<>();
    
    private String itemNextActionIndicatorTLV;
    
    private String itemIconIdentiferListTLV;
    
    private String itemsTextAttributeTLV;
    
    private String itemIdentifierTLV;
    
    private SelectItem(){}
    
    public static SelectItem getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SelectItem();
        }
        return singletonInstance;
    }

    @Override
    public void parseCommand(String command) {
        //To change body of generated methods, choose Tools | Templates.
        
         //check the Length of the BER TLV
        int tlvStartOffset =4;
        int tlvendOffset = Integer.parseInt(command.substring(2, 4),16)*2;
        if(command.substring(2,4).equals("81"))
        {
            tlvStartOffset = 6;
            tlvendOffset = Integer.parseInt(command.substring(4, 6),16)*2;
        }
        commandTLVList.clear();
        menuItemListTLV.clear();
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
                    
                case ITEM_TAG:case (byte)(ITEM_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    menuItemListTLV.add(command.substring(tlvStartOffset, tlvStartOffset+temp));
                    commandTLVList.add(command.substring(tlvStartOffset, tlvStartOffset+temp));
                    break;
                    
                case ITEM_IDENTIFIER_TAG: case (byte)(ITEM_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    itemIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(itemIdentifierTLV);
                    break;
                    
                case ICON_IDENTIFIER_TAG: case (byte)(ICON_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    iconIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(iconIdentifierTLV);
                    break;
                    
                case ITEMS_NEXT_ACTION_INDICATOR_TAG: case (byte)(ITEMS_NEXT_ACTION_INDICATOR_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    itemNextActionIndicatorTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(itemNextActionIndicatorTLV);
                    break;
                    
                case ITEM_ICON_IDENTIFIER_LIST_TAG: case (byte)(ITEM_ICON_IDENTIFIER_LIST_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    itemIconIdentiferListTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(itemIconIdentiferListTLV);
                    break;
                    
                case TEXT_ATTRIBUTE_TAG: case (byte)(TEXT_ATTRIBUTE_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    textAttributeTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(textAttributeTLV);
                    break;
                    
                case ALPHA_IDENTIFIER_TAG: case (byte)(ALPHA_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    alphaIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(alphaIdentifierTLV);
                    break;
                    
                case FRAME_IDENTIFIER_TAG: case (byte)(FRAME_IDENTIFIER_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    frameIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(frameIdentifierTLV);
                    break;
                    
                  case ITEM_TEXT_ATTRIBUTE_LIST_TAG: case (byte)(ITEM_TEXT_ATTRIBUTE_LIST_TAG|TAG_SET_CR):
                   temp = getDataLength(command,tlvStartOffset);
                    frameIdentifierTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(frameIdentifierTLV);
                       
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String getitemNextActionIndicatorTLV()
    {
        return itemNextActionIndicatorTLV;
    }
    
    public String getItemIconIdentiferListTLV()
    {
        return itemIconIdentiferListTLV;
    }
    
   
    public String getItemTextAttributeTLV()
    {
        return itemsTextAttributeTLV;
    }
    
    public String getItemIdentiferTLV()
    {
        return itemIdentifierTLV;
    }

    public String getItemText(String itemTLV)
    {
        return new String(convertToByteArray(itemTLV.substring(6)));
    }
    

    public List<String> getMenuItemList()
    {
        return menuItemListTLV;
    }
    
    
    public String prepareTerminalResponse(String selecteItemIndex, int tr) 
    {
        StringBuilder terminalResponse = new StringBuilder();
        trTLvList.clear();
        terminalResponse.append(commandDetailTLV);
        trTLvList.add(commandDetailTLV);
        trTLvList.add("8202"+deviceIdentitiesTLV.substring(6, 8)+deviceIdentitiesTLV.substring(4, 6));
        terminalResponse.append(trTLvList.get(1));
        switch(tr)
        {
            case 0:
                trTLvList.add("830100");
                terminalResponse.append(trTLvList.get(2));
                
                trTLvList.add(TLVPreperator.getInstance().prepareItemIdTLV(ITEM_IDENTIFIER_TAG, selecteItemIndex));
                terminalResponse.append(trTLvList.get(3));
                break;
            case 10:
                trTLvList.add("830110");
                terminalResponse.append(trTLvList.get(2));
                break;
            case 11:
                trTLvList.add("830111");
                terminalResponse.append(trTLvList.get(2));
                break;
        }
        return terminalResponse.toString();
    }
}
