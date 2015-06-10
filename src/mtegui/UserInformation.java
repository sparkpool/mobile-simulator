/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtegui;

/**
 *
 * @author sandesh
 */
public class UserInformation {
    //MEConfiguration Data
    private String TerminalProfile2G;
    private String TerminalProfile3G;
    private String IMEI;
    private String LanguageCode;
    private String PollingDuration;
    private String USSDResponse;
    
    //EnvelopeGenrator info
    
    private String EnvelopeType;
    private String Address;
    private String SubAddress;
    private String TransactionID;
    private String TimerID;
    private String MenuID;
    
    //Network Configuration info
    
    private String MCC;
    private String MNC;
    private String LAC;
    private String CellID;
    private String LocationStatus;
    private String AccessTechnology;
    private String NMR;
    
    //Call window Information
    private String TON_NPI;
    private String CC;
    private String PhNumber;
    private String TransID;
    private String CallSubAddress;
    
    
    
    
     private static UserInformation UserInformationInstance;
   
   /**
    * Private constructor of the TLVParser Class
    */
   private UserInformation(){}
   
   /**
    * This method used to get the singleton instance of the TLVParser class 
    * @return 
    */
   public static UserInformation getUserInformationInstance()
   {
       if(UserInformationInstance == null)
       {
           UserInformationInstance = new UserInformation();
       }
       return UserInformationInstance;
   }
    
  
    
    
    public void  SetTerminalProfile2G( String str){
        TerminalProfile2G = str;
    }
    public void  SetTerminalProfile3G( String str){
        TerminalProfile3G = str;
    }
    public void  setIMEI( String str){
        IMEI = str;
    }   
    public void  setLanguageCode( String str){
        LanguageCode = str;
    }
    public void  setPollingDuration( String str){
        PollingDuration = str;
    }
    public void  setUSSDResponse( String str){
        USSDResponse = str;
    }
    
    
    public String  getTerminalProfile2G( String str){
       return  TerminalProfile2G;
    }
    public String  getTerminalProfile3G( String str){
       return TerminalProfile3G ;
    }
    public String  getIMEI( String str){
        return IMEI ;
    }   
    public String  getLanguageCode( String str){
        return LanguageCode ;
    }
    public String  getPollingDuration( String str){
        return PollingDuration ;
    }
    public String  getUSSDResponse( String str){
       return  USSDResponse;
    }
    
 
    public void  setEnvelopeType( String str){
        EnvelopeType = str;
    }
    public void  setAddress( String str){
        Address = str;
    }
    public void  setSubAddress( String str){
        SubAddress = str;
    }   
    public void  setTransactionID( String str){
        TransactionID = str;
    }
    public void  setTimerID( String str){
        TimerID = str;
    }
    public void  setMenuID( String str){
        MenuID = str;
    }
    
    
    
    public String  getEnvelopeType( String str){
        return EnvelopeType ;
    }
    public String  getAddress( String str){
        return Address ;
    }
    public String  getSubAddress( String str){
        return SubAddress ;
    }   
    public String  getTransactionID( String str){
        return TransactionID ;
    }
    public String  getTimerID( String str){
        return TimerID ;
    }
    public String  getMenuID( String str){
       return MenuID ;
    }
    
    
    
    
    
    public void  setMCC( String str){
        MCC = str;
    }
    
    public void  setMNC( String str){
        MNC = str;
    }
    public void  setLAC( String str){
        LAC = str;
    }
    public void  setCellID( String str){
        CellID = str;
    }   
    public void  setLocationStatus( String str){
        LocationStatus = str;
    }
    public void  setAccessTechnology( String str){
        AccessTechnology = str;
    }
    public void  setNMR( String str){
        NMR = str;
    }
    
    public String  getMCC( String str){
       return MCC ;
    }
    
    public String  getMNC( String str){
       return MNC ;
    }
    public String  getLAC( String str){
       return LAC ;
    }
    public String  getCellID( String str){
       return CellID ;
    }   
    public String  getLocationStatus( String str){
       return LocationStatus ;
    }
    public String  getAccessTechnology( String str){
       return AccessTechnology ;
    }
    public String  getNMR( String str){
       return NMR ;
    }
    
  
    public void  setTON_NPI( String str){
        TON_NPI = str;
    }
    public void  setCC( String str){
        CC = str;
    }   
    public void  setPhNumber( String str){
        PhNumber = str;
    }
    public void  setTransID( String str){
        TransID = str;
    }
    public void  setCallSubAddress( String str){
        CallSubAddress = str;
    }
    
    public String  getTON_NPI( String str){
        return TON_NPI ;
    }
    public String  getCC( String str){
        return CC ;
    }   
    public String  getPhNumber( String str){
        return PhNumber ;
    }
    public String  getTransID( String str){
        return TransID ;
    }
    public String  getCallSubAddress( String str){
        return CallSubAddress ;
    }
    
    
    
    
}
