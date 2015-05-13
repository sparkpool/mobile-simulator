/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.tlvparser;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TLVParser {
   
	/**
     * Singleton instance of the TLVParser class
     */
   private static TLVParser tlvParserInstance;
   
   /**
    * Private constructor of the TLVParser Class
    */
   private TLVParser(){}
   
   /**
    * This method used to get the singleton instance of the TLVParser class 
    * @return 
    */
   public static TLVParser getTLVParserInstance()
   {
       if(tlvParserInstance == null)
       {
           tlvParserInstance = new TLVParser();
       }
       return tlvParserInstance;
   }
   
   
    public String getTLVName(String tlv) {
        return "Default TLV";
    }
   /**
    * This method will used to parse the any TLV which which will be provided in the parameter of the method
    * It will store each and every part of TLV in the List and in the same order as defined in the standard
    * @param tlv: TLV(<Tag><Length><Value>)
    * @return : Return the parsed TLV in the list
    */
   public ArrayList<String> parseTLV(String tlv)
   { 
       byte tag = (byte)Integer.parseInt(tlv.substring(0, 2), 16);
       ArrayList<String> parsedTLV = null;
       switch(tag)
       {
           case TLVConstants.COMMAND_DETAILS_TAG:
           case (byte)(TLVConstants.COMMAND_DETAILS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCommandDetails(tlv);
               break;
           case TLVConstants.DEVICE_IDENTITY_TAG:
           case (byte)(TLVConstants.DEVICE_IDENTITY_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseDeviceIdentity(tlv);
               break;
           case TLVConstants.RESULT_TAG:
           case (byte)(TLVConstants.RESULT_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseResult(tlv);
               break;
           case TLVConstants.DURATION_TAG:
           case (byte)(TLVConstants.DURATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseDuration(tlv);
               break;
           case TLVConstants.ALPHA_IDENTIFIER_TAG:
           case (byte)(TLVConstants.ALPHA_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAlphaIdentifier(tlv);
               break;
           case TLVConstants.ADDRESS_TAG:
           case (byte)(TLVConstants.ADDRESS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAddress(tlv);
               break;
               
           case TLVConstants.CAPABILITY_CONFIGURATION_PARAMETERS_TAG:
           case (byte)(TLVConstants.CAPABILITY_CONFIGURATION_PARAMETERS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCapConfParam(tlv);
               break;
           case TLVConstants.SUBADDRESS_TAG:
           case (byte)(TLVConstants.SUBADDRESS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseSubAddress(tlv);
               break;
           case TLVConstants.SS_STRING_TAG:
           case (byte)(TLVConstants.SS_STRING_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseSS_String(tlv);
               break;
           case TLVConstants.USSD_STRING_TAG:
           case (byte)(TLVConstants.USSD_STRING_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseUSSD_String(tlv);
               break;
           case TLVConstants.SMS_TPDU_TAG:
           case (byte)(TLVConstants.SMS_TPDU_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseSMS_TPDU_String(tlv);
               break;
           case TLVConstants.CELL_BROADCAST_PAGE_TAG:
           case (byte)(TLVConstants.CELL_BROADCAST_PAGE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCellBroadCastPage(tlv);
               break;
           case TLVConstants.TEXT_STRING_TAG:
           case (byte)(TLVConstants.TEXT_STRING_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseTextString(tlv);
               break;
           case TLVConstants.TONE_TAG:
           case (byte)(TLVConstants.TONE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseTone(tlv);
               break;
           case TLVConstants.ITEM_TAG:
           case (byte)(TLVConstants.ITEM_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseItem(tlv);
               break;
           case TLVConstants.ITEM_IDENTIFIER_TAG:
           case (byte)(TLVConstants.ITEM_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseItemIdentifier(tlv);
               break;
           case TLVConstants.RESPONSE_LENGTH_TAG:
           case (byte)(TLVConstants.RESPONSE_LENGTH_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseResponseLength(tlv);
               break;
               
           case TLVConstants.FILE_LIST_TAG:
           case (byte)(TLVConstants.FILE_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseFileList(tlv);
               break;
           case TLVConstants.LOCATION_INFORMATION_TAG:
           case (byte)(TLVConstants.LOCATION_INFORMATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseLocationInformation(tlv);
               break;
           case TLVConstants.IMEI_TAG:
           case (byte)(TLVConstants.IMEI_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseIMEI(tlv);
               break;
           case TLVConstants.HELP_REQUEST_TAG:
           case (byte)(TLVConstants.HELP_REQUEST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parsehelpRequest(tlv);
               break;
           case TLVConstants.NETWORK_MEASUREMENT_RESULTS_TAG:
           case (byte)(TLVConstants.NETWORK_MEASUREMENT_RESULTS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseNetworkMeasurementResult(tlv);
               break;
           case TLVConstants.DEFAULT_TEXT_TAG:
           case (byte)(TLVConstants.DEFAULT_TEXT_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseDefaultText(tlv);
               break;
           case TLVConstants.ITEMS_NEXT_ACTION_INDICATOR_TAG:
               parsedTLV= parseItemNextActionIndicator(tlv);
               break;
           case TLVConstants.EVENT_LIST_TAG:
           case (byte)(TLVConstants.EVENT_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseEventList(tlv);
               break;
           case TLVConstants.CAUSE_TAG:
           case (byte)(TLVConstants.CAUSE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCause(tlv);
               break;
           case TLVConstants.LOCATION_STATUS_TAG:
           case (byte)(TLVConstants.LOCATION_STATUS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseLocationStatus(tlv);
               break;
           case TLVConstants.TRANSACTION_IDENTIFIER_TAG:
           case (byte)(TLVConstants.TRANSACTION_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseTransactionIdentifier(tlv);
               break;
           case TLVConstants.BCCH_CHANNEL_LIST_TAG:
           case (byte)(TLVConstants.BCCH_CHANNEL_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBcchChannelList(tlv);
               break;
           case TLVConstants.ICON_IDENTIFIER_TAG:
           case (byte)(TLVConstants.ICON_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseIconIdentifier(tlv);
               break;
           case TLVConstants.ITEM_ICON_IDENTIFIER_LIST_TAG:
           case (byte)(TLVConstants.ITEM_ICON_IDENTIFIER_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseItemIconIdentifier(tlv);
               break;
           case TLVConstants.CARD_READER_STATUS_TAG:
           case (byte)(TLVConstants.CARD_READER_STATUS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCardReaderStatus(tlv);
               break;
           case TLVConstants.CARD_ATR_TAG:
           case (byte)(TLVConstants.CARD_ATR_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCardATR(tlv);
               break;
           case TLVConstants.C_APDU_TAG:
           case (byte)(TLVConstants.C_APDU_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseC_APDU(tlv);
               break;
           case TLVConstants.R_APDU_TAG:
           case (byte)(TLVConstants.R_APDU_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseR_APDU(tlv);
               break;
           case TLVConstants.TIMER_IDENTIFIER_TAG:
           case (byte)(TLVConstants.TIMER_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseTimerIdentifier(tlv);
               break;
           case TLVConstants.TIMER_VALUE_TAG:
           case (byte)(TLVConstants.TIMER_VALUE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= timerValue(tlv);
               break;
           case TLVConstants.DATE_TIME_AND_TIME_ZONE_TAG:
           case (byte)(TLVConstants.DATE_TIME_AND_TIME_ZONE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= dateTimeZone(tlv);
               break;
           
               
               
               
               
               
               
               
           case TLVConstants.CALL_CONTROL_REQUESTED_ACTION_TAG:
           case (byte)(TLVConstants.CALL_CONTROL_REQUESTED_ACTION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCallControlReqAction(tlv);
               break;
           case TLVConstants.AT_COMMAND_TAG:
           case (byte)(TLVConstants.AT_COMMAND_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAT_Command(tlv);
               break;
           case TLVConstants.AT_RESPONSE_TAG:
           case (byte)(TLVConstants.AT_RESPONSE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAT_Response(tlv);
               break;
           case TLVConstants.BC_REPEAT_INDICATOR_TAG:
           case (byte)(TLVConstants.BC_REPEAT_INDICATOR_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBC_RepeatIndicator(tlv);
               break;
           case TLVConstants.IMMEDIATE_RESPONSE_TAG:
           case (byte)(TLVConstants.IMMEDIATE_RESPONSE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseImmediateResponse(tlv);
               break;
           case TLVConstants.DTMF_STRING_TAG:
           case (byte)(TLVConstants.DTMF_STRING_TAG|TLVConstants.TAG_SET_CR):
             parsedTLV= parseDTMFString(tlv);
               break;
               
           case TLVConstants.LANGUAGE_TAG:
           case (byte)(TLVConstants.LANGUAGE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseLanguage(tlv);
               break;
           case TLVConstants.TIMING_ADVANCE_TAG:
           case (byte)(TLVConstants.TIMING_ADVANCE_TAG|TLVConstants.TAG_SET_CR):
              parsedTLV= parseTimingAdvance(tlv);
               break;
           case TLVConstants.AID_TAG:
           case (byte)(TLVConstants.AID_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAID(tlv);
               break;
           case TLVConstants.BROWSER_IDENTITY_TAG:
           case (byte)(TLVConstants.BROWSER_IDENTITY_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBrowseIdentity(tlv);
               break;
           case TLVConstants.URL_TAG:
           case (byte)(TLVConstants.URL_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseURL(tlv);
               break;
           case TLVConstants.BEARER_TAG:
           case (byte)(TLVConstants.BEARER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBearer(tlv);
               break;
           case TLVConstants.PROVISIONING_REFERENCE_FILE_TAG:
           case (byte)(TLVConstants.PROVISIONING_REFERENCE_FILE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseProvisioningRefeFile(tlv);
               break;
           case TLVConstants.BROWSER_TERMINATION_CAUSE_TAG:
           case (byte)(TLVConstants.BROWSER_TERMINATION_CAUSE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBrowserTerminationCause(tlv);
               break;
           case TLVConstants.BEARER_DESCRIPTION_TAG:
           case (byte)(TLVConstants.BEARER_DESCRIPTION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBearerDesc(tlv);
               break;
           case TLVConstants.CHANNEL_DATA_TAG:
           case (byte)(TLVConstants.CHANNEL_DATA_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseChannelData(tlv);
               break;
           case TLVConstants.CHANNEL_DATA_LENGTH_TAG:
           case (byte)(TLVConstants.CHANNEL_DATA_LENGTH_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseChannelDataLength(tlv);
               break;
           case TLVConstants.CHANNEL_STATUS_TAG:
           case (byte)(TLVConstants.CHANNEL_STATUS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseChannelStatus(tlv);
               break;
           case TLVConstants.BUFFER_SIZE_TAG:
           case (byte)(TLVConstants.BUFFER_SIZE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBuffSize(tlv);
               break;
           case TLVConstants.CARD_READER_IDENTIFIER_TAG:
           case (byte)(TLVConstants.CARD_READER_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCardreaderIndentifier(tlv);
               break;
           //case TLVConstants.FILE_UPDATE_INFORMATION_TAG:
           //case (byte)(TLVConstants.FILE_UPDATE_INFORMATION_TAG|TLVConstants.TAG_SET_CR):
             //  parsedTLV= parseFileUpdateInfo(tlv);
               //break;
           case TLVConstants.UICC_TERMINAL_INTERFACE_TRANSPORT_LEVEL_TAG:
           case (byte)(TLVConstants.UICC_TERMINAL_INTERFACE_TRANSPORT_LEVEL_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseUICC_TerminalIntTranslevel(tlv);
               break;
           case TLVConstants.NOT_USED:
           case (byte)(TLVConstants.NOT_USED|TLVConstants.TAG_SET_CR):
               parsedTLV= parseNotUsed(tlv);
               break;
           case TLVConstants.OTHER_ADDRESS_TAG:
           case (byte)(TLVConstants.OTHER_ADDRESS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseOtherAdd(tlv);
               break;
           case TLVConstants.ACCESS_TECHNOLOGY_TAG:
           case (byte)(TLVConstants.ACCESS_TECHNOLOGY_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAccessTech(tlv);
               break;
           case TLVConstants.DISPLAY_PARAMETERS_TAG:
           case (byte)(TLVConstants.DISPLAY_PARAMETERS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseDisplayParam(tlv);
               break;
           case TLVConstants.SERVICE_RECORD_TAG:
           case (byte)(TLVConstants.SERVICE_RECORD_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseServiceRecord(tlv);
               break;
           case TLVConstants.DEVICE_FILTER_TAG:
           case (byte)(TLVConstants.DEVICE_FILTER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseDeviceFilter(tlv);
               break;
           case TLVConstants.SERVICE_SEARCH_TAG:
           case (byte)(TLVConstants.SERVICE_SEARCH_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseServiceSearch(tlv);
               break;
           case TLVConstants.ATTRIBUTE_INFORMATION_TAG:
           case (byte)(TLVConstants.ATTRIBUTE_INFORMATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseAttributeInfo(tlv);
               break;
           case TLVConstants.SERVICE_AVAILABILITY_TAG:
           case (byte)(TLVConstants.SERVICE_AVAILABILITY_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseServiceAvailability(tlv);
               break;
           case TLVConstants.ESN_TAG:
           case (byte)(TLVConstants.ESN_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseESN(tlv);
               break;
           case TLVConstants.NETWORK_ACCESS_NAME_TAG:
           case (byte)(TLVConstants.NETWORK_ACCESS_NAME_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseNetAccessName(tlv);
               break;
           case TLVConstants.CDMA_SMS_TPDU_TAG:
           case (byte)(TLVConstants.CDMA_SMS_TPDU_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseCDMA_SMS_TPDU(tlv);
               break;
           case TLVConstants.REMOTE_ENTITY_ADDRESS_TAG:
           case (byte)(TLVConstants.REMOTE_ENTITY_ADDRESS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseRemoteEntityAdd(tlv);
               break;
           case TLVConstants.I_WLAN_IDENTIFIER_TAG:
           case (byte)(TLVConstants.I_WLAN_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseI_WLAN_Identfier(tlv);
               break;
           case TLVConstants.I_WLAN_ACCESS_STATUS_TAG:
           case (byte)(TLVConstants.I_WLAN_ACCESS_STATUS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseI_WLAN_AccessStatus(tlv);
               break;
           case TLVConstants.TEXT_ATTRIBUTE_TAG:
           case (byte)(TLVConstants.TEXT_ATTRIBUTE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseTextAttribute(tlv);
               break;
           
               
           case TLVConstants.ITEM_TEXT_ATTRIBUTE_LIST_TAG:
           case (byte)(TLVConstants.ITEM_TEXT_ATTRIBUTE_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseItemTextAttributeList(tlv);
               break;
           case TLVConstants.PDP_CONTEXT_ACTIVATION_PARAMETER_TAG:
           case (byte)(TLVConstants.PDP_CONTEXT_ACTIVATION_PARAMETER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parsePDP_ContextActParam(tlv);
               break;
           case TLVConstants.IMEISV_TAG:
           case (byte)(TLVConstants.IMEISV_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseIMEISV(tlv);
               break;
           case TLVConstants.BATTERY_STATE_TAG:
           case (byte)(TLVConstants.BATTERY_STATE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBatteryState(tlv);
               break;
           case TLVConstants.BROWSING_STATUS_TAG:
           case (byte)(TLVConstants.BROWSING_STATUS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBrowsingStatus(tlv);
               break;
           case TLVConstants.NETWORK_SEARCH_MODE_TAG:
           case (byte)(TLVConstants.NETWORK_SEARCH_MODE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseNetSearchMode(tlv);
               break;
               
           case TLVConstants.FRAME_LAYOUT_TAG:
           case (byte)(TLVConstants.FRAME_LAYOUT_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseFrameLayout(tlv);
               break;
           case TLVConstants.FRAMES_INFORMATION_TAG:
           case (byte)(TLVConstants.FRAMES_INFORMATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseFramesInfo(tlv);
               break;
           case TLVConstants.FRAME_IDENTIFIER_TAG:
           case (byte)(TLVConstants.FRAME_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseFrameIdentifier(tlv);
               break;
           case TLVConstants.UTRAN_MEASUREMENT_QUALIFIER_TAG:
           case (byte)(TLVConstants.UTRAN_MEASUREMENT_QUALIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseUTRANMeasQual(tlv);
               break;
           case TLVConstants.MULTIMEDIA_MESSAGE_REFERENCE_TAG:
           case (byte)(TLVConstants.MULTIMEDIA_MESSAGE_REFERENCE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseMultiMediaMessReference(tlv);
               break;
           case TLVConstants.MULTIMEDIA_MESSAGE_IDENTIFIER_TAG:
           case (byte)(TLVConstants.MULTIMEDIA_MESSAGE_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseMultiMediaMessIdentifier(tlv);
               break;
           case TLVConstants.MULTIMEDIA_MESSAGE_TRANSFER_STATUS_TAG:
           case (byte)(TLVConstants.MULTIMEDIA_MESSAGE_TRANSFER_STATUS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseMultiMediaMessTransStatus(tlv);
               break;
           case TLVConstants.MEID_TAG:
           case (byte)(TLVConstants.MEID_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseMEID(tlv);
               break;
           case TLVConstants.MULTIMEDIA_MESSAGE_CONTENT_IDENTIFIER_TAG:
           case (byte)(TLVConstants.MULTIMEDIA_MESSAGE_CONTENT_IDENTIFIER_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseMultiMediaMessContIdentifier(tlv);
               break;
           case TLVConstants.MULTIMEDIA_MESSAGE_NOTIFICATION_TAG:
           case (byte)(TLVConstants.MULTIMEDIA_MESSAGE_NOTIFICATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseMultiMediaMessNoti(tlv);
               break;
           case TLVConstants.LAST_ENVELOPE_TAG:
           case (byte)(TLVConstants.LAST_ENVELOPE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseLastEnvelope(tlv);
               break;
           case TLVConstants.REGISTRY_APPLICATION_DATA_TAG:
           case (byte)(TLVConstants.REGISTRY_APPLICATION_DATA_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseRegisryAppData(tlv);
               break;
           case TLVConstants.PLMNWACT_LIST_TAG:
           case (byte)(TLVConstants.PLMNWACT_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parsePLMNWACTList(tlv);
               break;
           case TLVConstants.ROUTING_AREA_INFORMATION_TAG:
           case (byte)(TLVConstants.ROUTING_AREA_INFORMATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseRoutingAreaInfo(tlv);
               break;
           case TLVConstants.UPDATE_ATTACH_TYPE_TAG:
           case (byte)(TLVConstants.UPDATE_ATTACH_TYPE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseUpdateAttachType(tlv);
               break;
           case TLVConstants.REJECTION_CAUSE_CODE_TAG:
           case (byte)(TLVConstants.REJECTION_CAUSE_CODE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseRejectionCauseCode(tlv);
               break;
           case TLVConstants.GEOGRAPHICAL_LOCATION_PARAMETERS_TAG:
           case (byte)(TLVConstants.GEOGRAPHICAL_LOCATION_PARAMETERS_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseGeographicalLocParam(tlv);
               break;
           case TLVConstants.GAD_SHAPES_TAG:
           case (byte)(TLVConstants.GAD_SHAPES_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseGADShapes(tlv);
               break;
           case TLVConstants.NMEA_SENTENCE_TAG:
           case (byte)(TLVConstants.NMEA_SENTENCE_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseNMEASentence(tlv);
               break;
           case TLVConstants.PLMN_LIST_TAG:
           case (byte)(TLVConstants.PLMN_LIST_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parsePLMNList(tlv);
               break;
           case TLVConstants.BROADCAST_NETWORK_INFORMATION_TAG:
           case (byte)(TLVConstants.BROADCAST_NETWORK_INFORMATION_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseBroadcastNetInfo(tlv);
               break;
           case TLVConstants.ACTIVATE_DESCRIPTOR_TAG:
           case (byte)(TLVConstants.ACTIVATE_DESCRIPTOR_TAG|TLVConstants.TAG_SET_CR):
               parsedTLV= parseActiveDescriptor(tlv);
               break;
              
            
               
       }
       return parsedTLV;
   }
   
   
   
   
 private ArrayList<String> parseTextString(String tlv) {
		ArrayList<String> parsedTLV = new ArrayList<String>();
	    
	    //Append TAG Value
	    parsedTLV.add("Text String TAG  :  "+tlv.substring(0,2));
	    int length;
	    int sublength = 2;
	    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
	    sublength+=2;
	    if(data[0] == 0x81){
	    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
	    	sublength+=2;
	    }else{
	    	length = (int)data[0];
	    }
	    
	     
	    //Append Length
	    parsedTLV.add("Length : "+ length);
	       
	    
	    //check if Alpha Id length is more than 0
	    if(length>0)
	    { 
	    	parsedTLV.add("Data coding scheme " + tlv.substring(sublength,sublength+2));
	    	sublength+=2;
	    	parsedTLV.add("Text string " + tlv.substring(sublength));
	    }
		return parsedTLV;
}

private ArrayList<String> parseCellBroadCastPage(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Cell Broadcast Page  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    		parsedTLV.add("Cell Broadcast Page: "+tlv.substring(4));  
    }
    
    return parsedTLV;
}

private ArrayList<String> parseActiveDescriptor(String tlv) {   
	   //not required by 3gpp
	return null;
}

private ArrayList<String> parseBroadcastNetInfo(String tlv) {
	 //not required by 3gpp
	return null;
}

private ArrayList<String> parsePLMNList(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("PLMN_LIST_TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("PLMN LIST Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	int j =4;
    	for(int i =0 ; i< length/3 ; i++){
    		parsedTLV.add(i+1 +" PLMN Identifier: "+Integer.parseInt(tlv.substring(j,j+6),16));
    		j=j+6;
    	}  
    }
    
    return parsedTLV;
	
}



private ArrayList<String> parseNMEASentence(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("NMEA_SENTENCE_TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("NMEA SENTENCE Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    		parsedTLV.add("NMEA SENTENCE: "+TLVConstants.convertFromHexToAscii(tlv.substring(4)));  
    }
    
    return parsedTLV;
	
}

private ArrayList<String> parseGADShapes(String tlv) {
	
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("GAD SHAPES TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("GAD SHAPES Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	int lengthOfGadShape = Integer.parseInt(tlv.substring(4,6),16);
    	parsedTLV.add("Length of GAD Shape: "+ lengthOfGadShape);
    	parsedTLV.add("GAD Shape: "+ tlv.substring(6,6+(2*lengthOfGadShape)));
    	if(tlv.length()>(6+(2*lengthOfGadShape))){
    		int lenofVelocity = Integer.parseInt(tlv.substring(6+(2*lengthOfGadShape),6+(2*lengthOfGadShape)+2),16);
    		parsedTLV.add("Length of Velocity: "+ lenofVelocity);
    		parsedTLV.add("Velocity: " + tlv.substring(6+(2*lengthOfGadShape)+2));
    	}
    }
    
    return parsedTLV;
	
}
//TODO
private ArrayList<String> parseGeographicalLocParam(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("GEOGRAPHICAL LOCATION PARAMETERS TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("GEOGRAPHICAL LOCATION PARAMETERS Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	if("81".equals(tlv.substring(4,6))) {
    		parsedTLV.add("Horizontal accuracy: Horizontal accuracy not specified");
    	}else {
    		parsedTLV.add("Horizontal accuracy: "+ Integer.parseInt(tlv.substring(4,6),16));
    	}
    	parsedTLV.add("Vertical coordinates: "+ Integer.parseInt(tlv.substring(6,8)));
    	parsedTLV.add("Velocity: "+ Integer.parseInt(tlv.substring(8,10)));
    	parsedTLV.add("Preffered GAD Shape: "+ Integer.parseInt(tlv.substring(10,12)));
    }
    
    return parsedTLV;
	
}

private ArrayList<String> parseRejectionCauseCode(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("REJECTION CAUSE CODE TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("REJECTION CAUSE CODE Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	parsedTLV.add("REJECTION CAUSE CODE: "+ Integer.parseInt(tlv.substring(4),16));
    }
    
    return parsedTLV;
	
}

private ArrayList<String> parseUpdateAttachType(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("UPDATE ATTACH TYPE TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("UPDATE ATTACH TYPE Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	parsedTLV.add(parseAttachTypeValue(tlv.substring(4)));
    }
    
    return parsedTLV;
	
}

private String parseAttachTypeValue(String substring) {
	byte byteaar[] = TLVConstants.hexStringToByteArray(substring);
	String str = "Update/Attach Type: ";
	switch(byteaar[0]){
	case (byte)0:
		str = str + "Normal Location Updating";
		break;
	case (byte)1:
		str = str + "Periodic Updating";
		break;
	case (byte)2:
		str = str + "IMSI Attach";
		break;
	case (byte)3:
		str = str + "GPRS Attach";
		break;
	case (byte)4:
		str = str + "Combined GPRS/IMSI Attach";
		break;
	case (byte)5:
		str = str + "RA Updating";
		break;
	case (byte)6:
		str = str + "Combined RA/LA Updating";
		break;
	case (byte)7:
		str = str + "Combined RA/LA Updating with IMSI Attach";
		break;
	case (byte)8:
		str = str + "Periodic Updating";
		break;
	case (byte)9:
		str = str + "EPS Attach";
		break;
	case (byte)10:
		str = str + "Combined EPS/IMSI Attach";
		break;
	case (byte)11:
		str = str + "TA updating";
		break;
	case (byte)12:
		str = str + "Combined TA/LA updating";
		break;
	case (byte)13:
		str = str + "Combined TA/LA updating with IMSI attach";
		break;
	case (byte)14:
		str = str + "Periodic updating";
		break;
	}
	return str;
}

private ArrayList<String> parseRoutingAreaInfo(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("ROUTING AREA INFORMATION TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("ROUTING AREA INFORMATION Length : "+ length);
    
    
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	parsedTLV.add("MCC & MNC " + tlv.substring(4,7));
    	parsedTLV.add("LAC " + tlv.substring(7,9));
    	parsedTLV.add("RAC " + tlv.substring(9));
    }
    
    return parsedTLV;
	
}

private ArrayList<String> parsePLMNWACTList(String tlv) {
	
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("PLMNwAcT LIST TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("PLMNwAcT LIST Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	int temp = 4;
    	int index = length/5;
    	for(int i =0 ; i<index;i++,temp=temp+5){
    		parsedTLV.add("PLMN Identifier " + tlv.substring(temp,temp+3));
        	parsedTLV.add("PLMN Access Technology " + tlv.substring(temp+3,temp+5));
    	}
    }
	return parsedTLV;
}

private ArrayList<String> parseRegisryAppData(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Registry Application Data TAG  :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	parsedTLV.add("Application port number " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Data coding scheme " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Registry content " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseLastEnvelope(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("LAST ENVELOPE TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("LAST ENVELOPE Length : "+ length);
	return parsedTLV;
}

private ArrayList<String> parseMultiMediaMessNoti(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Multimedia Message Notification TAG  :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("MMS notification message " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseMultiMediaMessContIdentifier(String tlv) {
	
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("MM CONTENT IDENTIFIER TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("MM CONTENT IDENTIFIER Length : "+ length);
    
    if(length>0){
    	
    	parsedTLV.add("MM CONTENT DATA OBJECT TAG  :  "+tlv.substring(4));
    }
    
    
	return parsedTLV;
}



private ArrayList<String> parseMEID(String tlv) {
	
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("MEID TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){
    	
    	parsedTLV.add("MEID OF TERMINAL  :  "+tlv.substring(4,11));
    	parsedTLV.add("RFU  :  "+tlv.substring(11));
    }
	return parsedTLV;
}

private ArrayList<String> parseMultiMediaMessTransStatus(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Multimedia Message Transfer Status TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Multimedia Message Transfer Status  :  "+tlv.substring(4));
    }
	return parsedTLV;
}

private ArrayList<String> parseMultiMediaMessIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Multimedia Message Identifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Multimedia Message Identifier  :  "+tlv.substring(4));
    }
	return parsedTLV;
}

private ArrayList<String> parseMultiMediaMessReference(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Multimedia Message Reference TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Multimedia Message Reference  :  "+tlv.substring(4));
    }
	return parsedTLV;
}

private ArrayList<String> parseUTRANMeasQual(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("UTRAN/E-UTRAN Measurement Qualifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("UTRAN/E-UTRAN Measurement Qualifier :  "+utranMeasQual(tlv.substring(4)));
    }
	return parsedTLV;
}

private String utranMeasQual(String substring) {
	byte data[] = TLVConstants.hexStringToByteArray(substring);
	switch(data[0]){
	case 0x01:
		return "UTRAN Intra-frequency measurements: " + substring;
	case 0x02:
		return "UTRAN Inter-frequency measurements: " + substring;
	case 0x03:
		return "UTRAN Inter-RAT (GERAN) measurements: " + substring;
	case 0x04:
		return "UTRAN Inter-RAT (E-UTRAN) measurements: " + substring;
	case 0x05:
		return "E-UTRAN Intra-frequency measurements: " + substring;
	case 0x06:
		return "E-UTRAN Inter-frequency measurements: " + substring;
	case 0x07:
		return "E-UTRAN Inter-RAT (GERAN) measurements: " + substring;
	case 0x08:
		return "E-UTRAN Inter-RAT (UTRAN) measurements: " + substring;
	default:
		return "Reserved for future use: " + substring;
			
	}
}

private ArrayList<String> parseFrameIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Frame identifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Identifier of frame  :  "+tlv.substring(4));
    }
	return parsedTLV;
}

private ArrayList<String> parseFramesInfo(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Frame Information TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Default frame id  :  "+tlv.substring(4,6));
    	parsedTLV.add("Frame Information List :  "+tlv.substring(6));
    }
	return parsedTLV;
}

private ArrayList<String> parseFrameLayout(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Frame Layout TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Layout of the frames :  "+tlv.substring(4,6));
    	parsedTLV.add("Relative-sized Frame :  "+tlv.substring(6));
    }
	return parsedTLV;
}

private ArrayList<String> parseNetSearchMode(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Network search mode Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Network search mode :  "+tlv.substring(4));
    	
    }
	return parsedTLV;
}

private ArrayList<String> parseBrowsingStatus(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Browsing status Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Browsing status :  "+tlv.substring(4));
    	
    }
	return parsedTLV;
}

private ArrayList<String> parseBatteryState(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Battery State Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Battery State :  "+getBatteryState(tlv.substring(4)));
    	
    }
	return parsedTLV;
}

private String getBatteryState(String substring) {
	byte data[] = TLVConstants.hexStringToByteArray(substring);
	switch(data[0]){
	case (byte)0x00:
		return "Battery very low";
	case (byte)0x01:
		return "Battery Low";
	case (byte)0x02:
		return "Battery Average";
	case (byte)0x03:
		return "Battery Good";
	case (byte) 0x04:
		return "Battery Full";
	case (byte) 0xFE:
		return "Status not applicable";
	case (byte) 0xFF:
		return "Status Unknown";
	default:
		return "RFU";
	}
}

private ArrayList<String> parseIMEISV(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("IMEISV Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("IMEISV of the terminal :  "+tlv.substring(4));
    	
    }
	return parsedTLV;
}

private ArrayList<String> parsePDP_ContextActParam(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("PDP context Activation parameters Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("PDP context Activation parameters :  "+tlv.substring(4));
    	
    }
	return parsedTLV;
}

private ArrayList<String> parseItemTextAttributeList(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Item Text Attribute List Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Text Attribute list :  "+tlv.substring(4));
    	
    }
	return parsedTLV;
}

private ArrayList<String> parseTextAttribute(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Text Attribute Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Text Formatting :  "+tlv.substring(4));
    	
    }
	return parsedTLV;
}

private ArrayList<String> parseI_WLAN_AccessStatus(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("I-WLAN Access Status Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Access status :  "+getI_WLANAccessStatus(tlv.substring(4)));
    	
    }
	return parsedTLV;
}

private String getI_WLANAccessStatus(String substring) {
	byte data[] = TLVConstants.hexStringToByteArray(substring);
	switch(data[0]){
	case (byte)0x00:
		return "No current I-WLAN coverage";
	case (byte)0x01:
		return "I-WLAN coverage available, no current connection";
	case (byte)0x02:
		return "I-WLAN coverage available, connection on-going";
	default:
		return "RFU";
	}
}

private ArrayList<String> parseI_WLAN_Identfier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("I-WLAN Identifier Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("WSID value :  "+tlv.substring(4));
    	
    }
    
    return parsedTLV;
}

private ArrayList<String> parseRemoteEntityAdd(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Remote Entity Address TAG  :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Coding Type " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Remote Entity address " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseCDMA_SMS_TPDU(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("CDMA SMS TPDU TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("CDMA SMS TPDU " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseNetAccessName(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Network Access Name Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Network Access Name :  "+tlv.substring(4));
    	
    }
    return parsedTLV;
}


//-------------------- All Have variable length----------------------------

private ArrayList<String> parseESN(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
	//TODO
	return parsedTLV;
}

private ArrayList<String> parseServiceAvailability(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Service Availability TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Services " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseAttributeInfo(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Item TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Local Bearer technology identifier " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Attribute Information " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseServiceSearch(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Service Search TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Local Bearer technology identifier " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Service Search " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseDeviceFilter(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Device Filter TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Local Bearer technology identifier " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Device Filter " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseServiceRecord(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Service Record TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Local Bearer technology identifier " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Service Identifier " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Service Record " + tlv.substring(sublength,sublength+2));
    	
    }
	return parsedTLV;
}
//----------------------------------------------------------------
private ArrayList<String> parseDisplayParam(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Display parameters Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Parameters list :  "+tlv.substring(4));
    	
    }
    return parsedTLV;
}

private ArrayList<String> parseAccessTech(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Access Technology Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Technology :  "+tlv.substring(4));
    	
    }
    return parsedTLV;
}

private ArrayList<String> parseOtherAdd(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Other address Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Type of address :  "+tlv.substring(4,6));
    	parsedTLV.add("Address :  "+tlv.substring(6));
    	
    }
    return parsedTLV;
}

private ArrayList<String> parseNotUsed(String tlv) {
	return null;
}

private ArrayList<String> parseUICC_TerminalIntTranslevel(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("UICC/terminal interface transport level Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Transport protocol type :  "+tlv.substring(4,6));
    	parsedTLV.add("Port number :  "+tlv.substring(6));
    	
    }
    return parsedTLV;
}



private ArrayList<String> parseCardreaderIndentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Card reader identifier Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Identifier of card reader :  "+tlv.substring(4));
    }
    return parsedTLV;
}

private ArrayList<String> parseBuffSize(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Buffer size Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Buffer size :  "+tlv.substring(4));
    }
    return parsedTLV;
}

private ArrayList<String> parseChannelStatus(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Channel status Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Channel status :  "+tlv.substring(4));
    }
    return parsedTLV;
}

private ArrayList<String> parseChannelDataLength(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Channel data length Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Channel data length :  "+tlv.substring(4));
    }
    return parsedTLV;
}

private ArrayList<String> parseChannelData(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Channel Data TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("ChannelData String " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseBearerDesc(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Bearer Description Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Bearer type :  "+typeOfBearer(tlv.substring(4,6)));
    	parsedTLV.add("Bearer parameters :  "+tlv.substring(6));
    }
    return parsedTLV;
}

private String typeOfBearer(String substring) {
	byte data[] = TLVConstants.hexStringToByteArray(substring);
	switch(data[0]){
	case (byte)0x01:
		return "reserved for GSM/3GPP";
	case (byte)0x02:
		return "reserved for GSM/3GPP";
	case (byte)0x03:
		return "default bearer for requested transport layer";
	case (byte) 0x04:
		return "local link technology independent";
	case (byte) 0x05:
		return "Bluetooth";
	case (byte) 0x06:
		return "IrDA";
	case (byte) 0x07:
		return "RS232";
	case (byte) 0x08:
		return "cdma2000 packet data service";
	case (byte) 0x09:
		return "reserved for GSM/3GPP";
	case (byte) 0x0A:
		return "reserved for 3GPP (I-WLAN)";
	case (byte) 0x0B:
		return "reserved for 3GPP (E-UTRAN/Mapped UTRAN packet service)";
	case (byte) 0x10:
		return "USB";
	default:
		return "RFU";
	}
}

private ArrayList<String> parseBrowserTerminationCause(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Browser Termination Cause Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Browser Termination Cause :  "+tlv.substring(4));	
    }
    return parsedTLV;
}

private ArrayList<String> parseProvisioningRefeFile(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Provisioning File Reference TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Path to the provisioning file " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseBearer(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Bearer TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("List of bearers in order of priority requested " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseURL(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("URL TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("URL " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseBrowseIdentity(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Browser identity Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Browser identity :  "+browserIdentity(tlv.substring(4)));	
    }
    return parsedTLV;
}

private String browserIdentity(String substring) {
	byte data[] = TLVConstants.hexStringToByteArray(substring);
	switch(data[0]){
	case 0x00:
		return "Default Browser: " + substring;
	case 0x01:
		return "WML Browser: " + substring;
	case 0x02:
		return "HTML Browser: " + substring;
	case 0x03:
		return "XHTML Browser: " + substring;
	case 0x04:
		return "CHTML Browser: " + substring;
		default:
			return "RFU:" + substring;
	}
}

private ArrayList<String> parseAID(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("AID Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("AID :  "+tlv.substring(4));	
    }
    return parsedTLV;
}

private ArrayList<String> parseTimingAdvance(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Timing Advance Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("ME Status :  "+tlv.substring(4,6));	
    	parsedTLV.add("Timing Advance :  "+tlv.substring(6));
    }
    return parsedTLV;
}

private ArrayList<String> parseLanguage(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Language Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Language :  "+tlv.substring(4));	
    }
    return parsedTLV;
}

private ArrayList<String> parseDTMFString(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("DTMF String TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("DTMF String " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseImmediateResponse(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Immediate response Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    return parsedTLV;
}

private ArrayList<String> parseBC_RepeatIndicator(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("BC repeat indicator Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("BC repeat indicator Value :  "+tlv.substring(4));	
    }
    return parsedTLV;
}

private ArrayList<String> parseAT_Response(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("AT Response TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("AT Response string " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseAT_Command(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("AT Command TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("AT Command string " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseCallControlReqAction(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Call Control Requested Action TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Call Control Requested Action " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseTone(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Tone Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Tone :  "+tlv.substring(4));	
    }
    return parsedTLV;
}

private ArrayList<String> parseItem(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Item TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
      
    if(length>0){ 
    	parsedTLV.add("Identifier of item : " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Text string of item " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseItemIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Item identifier Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Identifier of item chosen :  "+tlv.substring(4));	
    }
    return parsedTLV;
}

private ArrayList<String> parseResponseLength(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Response length Tag  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Length : "+ length);
    
    if(length>0){  	
    	parsedTLV.add("Minimum length :  "+tlv.substring(4,6));
    	parsedTLV.add("Maximum length :  "+tlv.substring(6));
    	
    }
    return parsedTLV;
}

private ArrayList<String> parseFileList(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("File List TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Number of files " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Files " + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseLocationInformation(String tlv) {

	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("LOCATION INFORMATION TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("LOCATION INFORMATION Length : "+ length);
    
    
    
    if (length >= 5){
    	parsedTLV.add("Mobile Country & Network Codes :"+tlv.substring(4, 7));
    	parsedTLV.add("Location Area Code/Tracking Area Code :"+tlv.substring(7, 9));
    }
    
    if (length >= 7){
    	parsedTLV.add("Cell Identity Value :"+tlv.substring(9, 11));
    }
    if (length >= 9){
    	parsedTLV.add("Extended Cell identity Value :"+tlv.substring(11));
    }
    
	return parsedTLV;
}

private ArrayList<String> parseIMEI(String tlv) {

	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("IMEI TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("IMEI Length : "+ length);
   	parsedTLV.add("IMEI Value :"+tlv.substring(4,12));
	return parsedTLV;
}


private ArrayList<String> parsehelpRequest(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Help Request TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Help Request Length : "+ length);
	return parsedTLV;
}

private ArrayList<String> parseNetworkMeasurementResult(String tlv) {
	//Append TAG Value

	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("NETWORK MEASUREMENT RESULTS TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("NETWORK MEASUREMENT RESULTS Length : "+ length);
   	parsedTLV.add("Network Measurement Results Value :"+tlv.substring(4,( 4+length)));
	return parsedTLV;
}

private ArrayList<String> parseDefaultText(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Default Text String TAG  :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0)
    { 
    	parsedTLV.add("Data coding scheme " + tlv.substring(sublength,sublength+2));
    	sublength+=2;
    	parsedTLV.add("Default Text string " + tlv.substring(sublength));
    }
	return parsedTLV;
	
}

private ArrayList<String> parseItemNextActionIndicator(String tlv) {
		ArrayList<String> parsedTLV = new ArrayList<String>();
	    parsedTLV.add("Items Next Action Indicator TAG  :  "+tlv.substring(0,2));
	    
	    int length = Integer.parseInt(tlv.substring(2,4),16); 
	    //Append Length
	    parsedTLV.add("Items Next Action Indicator Length : "+ length);
	   	parsedTLV.add("Items Next Action Indicator Value :"+tlv.substring(4,( 4+length)));
	
		return parsedTLV;
	
}


private ArrayList<String> parseEventList(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    
    //Append TAG Value
    parsedTLV.add("Event List TAG :  "+tlv.substring(0,2));
    int length;
    int sublength = 2;
    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    sublength+=2;
    if(data[0] == 0x81){
    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	sublength+=2;
    }else{
    	length = (int)data[0];
    }
    
     
    //Append Length
    parsedTLV.add("Length : "+ length);
       
    
    //check if Alpha Id length is more than 0
    if(length>0){ 
    	parsedTLV.add("Event List" + tlv.substring(sublength));
    }
	return parsedTLV;
}

private ArrayList<String> parseCause(String tlv) {

	//Append TAG Value

	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("CAUSE TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("CAUSE Length : "+ length);
   	parsedTLV.add("Cause Value :"+tlv.substring(4,( 4+length)));
	return parsedTLV;

}


private ArrayList<String> parseLocationStatus(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Location Status TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Location Status Length : "+ length);
   	parsedTLV.add("Location Status Value :"+locationStatusValue(tlv.substring(4,6)));

	return parsedTLV;
}

private String locationStatusValue(String substring) {
	byte data[] = TLVConstants.hexStringToByteArray(substring);
	switch(data[0]){
	case 0x01:
		return "Normal service";
	case 0x02:
		return "Limited service";
	case 0x03:
		return "No service";
	}
	return substring;
}

private ArrayList<String> parseTransactionIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Transaction Identifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Transaction Identifier Length : "+ length);
   	parsedTLV.add("Transaction Identifier Value :"+ tlv.substring(4,( 4+length)));
	
	return parsedTLV;
}


private ArrayList<String> parseBcchChannelList(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("BCCH Channel List TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("BCCH Channel List  Length : "+ length);
   	parsedTLV.add("BCCH Channel List Value :"+tlv.substring(4,( 4+length)));

	return parsedTLV;
	
}

private ArrayList<String> parseIconIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Icon Identifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Icon Identifier Length : "+ length);
   	parsedTLV.add("Icon Qualifier Value :"+tlv.substring(4,6));
	parsedTLV.add("Icon Identifier Value :"+tlv.substring(6,8));
	
	return parsedTLV;
}

private ArrayList<String> parseItemIconIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Items Icon Identifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Items Icon Identifier Length : "+ length);
   	parsedTLV.add("Icon List Qualifier Value :"+tlv.substring(4,6));
	parsedTLV.add("Icon Identifier List Value :"+tlv.substring(6,(6+ length)));

	return parsedTLV;
}

private ArrayList<String> parseCardReaderStatus(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Card Reader Status TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Card Reader Status Length : "+ length);
   	parsedTLV.add("Card Reader Status Value :"+tlv.substring(4,6));
	
	return parsedTLV;
}

private ArrayList<String> parseCardATR(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Card ATR TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Card ATR Length : "+ length);
   	parsedTLV.add("Card ATR Value :"+tlv.substring(4,(4+length)));
	
	return parsedTLV;
}

private ArrayList<String> parseC_APDU(String tlv) {
	// TODO Auto-generated method stub
	return null;
}

private ArrayList<String> parseR_APDU(String tlv) {
	// TODO Auto-generated method stub
	return null;
}

private ArrayList<String> parseTimerIdentifier(String tlv) {
	ArrayList<String> parsedTLV = new ArrayList<String>();
    parsedTLV.add("Timer Identifier TAG  :  "+tlv.substring(0,2));
    
    int length = Integer.parseInt(tlv.substring(2,4),16); 
    //Append Length
    parsedTLV.add("Timer Identifier Length : "+ length);
   	parsedTLV.add("Timer Identifier Value :"+tlv.substring(4,(4+length)));
	
	return parsedTLV;
}

/**
    * This 
    * @param tlv 
    */
    private ArrayList<String> timerValue(String tlv) 
    {
        ArrayList<String> parsedTLV = new ArrayList<String>();
        
        //Append TAG Value
        parsedTLV.add("TIMER VALUE TAG  :  "+tlv.substring(0,2));
        
        //Append Length
        parsedTLV.add("TIMER VALUE LENGTH : "+Integer.parseInt(tlv.substring(2,4),16));
        
        //check if Alpha Id length is more than 0
        if(Integer.parseInt(tlv.substring(2,4),16)>0)
        { 
            //Append Alpha Id Text
            parsedTLV.add(TLVConstants.convertFromHexToTimerVAlue(tlv.substring(4)));
        }
        
        return parsedTLV;
    }
 
   
   
   /**
    * This 
    * @param tlv 
    */
    private ArrayList<String> dateTimeZone(String tlv) 
    {
        ArrayList<String> parsedTLV = new ArrayList<String>();
        
        //Append TAG Value
        parsedTLV.add("DATE AND TIME ZONE TAG  :  "+tlv.substring(0,2));
        
        //Append Length
        parsedTLV.add("DATE AND TIME ZONE LENGTH : "+Integer.parseInt(tlv.substring(2,4),16));
        
        //check if Alpha Id length is more than 0
        if(Integer.parseInt(tlv.substring(2,4),16)>0)
        { 
            //Append Alpha Id Text
            parsedTLV.add(TLVConstants.convertFromHexToDateAndTime(tlv.substring(4)));
        }
        
        return parsedTLV;
    }
   

   /**
    * This 
    * @param tlv 
    */
    private ArrayList<String> parseAlphaIdentifier(String tlv) 
    {
        ArrayList<String> parsedTLV = new ArrayList<String>();
        
        //Append TAG Value
        parsedTLV.add("TAG_ALPHA_IDENTIFER  :  "+tlv.substring(0,2));
        
        //Append Length
        parsedTLV.add("Alpha Identifier Length : "+Integer.parseInt(tlv.substring(2,4),16));
        
        //check if Alpha Id length is more than 0
        if(Integer.parseInt(tlv.substring(2,4),16)>0)
        { 
            //Append Alpha Id Text
            parsedTLV.add("Alpha Id Text : "+TLVConstants.convertFromHexToAscii(tlv.substring(4)));
        }
        
        return parsedTLV;
    }
   
    
    /**
     * This 
     * @param tlv 
     */
     private ArrayList<String> parseCommandDetails(String tlv) 
     {
         ArrayList<String> parsedTLV = new ArrayList<String>();
         
         //Append TAG Value
         parsedTLV.add("COMMAND_DETAILS_TAG  :  "+tlv.substring(0,2));
         
         //Append Length
         parsedTLV.add("Command Details Length : "+Integer.parseInt(tlv.substring(2,4),16));
         
         
         
         //check if Alpha Id length is more than 0
         if(Integer.parseInt(tlv.substring(2,4),16)>0)
         { 
        	 parsedTLV.add("Command Number: "+Integer.parseInt(tlv.substring(4,6),16)); 
             parsedTLV.add("Type of command: "+typeOfCommand(tlv.substring(6,10)));
             //parsedTLV.add("Command Qualifier: "+Integer.parseInt(tlv.substring(8,10),16));
             //TODO
            
         }
         
         return parsedTLV;
     }
    
    
     /**
      * This 
      * @param tlv 
      */
      private ArrayList<String> parseDeviceIdentity(String tlv) 
      {
          ArrayList<String> parsedTLV = new ArrayList<String>();
          
          //Append TAG Value
          parsedTLV.add("DEVICE_IDENTITY_TAG  :  "+tlv.substring(0,2));
          
          //Append Length
          parsedTLV.add("Device Identity Length : "+Integer.parseInt(tlv.substring(2,4),16));
          
          //check if Alpha Id length is more than 0
          if(Integer.parseInt(tlv.substring(2,4),16)>0)
          { 
        	  parsedTLV.add("Source device identity: "+deviceIdentity(tlv.substring(4,6))); 
              parsedTLV.add("Destination device identity: "+deviceIdentity(tlv.substring(6,8)));            
          }
          
          return parsedTLV;
      }
     
      /**
       * This 
       * @param tlv 
       */
       private ArrayList<String> parseResult(String tlv) 
       {
           ArrayList<String> parsedTLV = new ArrayList<String>();
           
           //Append TAG Value
           parsedTLV.add("RESULT_TAG  :  "+tlv.substring(0,2));
           
           //Append Length
           parsedTLV.add("Device Identity Length : "+Integer.parseInt(tlv.substring(2,4),16));
           
           //check if Alpha Id length is more than 0
           if(Integer.parseInt(tlv.substring(2,4),16)>0)
           { 
         	  parsedTLV.add("General Result: "+ generalResult(tlv.substring(4,6))); 
               parsedTLV.add("Additional information on result: "+additionalInfo(tlv.substring(6)));            
           }
           
           return parsedTLV;
       }
      
       /**
        * This 
        * @param tlv 
        */
        private ArrayList<String> parseDuration(String tlv) 
        {
            ArrayList<String> parsedTLV = new ArrayList<String>();
            
            //Append TAG Value
            parsedTLV.add("DURATION_TAG  :  "+tlv.substring(0,2));
            
            //Append Length
            parsedTLV.add("Duration data Length : "+Integer.parseInt(tlv.substring(2,4),16));
            
            //check if Alpha Id length is more than 0
            if(Integer.parseInt(tlv.substring(2,4),16)>0)
            { 
          	  parsedTLV.add("Time unit: "+timeUnit(tlv.substring(4,6))); 
                parsedTLV.add("Time interval: "+timeInterval(tlv.substring(6)));            
            }
            
            return parsedTLV;
        }
      
        /**
         * This 
         * @param tlv 
         */
         private ArrayList<String> parseAddress(String tlv) 
         {
        		ArrayList<String> parsedTLV = new ArrayList<String>();
        	    
        	    //Append TAG Value
        	    parsedTLV.add("Address TAG :  "+tlv.substring(0,2));
        	    int length;
        	    int sublength = 2;
        	    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
        	    sublength+=2;
        	    if(data[0] == 0x81){
        	    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
        	    	sublength+=2;
        	    }else{
        	    	length = (int)data[0];
        	    }
        	    
        	     
        	    //Append Length
        	    parsedTLV.add("Length : "+ length);
        	       
        	    
        	    //check if Alpha Id length is more than 0
        	    if(length>0){ 
        	    	parsedTLV.add("TON NPI" + tlv.substring(sublength,sublength+2));
        	    	sublength+=2;
        	    	parsedTLV.add("Dialling number string " + tlv.substring(sublength));
        	    }
        		return parsedTLV;
         }
       
         /**
          * This 
          * @param tlv 
          */
          private ArrayList<String> parseCapConfParam(String tlv) 
          {
      		ArrayList<String> parsedTLV = new ArrayList<String>();
    	    
    	    //Append TAG Value
    	    parsedTLV.add("Capability Configuration Parameters TAG :  "+tlv.substring(0,2));
    	    int length;
    	    int sublength = 2;
    	    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
    	    sublength+=2;
    	    if(data[0] == 0x81){
    	    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
    	    	sublength+=2;
    	    }else{
    	    	length = (int)data[0];
    	    }
    	    
    	     
    	    //Append Length
    	    parsedTLV.add("Length : "+ length);
    	       
    	    
    	    //check if Alpha Id length is more than 0
    	    if(length>0){ 
    	    	parsedTLV.add("Capability configuration parameters" + tlv.substring(sublength));
    	    	
    	    }
    		return parsedTLV;
          }
        
          /**
           * This 
           * @param tlv 
           */
           private ArrayList<String> parseSubAddress(String tlv) 
           {
        	   ArrayList<String> parsedTLV = new ArrayList<String>();
       	    
       	    //Append TAG Value
       	    parsedTLV.add("Subaddress TAG :  "+tlv.substring(0,2));
       	    int length;
       	    int sublength = 2;
       	    byte data[] = TLVConstants.hexStringToByteArray(tlv.substring(sublength,sublength+2));
       	    sublength+=2;
       	    if(data[0] == 0x81){
       	    	length = Integer.parseInt(tlv.substring(sublength,sublength+2),16);
       	    	sublength+=2;
       	    }else{
       	    	length = (int)data[0];
       	    }
       	    
       	     
       	    //Append Length
       	    parsedTLV.add("Length : "+ length);
       	       
       	    
       	    //check if Alpha Id length is more than 0
       	    if(length>0){ 
       	    	parsedTLV.add("Subaddress " + tlv.substring(sublength));
       	    	
       	    }
       		return parsedTLV;
           }
         
           /**
            * This 
            * @param tlv 
            */
            private ArrayList<String> parseSS_String(String tlv) 
            {
                ArrayList<String> parsedTLV = new ArrayList<String>();
                
                //Append TAG Value
                parsedTLV.add("SS_STRING_TAG  :  "+tlv.substring(0,2));
                
                //Append Length
                parsedTLV.add("SS_String Length : "+Integer.parseInt(tlv.substring(2,4),16));
                
                //check if Alpha Id length is more than 0
                if(Integer.parseInt(tlv.substring(2,4),16)>0)
                { 
              	  parsedTLV.add("TON NPI: "+Integer.parseInt(tlv.substring(4,6),16));
              	  parsedTLV.add("SS String: "+tlv.substring(6));
                }
                
                return parsedTLV;
            }
          
            /**
             * This 
             * @param tlv 
             */
             private ArrayList<String> parseUSSD_String(String tlv) 
             {
                 ArrayList<String> parsedTLV = new ArrayList<String>();
                 
                 //Append TAG Value
                 parsedTLV.add("USSD_STRING_TAG  :  "+tlv.substring(0,2));
                 
                 //Append Length
                 parsedTLV.add("USSD_String Length : "+Integer.parseInt(tlv.substring(2,4),16));
                 
                 //check if Alpha Id length is more than 0
                 if(Integer.parseInt(tlv.substring(2,4),16)>0)
                 { 
                	 parsedTLV.add("Data coding scheme: "+Integer.parseInt(tlv.substring(4,6),16));
                 	 parsedTLV.add("USSD String: "+tlv.substring(6)); 
                 }
                
                 return parsedTLV;
             }
      
             /**
              * This 
              * @param tlv 
              */
              private ArrayList<String> parseSMS_TPDU_String(String tlv) 
              {
                  ArrayList<String> parsedTLV = new ArrayList<String>();
                  
                  //Append TAG Value
                  parsedTLV.add("SMS_TPDU_TAG  :  "+tlv.substring(0,2));
                  
                  //Append Length
                  parsedTLV.add("SMS_TPDU Length : "+Integer.parseInt(tlv.substring(2,4),16));
                  
                  //check if Alpha Id length is more than 0
                  if(Integer.parseInt(tlv.substring(2,4),16)>0)
                  { 
                	  parsedTLV.add("TPDU SMS:  " +tlv.substring(4)); 
                  }
                  
                  return parsedTLV;
              }
       
                 
     
     
  
    public String typeOfCommand(String hexData){
    	byte data[] = TLVConstants.hexStringToByteArray(hexData);
    	//String str = "Type of Command: ";
    	switch(data[0]){
    	case 0x01:
    		return "REFRESH: "+ hexData; 
    	case 0x02:
    		return "MORE TIME: "+ hexData;
    	case 0x03:
    		return "POLL INTERVAL: "+ hexData;
    	case 0x04:
    		return "POLLING OFF: "+ hexData;
    	case 0x05:
    		return "SET UP EVENT LIST: "+ hexData;
    	case 0x10:
    		return "SET UP CALL: "+ hexData;
    	case 0x11:
    		return "SEND SS: "+ hexData;
    	case 0x12:
    		return "SEND USSD: "+ hexData;
    	case 0x13:
    		return "SEND SHORT MESSAGE: "+ hexData;
    	case 0x14:
    		return "SEND DTMF: "+ hexData;
    	case 0x15:
    		return "LAUNCH BROWSER: "+ hexData;
    	case 0x16:
    		return "GEOGRAPHICAL LOCATION REQUEST: "+ hexData;
    	case 0x20:
    		return "PLAY TONE: "+ hexData;
    	case 0x21:
    		return "DISPLAY TEXT: "+ hexData;
    	case 0x22:
    		return "GET INKEY: "+ hexData;
    	case 0x23:
    		return "GET INPUT: "+ hexData;
    	case 0x24:
    		return "SELECT ITEM: "+ hexData;
    	case 0x25:
    		return "SET UP MENU: "+ hexData;
    	case 0x26:
    		return "PROVIDE LOCAL INFORMATION: "+ hexData;
    	case 0x27:
    		return "TIMER MANAGEMENT: "+ hexData;
    	case 0x28:
    		return "SET UP IDLE MODE TEXT: "+ hexData;
    	case 0x30:
    		return "PERFORM CARD APDU: "+ hexData;
    	case 0x31:
    		return "POWER ON CARD: "+ hexData;
    	case 0x32:
    		return "POWER OFF CARD: "+ hexData;
    	case 0x33:
    		return "GET READER STATUS: "+ hexData;
    	case 0x34:
    		return "RUN AT COMMAND: "+ hexData;
    	case 0x35:
    		return "LANGUAGE NOTIFICATION: "+ hexData;
    	case 0x40:
    		return "OPEN CHANNEL: "+ hexData;
    	case 0x41:
    		return "CLOSE CHANNEL: "+ hexData;
    	case 0x42:
    		return "RECEIVE DATA: "+ hexData;
    	case 0x43:
    		return "SEND DATA: "+ hexData;
    	case 0x44:
    		return "GET CHANNEL STATUS: "+ hexData;
    	case 0x45:
    		return "SERVICE SEARCH: "+ hexData;
    	case 0x46:
    		return "GET SERVICE INFORMATION: "+ hexData;
    	case 0x47:
    		return "DECLARE SERVICE: "+ hexData;
    	case 0x50:
    		return "SET FRAMES: "+ hexData;
    	case 0x51:
    		return "GET FRAMES STATUS: "+ hexData;
    	case 0x60:
    		return "RETRIEVE MULTIMEDIA MESSAGE: "+ hexData;
    	case 0x61:
    		return "SUBMIT MULTIMEDIA MESSAGE: "+ hexData;
    	case 0x62:
    		return "DISPLAY MULTIMEDIA MESSAGE: "+ hexData;
    	case 0x70:
    		return "ACTIVATE: "+ hexData;
    	case 0x71:
    		return "CONTACTLESS STATE CHANGED: "+ hexData;
    	case 0x72:
    		return "COMMAND CONTAINER: "+ hexData;
    	case 0x73:
    		return "ENCAPSULATED SESSION CONTROL: "+ hexData;
    	case (byte) 0x81:
    		return "End of the proactive UICC session: "+ hexData;
    	}
		return hexData;   	
    }
    
    public String deviceIdentity(String hexData){
    	byte data[] = TLVConstants.hexStringToByteArray(hexData);
    	switch(data[0]){
    	case 0x01:
    		return "Keyboard: " + hexData;
    	case 0x02:
    		return "Display: " + hexData;
    	case 0x03:
    		return "Earpiece: " + hexData;
    	case 0x10:
    	case 0x11:
    	case 0x12:
    	case 0x13:
    	case 0x14:
    	case 0x15:
    	case 0x16:
    	case 0x17:
    		return "Additional Card Reader: " + hexData;
    	case 0x21:
    	case 0x22:
    	case 0x23:
    	case 0x24:
    	case 0x25:
    	case 0x26:
    	case 0x27:
    		return "Channel with Channel identifier x: "+ hexData;	
    	}
    	return hexData;
    }
    
    public String generalResult(String hexData){
    	byte data[] = TLVConstants.hexStringToByteArray(hexData);
    	switch(data[0]){
    	case 0x00:
    		return "Command performed successfully: " + hexData;
    	case 0x01:
    		return "Command performed with partial comprehension: " + hexData;
    	case 0x02:
    		return "Command performed, with missing information: " + hexData;
    	case 0x03:
    		return "REFRESH performed with additional Efs read: " + hexData;
    	case 0x04:
    		return "Command performed successfully, but requested icon could not be displayed: " + hexData;
    	case 0x05:
    		return "Command performed, but modified by call control by NAA: " + hexData;
    	case 0x06:
    		return "Command performed successfully, limited service: " + hexData;
    	case 0x07:
    		return "Command performed with modification: " + hexData;
    	case 0x08:
    		return "REFRESH performed but indicated NAA was not active: " + hexData;
    	case 0x09:
    		return "Command performed successfully, tone not played: " + hexData;
    	case 0x10:
    		return "Proactive UICC session terminated by the user: " + hexData;
    	case 0x11:
    		return "Backward move in the proactive UICC session requested by the user: " + hexData;
    	case 0x12:
    		return "No response from user: " + hexData;
    	case 0x13:
    		return "Help information required by the user: " + hexData;
    	case 0x14:
    		return "Reserved for GSM/3G: " + hexData;
    	case 0x20:
    		return "terminal currently unable to process command: " + hexData;
    	case 0x21:
    		return "Network currently unable to process command: " + hexData;
    	case 0x22:
    		return "User did not accept the proactive command: " + hexData;
    	case 0x23:
    		return "User cleared down call before connection or network release: "+ hexData;
    	case 0x24:
    		return "Action in contradiction with the current timer state: " + hexData;
    	case 0x25:
    		return "Interaction with call control by NAA, temporary problem: " + hexData;
    	case 0x26:
    		return "Launch browser generic error code: " + hexData;
    	case 0x27:
    		return "MMS temporary problem: " + hexData;
    	case 0x30:
    		return "Command beyond terminal's capabilities: " + hexData;
    	case 0x31:
    		return "Command type not understood by terminal: " + hexData;
    	case 0x32:
    		return "Command data not understood by terminal: " + hexData;
    	case 0x33:
    		return "Command number not known by terminal: " + hexData;
    	case 0x34:
    	case 0x35:
    	case 0x37:
    		return "reserved for GSM/3G;" + hexData;
    	case 0x36:
    		return "Error, required values are missing: " + hexData;
    	case 0x38:
    		return "MultipleCard commands error: " + hexData;
    	case 0x39:
    		return "Interaction with call control by NAA, permanent problem: "+ hexData;
    	case 0x3A:
    		return "Bearer Independent Protocol error: "+ hexData;
    	case 0x3B:
    		return "Access Technology unable to process command: "+ hexData;
    	case 0x3C:
    		return "Frames error: "+ hexData;
    	case 0x3D:
    		return "MMS Error: "+ hexData;
    		
    	}
    	return hexData;
    }
    
    public String additionalInfo(String hexData){
		return hexData;
    	
    }
    
    
    public String timeUnit(String hexData){
    	byte data[] = TLVConstants.hexStringToByteArray(hexData);
    	switch(data[0]){
    	case 0x00:
    		return "Minutes: " + hexData;
    	case 0x01:
    		return "Seconds: " + hexData;
    	case 0x02:
    		return "Tenth of code: " + hexData;
    	}
		return hexData;
    	
    }
    
    public String timeInterval(String hexData){
    	byte data[] = TLVConstants.hexStringToByteArray(hexData);
    	switch(data[0]){
    	case 0x01:
    		return "1 unit: " + hexData;
    	case 0x02:
    		return "2 units: " + hexData;
    	case 0x03:
    		return "255 units: " + hexData;
    	}
		return hexData;
    }

}
