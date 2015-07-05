/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.envelope;

import java.util.ArrayList;
import java.util.List;
import sim.toolkit.tlvparser.TLVConstants;

/**
 *
 * @author user
 */
public class EnvelopePreparator implements TLVConstants{
    
    private static EnvelopePreparator instance;
    
    private ArrayList<String> envelopeTLVList = new ArrayList<>();
            
    private EnvelopePreparator(){}
    
    public static EnvelopePreparator getInstance()
    {
        if(instance == null )
        {
            instance = new EnvelopePreparator();
        }
        return instance;
    }
    
    /**
     * This method used to prepare the menu selection envelope based on the provided parameters in the argument
     * @param itemId: Id of selected item
     * @param isHelpRequired: Tell whether help information is required or not
     * @return : Return the complete envelope
     */
    public String prepareEnvMenuSelection(String itemId, boolean isHelpRequired)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_KEYPAD, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareItemIdTLV(ITEM_IDENTIFIER_TAG, itemId));
        if(isHelpRequired)
        {
            envelope.append(tlvPrep.prepareHelpRequestTLV(HELP_REQUEST_TAG));
        }
        int len = envelope.length()/2;
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_MENUSELECTION+temp+envelope;
    }
    
    /**
     * This Method used to prepare the event call control Envelope
     * @param addressTLV: It contain the Address or SS string or USSD string or PDP context activation parameters or EPS PDN
     * connection activation parameters or IMS URI
     * @param subAddressTLV: this contains the subaddress TLV
     * @param ccpTLV1 : This contains the Capability configuration parameter 1 TLV
     * @param ccpTLV2: This contains the Capability configuration parameter 2 TLV
     * @param lociInfoTLV: This contain the location info TLV  
     * @param bcRepeaterTLv: This contains the BC Repeater TLV 
     * @return 
     */
    public String prepareEnvCallControl(String addressTLV, String subAddressTLV,String ccpTLV1, String ccpTLV2, String lociInfoTLV, String bcRepeaterTLv)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(addressTLV);
        
        if(ccpTLV1 != null)
        {
            envelope.append(ccpTLV1);
        }
        if(subAddressTLV !=null)
        {
            envelope.append(subAddressTLV);
        }
        
        envelope.append(lociInfoTLV);
        
        if(ccpTLV2 != null)
        {
            envelope.append(ccpTLV2);
        }
        
        if(bcRepeaterTLv != null)
        {
            envelope.append(bcRepeaterTLv);
        }
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_CALLCONTROL+temp+envelope;
    }
    
    /**
     * This method used to prepare Timer Expiration envelope based on the provided parameters 
     * @param timerId: This item tell the timer id of the expired timer
     * @param timerValueTLV: Timer Value TLV of the currently expired timer
     * @return : Return the complete Timer Expiration TLV
     */
    public String prepareEnvTimerExp(int timerId, String timerValueTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareTimerId(TIMER_IDENTIFIER_TAG, timerId));
        envelope.append(timerValueTLV);
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_TIMER_EXPIRATION + temp + envelope;
    }
    
    /**
     * This method used to prepare the MT Call Envelope based on the provided parameter 
     * @param transactionId: Transaction id of call
     * @param addressTLV: Address TLV 
     * @param subAddressTLV: Sub Address TLV id available otherwise null will be supplied
     * @return : Return the complete MT Envelope
     */
    public String prepareMTEnvelope(String transactionId, String addressTLV, String subAddressTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_MT_CALL));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_NETWORK, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareTransactionIdTLV(TRANSACTION_IDENTIFIER_TAG, transactionId));
        envelope.append(addressTLV);
        envelope.append(subAddressTLV); 
         int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    public String prepareEnvCallConnected(String transactionId, String connectionSrc)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_CALL_CONNECTED));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, connectionSrc, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareTransactionIdTLV(TRANSACTION_IDENTIFIER_TAG, transactionId));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvCallDisconnected(String transactionId, String cause, String disconnectionSrc)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_CALL_DISCONNECTED));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, disconnectionSrc, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareTransactionIdTLV(TRANSACTION_IDENTIFIER_TAG, transactionId));
        envelope.append(tlvPrep.prepareCauseTLV(CAUSE_TAG, cause));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvLocationStatus(String locationStatus, String locationInfoTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_LOCATION_STATUS));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareLocationStatusTLV(LOCATION_STATUS_TAG, locationStatus));
        envelope.append(locationInfoTLV);
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvUserActivity()
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_USER_ACTIVITY));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvIdleScreen(String cardReaderStatus)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_IDLE_SCREEN_AVAILABLE));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_DISPLAY, DEV_ID_UICC));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvLanguageSelection(String language)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_LANG_SELECTION));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareLanguageTLV(LANGUAGE_TAG, language));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String PrepareEnvBrowserTermination(String browserTerminationCause)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_BROWSER_TERMINATION));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareBroswerTerminationCause(BROWSER_TERMINATION_CAUSE_TAG, browserTerminationCause));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String PrepareEnvDataAvailable(String channelDataLengthTLV, String channelStatusTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_DATA_AVAILABLE));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(channelStatusTLV);
        envelope.append(channelDataLengthTLV);
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvChannelStatus(String channelStatusTLV, String bearerDescTLV, String otherAddressTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_CHANNEL_STATUS));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(channelStatusTLV);
        if(bearerDescTLV!=null)
        {
            envelope.append(bearerDescTLV);
        }
        if(otherAddressTLV!=null)
        {
            envelope.append(otherAddressTLV);
        }
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvAccessTechChange(String accessTech)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_ACCESS_TECHNOLOGY_CHANGE_SINGLE));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareAccessTechTLV(ACCESS_TECH_TAG, accessTech));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvDisplayParaChange(String displayParaTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_DISPLAY_PARAMETER_CHANGE));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(displayParaTLV);
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    
    public String prepareEnvLocalConnection(String serviceRecordTLV, String remoteEntityAddTLV, String uiccTransportLevelTLV, String remoteEntityTransportTLV )
    {
        return null;
    }
    
    public String prepareEnvNetworkSearchModeChanged(String networkSearchMode)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_NETWORK_SERACH_MODE_CHANGE));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareNetworSearchModeTLV(NETWORK_SEARCH_MODE_TAG, networkSearchMode));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvBrowsingStatus(String browsingStatus)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_BROWSING_STATUS));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(tlvPrep.prepareBrowsingStatusTLV(BROWSING_STATUS_TAG, browsingStatus));
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvFrameInformationCahnged(String frameInfoTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_FRAME_INFO_CHANGE));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(frameInfoTLV);
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public String prepareEnvHCIConnectivity()
    {
        return null;
    }
    
    public String prepareEnvPollInterval(String durationTLV)
    {
        TLVPreperator tlvPrep = TLVPreperator.getInstance();
        StringBuilder envelope = new StringBuilder(tlvPrep.prepareEventListTLV(EVENT_LIST_TAG, EVENT_POLL_INTERVAL));
        envelope.append(tlvPrep.prepareDeviceIdentitesTLV(DEVICE_IDENTITY_TAG, DEV_ID_TERMINAL, DEV_ID_UICC));
        envelope.append(durationTLV);
        int len = envelope.length();
        String temp = Integer.toHexString(len).length()== 1? 0+Integer.toHexString(len).toUpperCase():Integer.toHexString(len).toUpperCase();
        return BER_TAG_EVENT_DOWNLOAD + temp + envelope;
    }
    
    public List<String> getEnvTLVList()
    {
        return envelopeTLVList;
    }
}
