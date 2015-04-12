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
   
    public static final byte TAG_ALPHA_ID = 0x05;
    
    public static final byte TAG_SET_CR = (byte)0x80;
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
   
   
   /**
    * This method will used to parse the any TLV which which will be provided in the parameter of the method
    * It will store each and every part of TLV in the List and in the same order as defined in the standard
    * @param tlv: TLV(<Tag><Length><Value>)
    * @return : Return the parsed TLV in the list
    */
   public ArrayList<String> parseTLV(String tlv)
   { 
       int tag = Integer.parseInt(tlv.substring(0, 2), 16);
       ArrayList<String> parsedTLV = null;
       switch(tag)
       {
           case TAG_ALPHA_ID:
           case (byte)(TAG_ALPHA_ID|TAG_SET_CR):
               parsedTLV= parseAlphaIdentifier(tlv);
               break;
           //TODO put each and every TAG value defined as the case and create one method to parse each TLV
           
       }
       return parsedTLV;
   }

   /**
    * This 
    * @param tlv 
    */
    private ArrayList<String> parseAlphaIdentifier(String tlv) 
    {
        ArrayList<String> parsedTLV = new ArrayList<>();
        
        //Append TAG Value
        parsedTLV.add("TAG_ALPHA_IDENTIFER  :  "+tlv.substring(0,2));
        
        //Append Length
        parsedTLV.add("Alpha Identifier Length : "+Integer.parseInt(tlv.substring(2,4),16));
        
        //check if Alpha Id length is more than 0
        if(Integer.parseInt(tlv.substring(2,4),16)>0)
        { 
            //Append Alpha Id Text
            parsedTLV.add("Alpha Id Text : "+convertFromHexToAscii(tlv.substring(4)));
        }
        
        return parsedTLV;
    }
    
    
    /**
     * This method will convert the Hex data into the ASCII data
     * @param hexData: This will contain the hex data in the String format
     * Lets sat Hex Data is 414243444546313232 then it will convert it to ABCDEF123
     * @return : Return the equivalent ASCII data.
     */
    public String convertFromHexToAscii(String hexData)
    {
        //TODO
        return null;
    }
}