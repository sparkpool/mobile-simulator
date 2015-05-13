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
public class SecurityCommands extends Command{
    
    private static SecurityCommands securityInstance;
    
    public static SecurityCommands getSecurityInstance()
    {
        if(securityInstance == null)
        {
            securityInstance = new SecurityCommands();
        }
        return securityInstance;
    }
    
    public String varifyCHV(int chvNumber, String pinValue)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "20";
        Command.P1="00";
        Command.P2="00";
        Command.P3= "08";
        Command.DATA = pinValue;
       
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    public String changeCHV(int chvNumber, String oldCHV, String newCHV)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "24";
        Command.P1="00";
        Command.P2=convertToHextString(chvNumber);
        Command.P3= "10";
        Command.DATA = oldCHV+newCHV;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    public String disableCHV1(String chv1)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "26";
        Command.P1="00";
        Command.P2="01";
        Command.P3= "08";
        Command.DATA = chv1;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    public String enableCHV1(String chv1)
    {
        Command.CLS = operationMode.equals("00")?"A0":"00";
        Command.INS = "28";
        Command.P1="00";
        Command.P2="01";
        Command.P3= "08";
        Command.DATA = chv1;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
}
