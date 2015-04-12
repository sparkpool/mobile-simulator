/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.basic.io;

/**
 *
 * @author user
 */
class Command 
{
    private static Command commandInstance;
    
    private static BasicIO basicIOInstance;
    
    static String operationMode;
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
    /**
     * This method used to get the singleton instance of the Command class 
 This instance will expose the API to communicate the card
     * @return : Singleton instance of the Command class
     */
    static Command getCardCommInstance()
    {
        if(commandInstance == null)
        {
            basicIOInstance = BasicIO.getBasicIOInstance();
            commandInstance = new Command();
        }
        return commandInstance;
    }
    
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
        return null;
    }
    
    /**
     * This method used to get the previously executed command
     * @return 
     */
    String getLastExecutedCommand()
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
    
    byte[] convertToByteArray(String command)
    {
        return null;
    }
    
    String getSW1()
    {
        return Integer.toHexString(basicIOInstance.getSW1()).length() == 1?"0"+Integer.toHexString(basicIOInstance.getSW1()).toUpperCase():Integer.toHexString(basicIOInstance.getSW1()).toUpperCase();
        
    }
    String getSW2()
    {
         return Integer.toHexString(basicIOInstance.getSW1()).length() == 1?"0"+Integer.toHexString(basicIOInstance.getSW2()).toUpperCase():Integer.toHexString(basicIOInstance.getSW1()).toUpperCase();
    }
    String getSW1SW2()
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
}
