package sim.toolkit.tlvparser;

public interface TLVConstants {
	
    public static final byte TAG_SET_CR = (byte) 0x80;
    public static final byte COMMAND_DETAILS_TAG = (byte) 0x01;
    public static final byte DEVICE_IDENTITY_TAG = (byte) 0x02;
    public static final byte RESULT_TAG = (byte) 0x03;
    public static final byte DURATION_TAG = (byte) 0x04;
    public static final byte ALPHA_IDENTIFIER_TAG = (byte) 0x05;
    public static final byte ADDRESS_TAG = (byte) 0x06;
    public static final byte CAPABILITY_CONFIGURATION_PARAMETERS_TAG = (byte) 0x07;
    public static final byte SUBADDRESS_TAG = (byte) 0x08;
    public static final byte SS_STRING_TAG = (byte) 0x09;
    public static final byte USSD_STRING_TAG = (byte) 0x0A;
    public static final byte SMS_TPDU_TAG = (byte) 0x0B;
    public static final byte CELL_BROADCAST_PAGE_TAG = (byte) 0x0C;
    public static final byte TEXT_STRING_TAG = (byte) 0x0D;
    public static final byte TONE_TAG = (byte) 0x0E;
    public static final byte ITEM_TAG = (byte) 0x0F;
    public static final byte ITEM_IDENTIFIER_TAG = (byte) 0x10;
    public static final byte RESPONSE_LENGTH_TAG = (byte) 0x11;
    public static final byte FILE_LIST_TAG = (byte) 0x12;
    public static final byte LOCATION_INFORMATION_TAG = (byte) 0x13;
    public static final byte IMEI_TAG = (byte) 0x14;
    public static final byte HELP_REQUEST_TAG = (byte) 0x15;
    public static final byte NETWORK_MEASUREMENT_RESULTS_TAG = (byte) 0x16;
    public static final byte DEFAULT_TEXT_TAG = (byte) 0x17;
    public static final byte ITEMS_NEXT_ACTION_INDICATOR_TAG = (byte) 0x18;
    public static final byte EVENT_LIST_TAG = (byte) 0x19;
    public static final byte CAUSE_TAG = (byte) 0x1A;
    public static final byte LOCATION_STATUS_TAG = (byte) 0x1B;
    public static final byte TRANSACTION_IDENTIFIER_TAG = (byte) 0x1C;
    public static final byte BCCH_CHANNEL_LIST_TAG = (byte) 0x1D;
    public static final byte ICON_IDENTIFIER_TAG = (byte) 0x1E;
    public static final byte ITEM_ICON_IDENTIFIER_LIST_TAG = (byte) 0x1F;
    public static final byte CARD_READER_STATUS_TAG = (byte) 0x20;
    public static final byte CARD_ATR_TAG = (byte) 0x21;
    public static final byte C_APDU_TAG = (byte) 0x22;
    public static final byte R_APDU_TAG = (byte) 0x23;
    public static final byte TIMER_IDENTIFIER_TAG = (byte) 0x24;
    public static final byte TIMER_VALUE_TAG = (byte) 0x25;
    public static final byte DATE_TIME_AND_TIME_ZONE_TAG = (byte) 0x26;

    public static final byte CALL_CONTROL_REQUESTED_ACTION_TAG = (byte) 0x27;
    public static final byte AT_COMMAND_TAG = (byte) 0x28;
    public static final byte AT_RESPONSE_TAG = (byte) 0x29;
    public static final byte BC_REPEAT_INDICATOR_TAG = (byte) 0x2A;
    public static final byte IMMEDIATE_RESPONSE_TAG = (byte) 0x2B;
    public static final byte DTMF_STRING_TAG = (byte) 0x2C;
    public static final byte LANGUAGE_TAG = (byte) 0x2D;
    public static final byte TIMING_ADVANCE_TAG = (byte) 0x2E;
    public static final byte AID_TAG = (byte) 0x2F;
    public static final byte BROWSER_IDENTITY_TAG = (byte) 0x30;
    public static final byte URL_TAG = (byte) 0x31;
    public static final byte BEARER_TAG = (byte) 0x32;
    public static final byte PROVISIONING_REFERENCE_FILE_TAG = (byte) 0x33;
    public static final byte BROWSER_TERMINATION_CAUSE_TAG = (byte) 0x34;
    public static final byte BEARER_DESCRIPTION_TAG = (byte) 0x35;
    public static final byte CHANNEL_DATA_TAG = (byte) 0x36;
    public static final byte CHANNEL_DATA_LENGTH_TAG = (byte) 0x37;
    public static final byte CHANNEL_STATUS_TAG = (byte) 0x38;
    public static final byte BUFFER_SIZE_TAG = (byte) 0x39;
    public static final byte CARD_READER_IDENTIFIER_TAG = (byte) 0x3A;
    public static final byte FILE_UPDATE_INFORMATION_TAG = (byte) 0x3B;
    public static final byte UICC_TERMINAL_INTERFACE_TRANSPORT_LEVEL_TAG = (byte) 0x3C;
    public static final byte NOT_USED = (byte) 0x3D;
    public static final byte OTHER_ADDRESS_TAG = (byte) 0x3E;
    public static final byte ACCESS_TECHNOLOGY_TAG = (byte) 0x3F;
    public static final byte DISPLAY_PARAMETERS_TAG = (byte) 0x40;
    public static final byte SERVICE_RECORD_TAG = (byte) 0x41;
    public static final byte DEVICE_FILTER_TAG = (byte) 0x42;
    public static final byte SERVICE_SEARCH_TAG = (byte) 0x43;
    public static final byte ATTRIBUTE_INFORMATION_TAG = (byte) 0x44;
    public static final byte SERVICE_AVAILABILITY_TAG = (byte) 0x45;
    public static final byte ESN_TAG = (byte) 0x46;
    public static final byte NETWORK_ACCESS_NAME_TAG = (byte) 0x47;
    public static final byte CDMA_SMS_TPDU_TAG = (byte) 0x48;
    public static final byte REMOTE_ENTITY_ADDRESS_TAG = (byte) 0x49;
    public static final byte I_WLAN_IDENTIFIER_TAG = (byte) 0x4A;
    public static final byte I_WLAN_ACCESS_STATUS_TAG = (byte) 0x4B;
    public static final byte TEXT_ATTRIBUTE_TAG = (byte) 0x50;
    public static final byte ITEM_TEXT_ATTRIBUTE_LIST_TAG = (byte) 0x51;
    public static final byte PDP_CONTEXT_ACTIVATION_PARAMETER_TAG = (byte) 0x52;
    public static final byte IMEISV_TAG = (byte) 0x62;
    public static final byte BATTERY_STATE_TAG = (byte) 0x63;
    public static final byte BROWSING_STATUS_TAG = (byte) 0x64;
    public static final byte NETWORK_SEARCH_MODE_TAG = (byte) 0x65;
    public static final byte FRAME_LAYOUT_TAG = (byte) 0x66;
    public static final byte FRAMES_INFORMATION_TAG = (byte) 0x67;
    public static final byte FRAME_IDENTIFIER_TAG = (byte) 0x68;
    public static final byte UTRAN_MEASUREMENT_QUALIFIER_TAG = (byte) 0x69;
    public static final byte MULTIMEDIA_MESSAGE_REFERENCE_TAG = (byte) 0x6A;
    public static final byte MULTIMEDIA_MESSAGE_IDENTIFIER_TAG = (byte) 0x6B;
    public static final byte MULTIMEDIA_MESSAGE_TRANSFER_STATUS_TAG = (byte) 0x6C;
    public static final byte MEID_TAG = (byte) 0x6D;
    public static final byte MULTIMEDIA_MESSAGE_CONTENT_IDENTIFIER_TAG = (byte) 0x6E;
    public static final byte MULTIMEDIA_MESSAGE_NOTIFICATION_TAG = (byte) 0x6F;
    public static final byte LAST_ENVELOPE_TAG = (byte) 0x70;
    public static final byte REGISTRY_APPLICATION_DATA_TAG = (byte) 0x71;
    public static final byte PLMNWACT_LIST_TAG = (byte) 0x72;
    public static final byte ROUTING_AREA_INFORMATION_TAG = (byte) 0x73;
    public static final byte UPDATE_ATTACH_TYPE_TAG = (byte) 0x74;
    public static final byte REJECTION_CAUSE_CODE_TAG = (byte) 0x75;
    public static final byte GEOGRAPHICAL_LOCATION_PARAMETERS_TAG = (byte) 0x76;
    public static final byte GAD_SHAPES_TAG = (byte) 0x77;
    public static final byte NMEA_SENTENCE_TAG = (byte) 0x78;
    public static final byte PLMN_LIST_TAG = (byte) 0x79;
    public static final byte BROADCAST_NETWORK_INFORMATION_TAG = (byte) 0x7A;
    public static final byte ACTIVATE_DESCRIPTOR_TAG = (byte) 0x7B;

    //by me
       
        public static final byte PROVISIONING_FILE_TAG = 0x33;
        public static final byte UICC_TRANSPORT_LEVEL= 0x3C;
        public static final byte ATTRIBUTE_INFO_TAG = 0x44;
        public static final byte MULTIMEDIA_CONTENT_IDENTIFIER_TAG = 0x6E;
        public static final byte ACCESS_TECH_TAG = 0x3F;
        
        //Device ID's
        public static final String DEV_ID_KEYPAD = "01";
        public static final String DEV_ID_DISPLAY = "02";
        public static final String DEV_ID_EARPIECE = "03";
        
        public static final String DEV_ID_ADD_CR1 = "10";
        public static final String DEV_ID_ADD_CR2 = "11";
        public static final String DEV_ID_ADD_CR3 = "12";
        public static final String DEV_ID_ADD_CR4 = "13";
        public static final String DEV_ID_ADD_CR5 = "14";
        public static final String DEV_ID_ADD_CR6 = "15";
        public static final String DEV_ID_ADD_CR7 = "16";
        public static final String DEV_ID_ADD_CR8 = "17";
        
        public static final String DEV_ID_CH_1 = "21";
        public static final String DEV_ID_CH_2 = "22";
        public static final String DEV_ID_CH_3 = "23";
        public static final String DEV_ID_CH_4 = "24";
        public static final String DEV_ID_CH_5 = "25";
        public static final String DEV_ID_CH_6 = "26";
        public static final String DEV_ID_CH_7 = "27";
        public static final String DEV_ID_CH_8 = "28";
        
        public static final String DEV_ID_UICC = "81";
        public static final String DEV_ID_TERMINAL = "82";
        public static final String DEV_ID_NETWORK = "83";
        
        public static final String BER_TAG_MENUSELECTION = "D3";
        public static final String BER_TAG_CALLCONTROL = "D4";
        public static final String BER_TAG_PROACTIVE_COMMAND = "D0";
        public static final String BER_TAG_SMSPP_DOWNLOAD = "D1";
        public static final String BER_TAG_CELL_BROADCAST = "D2";
        public static final String BER_TAG_EVENT_DOWNLOAD = "D6";
        public static final String BER_TAG_TIMER_EXPIRATION = "D7";
        public static final String BER_TAG_GEO_LOCATION_REPORTING = "DD";
        
        
        //Event List
        public static final String EVENT_MT_CALL = "00";
        public static final String EVENT_CALL_CONNECTED = "01";
        public static final String EVENT_CALL_DISCONNECTED = "02";
        public static final String EVENT_LOCATION_STATUS = "03";
        public static final String EVENT_USER_ACTIVITY = "04";
        public static final String EVENT_IDLE_SCREEN_AVAILABLE = "05";
        public static final String EVENT_CARD_READER_STATUS = "06";
        public static final String EVENT_LANG_SELECTION = "07";
        public static final String EVENT_BROWSER_TERMINATION = "08";
        public static final String EVENT_DATA_AVAILABLE = "09";
        public static final String EVENT_CHANNEL_STATUS= "0A";
        public static final String EVENT_ACCESS_TECHNOLOGY_CHANGE_SINGLE = "0B";
        public static final String EVENT_ACCESS_TECHNOLOGY_CHANGE_MULTIPLE = "14";
        public static final String EVENT_DISPLAY_PARAMETER_CHANGE = "0C";
        public static final String EVENT_LOCAL_CONNECTION = "0D";
        public static final String EVENT_NETWORK_SERACH_MODE_CHANGE = "0E";
        public static final String EVENT_BROWSING_STATUS = "0F";
        public static final String EVENT_FRAME_INFO_CHANGE = "10";
        public static final String EVENT_IWLAN_ACCESS_STATUS = "11";
        public static final String EVENT_NETWORK_REJECTION = "12";
        public static final String EVENT_HCI_CONNECTIVITY = "13";
        public static final String EVENT_CSG_CELL_SELECTION= "15";
        public static final String EVENT_CONTACTLESS_STATE_REQUEST = "16";
        public static final String EVENT_IMS_REGISTRATION = "17";
        public static final String EVENT_IMS_INCOMING_DATA = "18";
        public static final String EVENT_PROFILE_CONTAINER = "19";
        public static final String EVENT_POLL_INTERVAL = "1C";
        
        //command name
        public static final byte TOC_REFRESH = 0x01;
        public static final byte TOC_MORE_TIME = 0x02;
        public static final byte TOC_POLL_INTERVAL = 0x03;
        public static final byte TOC_POLLING_OFF = 0x04;
        public static final byte TOC_SETUP_EVENT_LIST = 0x05;
        public static final byte TOC_SETUP_CALL = 0x10;
        public static final byte TOC_SEND_SS = 0x11;
        public static final byte TOC_SEND_USSD = 0x12;
        public static final byte TOC_SEND_SMS = 0x13;
        public static final byte TOC_SEND_DTMF = 0x14;
        public static final byte TOC_LAUNCH_BROWSER = 0x15;
        public static final byte TOC_GEO_LOCATION_REQUEST = 0x16;
        public static final byte TOC_PLAY_TONE = 0x20;
        public static final byte TOC_DISPLAY_TEXT = 0x21;
        public static final byte TOC_GET_INKEY = 0x22;
        public static final byte TOC_GET_INPUT = 0x23;
        public static final byte TOC_SELECT_ITEM = 0x24;
        public static final byte TOC_SETUP_MENU = 0x25;
        public static final byte TOC_PLI = 0x26;
        public static final byte TOC_TIMER_MANAGEMENT = 0x27;
        public static final byte TOC_SETUP_IDLE_MODE_TEXT = 0x28;
        public static final byte TOC_PERFORM_CARD_APDU = 0x30;
        public static final byte TOC_POWER_ON_CARD = 0x31;
        public static final byte TOC_POWER_OFF_CARD = 0x32;
        public static final byte TOC_GET_READER_STATUS = 0x33;
        public static final byte TOC_RUN_AT_COMMAND = 0x34;
        public static final byte TOC_LANGUAGE_NOTIFICATION = 0x35;
        public static final byte TOC_OPEN_CHANNEL = 0x40;
        public static final byte TOC_CLOSE_CHANNEL = 0x41;
        public static final byte TOC_RECEIVE_DATA = 0x42;
        public static final byte TOC_SEND_DATA = 0x43;
        public static final byte TOC_GET_CHANNEL_STATUS = 0x44;
        public static final byte TOC_SERVICE_SEARCH = 0x45;
        public static final byte TOC_GET_SERVICE_INFO = 0x46;
        public static final byte TOC_DECLARE_SERVICE = 0x47;
        public static final byte TOC_SET_FRAME = 0x50;
        public static final byte TOC_GET_FRAME_STATUS = 0x51;
        
        
        
        
        
    
    public static byte[] hexStringToByteArray(String s){
    	int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    
    
    /**
     * This method will convert the Hex data into the ASCII data
     * @param hexData: This will contain the hex data in the String format
     * Lets sat Hex Data is 414243444546313232 then it will convert it to ABCDEF123
     * @return : Return the equivalent ASCII data.
     */
    public static String convertFromHexToAscii(String hexData)
    {
    	int n = hexData.length();
    	  StringBuilder sb = new StringBuilder(n / 2);
    	  for (int i = 0; i < n; i += 2) {
    	    char a = hexData.charAt(i);
    	    char b = hexData.charAt(i + 1);
    	    sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
    	  }
    	  return sb.toString();
    }

    public static int hexToInt(char ch) {
    	  if ('a' <= ch && ch <= 'f') { return ch - 'a' + 10; }
    	  if ('A' <= ch && ch <= 'F') { return ch - 'A' + 10; }
    	  if ('0' <= ch && ch <= '9') { return ch - '0'; }
    	  throw new IllegalArgumentException(String.valueOf(ch));
    	}


	public static String convertFromHexToDateAndTime(String substring) {
		// TODO Auto-generated method stub
		return null;
	}


	public static String convertFromHexToTimerVAlue(String substring) {
		// TODO Auto-generated method stub
		return null;
	}
}