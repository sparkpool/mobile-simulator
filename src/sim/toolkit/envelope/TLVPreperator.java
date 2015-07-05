 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.envelope;

/**
 *
 * @author user
 */
public class TLVPreperator {
    private static TLVPreperator instance;
    private TLVPreperator(){}
    public static TLVPreperator getInstance()
    {
        if(instance == null)
        {
            instance = new TLVPreperator();
        }
        return instance;
    }
    
    /**
     * This method used to prepare the Address TLv based on the provided parameters
     * @param tag: Address Tag Value
     * @param tonNpi: TON/NPI of the address
     * @param dialingString: Dialing number including Country Code(CC) and mobile number
     * @return : Return the complete TLV
     */
    public String prepareAddressTLV(byte tag, String tonNpi, String dialingString)
    {
        StringBuilder addressTLV =new StringBuilder(tonNpi);
        if(dialingString.length()%2==1)
        {
            dialingString = dialingString+"F";
        }
        for(int i=0; i<dialingString.length();i=i+2)
        {
            addressTLV.append(dialingString.charAt(i+1)+dialingString.charAt(i));                    
        }
        
        return convertTagToString(tag)+getTLVLength(addressTLV.length()/2)+addressTLV;
    }
    
    public String prepareAlphaIDTLV(byte tag, String AlphaIdString)
    {
       return null;
    }
    
    public String prepareSubAddTLV(byte tag, String subAddress)
    {
        StringBuilder addressTLV =new StringBuilder();
        if(subAddress.length()%2==1)
        {
            subAddress = subAddress+"F";
        }
        for(int i=0; i<subAddress.length();i=i+2)
        {
            addressTLV.append(subAddress.charAt(i+1)+subAddress.charAt(i));                    
        }
        
        return convertTagToString(tag)+getTLVLength(addressTLV.length()/2)+addressTLV;
    }
    
    public String prepareCCPTLV(byte tag, String ccp)
    {
        return convertTagToString(tag)+getTLVLength(ccp.length()/2)+ccp;
    }
    
    public String prepareCommandDetailTLV(byte tag, String commandNumber, String toc, String cq)
    {
        return convertTagToString(tag)+"03"+commandNumber+toc+cq;
    }
    
    
    public String prepareDeviceIdentitesTLV(byte tag, String src, String dest)
    {
        return convertTagToString(tag)+"02"+src+dest;
    }
    
    
    public String prepareDurationTLV(String tag, String timeUnit, int durtion)
    {
        return null;
    }
    
    public String prepareItemIdTLV(byte tag, String itemId)
    {
        return convertTagToString(tag)+"01"+itemId;
    }
    
    public String prepareResultTag(String tag, String result, String additionalInfo)
    {
        return null;
    }
    
    public String prepareLocInfoTLV(String tag, String locationInfo)
    {
        return  null;
    }
    
    public String prepareIMEITLV(String tag, String IMEI)
    {
        return null;
    }
    
    public String prepareNMRTLV(String tag, String nmr)
    {
        return null;
    }
    
    public String prepareEventListTLV(byte tag, String eventList)
    {
        return null;
    }
    
    public String prepareCauseTLV(byte tag, String cause)
    {
        return null;
    }
    
    public String prepareLocationStatusTLV(byte tag, String locationStatus)
    {
        return null;
    }
   
    public String prepareTransactionIdTLV(byte tag, String transactionId)
    {
        return null;
    }
    
    public String prepareCardReaderStatusTLV(String tag, String cardReaderStatus)
    {
        return null;
    }
    
    public String prepareTimerId(byte tag, int timerId)
    {
        return null;
    }
    
    public String prepareTimerValue(String tag, int hour, int minute, int second)
    {
        return null;
    }
    
    public String prepareDataTimerTLV(String tag, String time)
    {
        return null;
    }
    
    public String prepareLanguageTLV(byte tag, String languageCode)
    {
        return null;
    }
    
    public String prepareTextStringTLV(byte tag, String dcs, String textString)
    {
        StringBuilder hexString = new StringBuilder();
        for(int i=0;i<textString.length();i++)
        {
            hexString.append(String.format("%x", (byte)textString.charAt(i)));
        }
        
        return convertTagToString(tag)+getTLVLength((textString.length()+1))+dcs+hexString.toString();        
    }
    
    public String prepareAccessTechTLV(byte tag, String accessTech)
    {
        return null;
    }
    
    public String prepareServiceRecTLV(String tag, String localBearTech, String serviceId, String serviceRecord)
    {
        return null;
    }
    
    public String prepareESNTLV(String tag, String esn)
    {
        return null;
    }
    
    public String prepareIMEISVTLV()
    {
        return null;
    }

    public String prepareHelpRequestTLV(byte HELP_REQUEST_TAG) {
        return null;
    }
    
    public String prepareBroswerTerminationCause(byte tag, String cause)
    {
        return null;
    }

    public String prepareNetworSearchModeTLV(byte tag, String networkSearchMode) 
    {
        return null;
    }
    
    public String prepareBrowsingStatusTLV(byte tag, String status)
    {
        return null;
    }
    
    private String getTLVLength(int length)
    {
        StringBuilder len = new StringBuilder();
        if(length>127)
        {
            len.append("81");
        }
        return len.append(Integer.toHexString(length).length() == 1 ?"0"+Integer.toHexString(length).toUpperCase():Integer.toHexString(length).toUpperCase()).toString();
    }
    
    private String convertTagToString(byte tag)
    {
        return Integer.toHexString(tag).length() == 1 ?"0"+Integer.toHexString(tag).toUpperCase():Integer.toHexString(tag).toUpperCase();
    }
}
