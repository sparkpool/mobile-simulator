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
public class DeclareService extends BasicCommand{
    private static DeclareService declareServiceInstance;
    private String serviceRecordTLV;
    private String uiccTransportTLV;
    
    private DeclareService(){}
    
    public static DeclareService getInstance()
    {
        if(declareServiceInstance == null)
        {
            declareServiceInstance = new DeclareService();
        }
        return declareServiceInstance;
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
                    
                case SERVICE_RECORD_TAG: case (byte)(SERVICE_RECORD_TAG|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    serviceRecordTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(serviceRecordTLV);
                    break;
                    
                case UICC_TRANSPORT_LEVEL: case (byte)(UICC_TRANSPORT_LEVEL|TAG_SET_CR):
                    temp = getDataLength(command,tlvStartOffset);
                    uiccTransportTLV = command.substring(tlvStartOffset, tlvStartOffset+temp);
                    commandTLVList.add(uiccTransportTLV);
                    break;
                    
                default:
                    //Provide mechanizm to handle the invalid command case
                    return;
            }
            tlvStartOffset+=temp;
        }
    }
    
    public String getServiceRecordTLV()
    {
        return serviceRecordTLV;
    }
    public String getUICCTransportTLV()
    {
        return uiccTransportTLV;
    }

    public String prepareTerminalResponse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
