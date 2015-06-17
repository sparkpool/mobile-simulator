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
    private int LocationStatus;
    private int AccessTechnology;
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
    
    
    public String  getTerminalProfile2G(){
       return  TerminalProfile2G;
    }
    public String  getTerminalProfile3G(){
       return TerminalProfile3G ;
    }
    public String  getIMEI( ){
        return IMEI ;
    }   
    public String  getLanguageCode( ){
        return LanguageCode ;
    }
    public String  getPollingDuration(){
        return PollingDuration ;
    }
    public String  getUSSDResponse(){
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
    
    
    
    public String  getEnvelopeType(){
        return EnvelopeType ;
    }
    public String  getAddress(){
        return Address ;
    }
    public String  getSubAddress(){
        return SubAddress ;
    }   
    public String  getTransactionID(){
        return TransactionID ;
    }
    public String  getTimerID(){
        return TimerID ;
    }
    public String  getMenuID( ){
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
    public void  setLocationStatus( int str){
        LocationStatus = str;
    }
    public void  setAccessTechnology( int str){
        AccessTechnology = str;
    }
    public void  setNMR( String str){
        NMR = str;
    }
    
    public String  getMCC( ){
       return MCC ;
    }
    
    public String  getMNC( ){
       return MNC ;
    }
    public String  getLAC( ){
       return LAC ;
    }
    public String  getCellID(){
       return CellID ;
    }   
    public int  getLocationStatus(){
       return LocationStatus ;
    }
    public int  getAccessTechnology(){
       return AccessTechnology ;
    }
    public String  getNMR( ){
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
    
    public String  getTON_NPI(){
        return TON_NPI ;
    }
    public String  getCC( ){
        return CC ;
    }   
    public String  getPhNumber(){
        return PhNumber ;
    }
    public String  getTransID( ){
        return TransID ;
    }
    public String  getCallSubAddress(){
        return CallSubAddress ;
    }
    
    
    
    
}
