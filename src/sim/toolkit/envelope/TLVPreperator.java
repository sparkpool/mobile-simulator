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
    
    public String prepareAddTLV(String tag, String tonNpi, String dialingString)
    {
        return null;
    }
    
    public String prepareAIDTLV(String tag, String AlphaIdString)
    {
        return null;
    }
    
    public String prepareSubAddTLV(String tag, String subAddress)
    {
        return null;
    }
    
    public String prepareCCPTLV(String tag, String ccp)
    {
        return null;
    }
    
    public String prepareCommandDetailTLV(String tag, String commandNumber, String toc, String cq)
    {
        return null;
    }
    
    
    public String prepareDeviceIdentitesTLV(String tag, String src, String dest)
    {
        return null;
    }
    
    
    public String prepareDurationTLV(String tag, String timeUnit, int durtion)
    {
        return null;
    }
    
    public String prepareItemIdTLV(String tag, String itemId)
    {
        return null;
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
    
    public String prepareEventListTLV(String tag, String eventList)
    {
        return null;
    }
    
    public String prepareCauseTLV(String tag, String cause)
    {
        return null;
    }
    
    public String prepareLocationStatusTLV(String tag, String locationStatus)
    {
        return null;
    }
   
    public String prepareTransactionIdTLV(String tag, String transactionId)
    {
        return null;
    }
    
    public String prepareCardReaderStatusTLV(String tag, String cardReaderStatus)
    {
        return null;
    }
    
    public String prepareTimerId(String tag, String timerId)
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
    
    public String prepareLanguageTLV(String tag, String languageCode)
    {
        return null;
    }
    
    public String prepareTextStringTLV(String tag, String dcs, String textString)
    {
        return null;
    }
    
    public String prepareAccessTechTLV(String tag, String accessTech)
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
}
