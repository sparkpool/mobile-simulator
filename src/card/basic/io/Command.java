/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.basic.io;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author user
 */
class Command 
{
    private static BasicIO basicIOInstance = BasicIO.getBasicIOInstance();;
    public static String operationMode;
    public static String CLS;
    public static String INS;
    public static String P1;
    public static String P2;
    public static String P3;
    public static String DATA;
    
    /**
     * This String object store the previously successfully executed command
     */
    private String previousExecutedCommand;
   
    
    void initCommand()
    {
        CLS ="";
        INS = "";
        P1= "";
        P2="";
        P3="";
        DATA="";
    }
    
    String executeCommand()
    {
        String stringCommand=Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA;
        byte [] cmd = convertToByteArray(stringCommand.toUpperCase());
        //byte c[] = {(byte)0xA0, (byte)0xA4, 0x00, 0x00, 0x02, 0x3F, 0x00};
        basicIOInstance.sendAPDU(cmd);
        previousExecutedCommand = stringCommand;
        return Integer.toHexString(basicIOInstance.getSW1SW2()).toUpperCase();
    }
    
    /**
     * This method used to get the previously executed command
     * @return 
     */
    public String getLastExecutedCommand()
    {
        return previousExecutedCommand;
    }
    
    /**
     * This method used to validate the command whether it contains only the data in the hexadecimal format
     * @param command: Complete command including each and every part part of the command
     * @return 
     */
    boolean validateCommand(String command)
    {
        
        for(int i= 0; i<command.length();i++)
        {
            switch (command.charAt(i)) 
            {
                case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                case 'A':case 'a':case 'B':case 'b':case 'C':case 'c':case 'D':case 'd':case 'E':case 'e':case 'F':case 'f':
                   break;
                default:
                    return false;
            }
        }
        return true;
    }
    
    byte[] convertToByteArray(String data)
    {
        byte b1=0;
        byte b2=0;
        byte cmd[] = new byte[data.length()/2];
        int count =0;
        for(int i=0; i< data.length(); i=i+2)
        {
             switch(data.charAt(i))
             {
                 case '0':case '1':case '2': case '3':case '4':case '5':case '6':
                 case '7': case '8': case '9':
                     b1=(byte)((byte)(data.charAt(i)&0x00FF)-0x0030);
                     break;
                     
                 case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                     b1=(byte)((byte)(data.charAt(i)&0x00FF)-0x0037);
                     break;
             }
             switch(data.charAt(i+1))
             {
                 case '0':case '1':case '2': case '3':case '4':case '5':case '6':
                 case '7': case '8': case '9':
                     b2=(byte)((byte)(data.charAt(i+1)&0x00FF)-0x0030);
                     break;
                     
                 case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                     b2=(byte)((byte)(data.charAt(i+1)&0x00FF)-0x0037);
                     break;
             }
             cmd[count++]=(byte)(b1<<4|b2);
        }
        return cmd;
    }
    
    public String getSW1()
    {
        return Integer.toHexString(basicIOInstance.getSW1()).length() == 1?"0"+Integer.toHexString(basicIOInstance.getSW1()).toUpperCase():Integer.toHexString(basicIOInstance.getSW1()).toUpperCase();
        
    }
    public String getSW2()
    {
         return Integer.toHexString(basicIOInstance.getSW2()).length() == 1?"0"+Integer.toHexString(basicIOInstance.getSW2()).toUpperCase():Integer.toHexString(basicIOInstance.getSW2()).toUpperCase();
    }
    public String getSW1SW2()
    {
         return Integer.toHexString(basicIOInstance.getSW1SW2()).toUpperCase();
    }
    
    public static void setOperationMode(String opMode)
    {
        operationMode = opMode;
    }
    
    String convertToHextString(int decimal)
    {
        return Integer.toHexString(decimal).length()==1?"0"+Integer.toHexString(decimal).toUpperCase():Integer.toHexString(decimal).toUpperCase();
        
    }
    
    public String getResponseData()
    {
        return DatatypeConverter.printHexBinary(basicIOInstance.getResponseData());
    }
}
