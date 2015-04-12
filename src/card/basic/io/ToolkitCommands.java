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
 public class ToolkitCommands extends Command
 {
    
    private static ToolkitCommands tkCmdInstance;
    
    public static ToolkitCommands getToolkitCommandsInstance()
    {
        if(tkCmdInstance == null)
        {
            tkCmdInstance = new ToolkitCommands();
        }
        return tkCmdInstance;
    }
    
    /**
     * This method used to send the fetch command
     * @param dataLength: Length of the data to fetch
     * @return : Return the status word of the command
     */
    public String fetch(int dataLength)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "12";
        Command.P1="00";
        Command.P2="00";
        Command.P3 = convertToHextString(dataLength);
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return Command.getCardCommInstance().executeCommand();
    }
    
    
    
    /**
     * This method used to send the terminal response command
     * @param terminalResponse: Terminal Response of the command
     * @return : the status word of the command
     */
    public String terminalResponse(String terminalResponse)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "14";
        Command.P1="00";
        Command.P2="00";        
        int trLength = terminalResponse.length()/2;
        Command.P3 = convertToHextString(trLength);
        Command.DATA = terminalResponse;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return Command.getCardCommInstance().executeCommand();
    }
    
    /**
     * This method used to send the Terminal Profile command to the card
     * @param teminalProfileData: Terminal Profile data
     * @return : Return the status word
     */
    public String terminalprofile(String teminalProfileData)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "10";
        Command.P1="00";
        Command.P2="00";        
        int trLength = teminalProfileData.length()/2;
        Command.P3 = convertToHextString(trLength);
        Command.DATA = teminalProfileData;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return Command.getCardCommInstance().executeCommand();
    }
    
    /**
     * This method used to send the envelope command
     * @param envelopeData : Data to send in the envelope command
     * @return : return the status word
     */
    public String envelope(String envelopeData)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "10";
        Command.P1="00";
        Command.P2="00";        
        int trLength = envelopeData.length()/2;
        Command.P3 = convertToHextString(trLength);
        Command.DATA = envelopeData;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return Command.getCardCommInstance().executeCommand();
    }
    
    
}
