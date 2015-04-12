/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.toolkitcommands;

/**
 *
 * @author user
 */
public class SendSS extends BasicCommand{
     private static SendSS singletonInstance;
    
    private SendSS(){}
    
    public static SendSS getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new SendSS();
        }
        return singletonInstance;
    }

    @Override
    public void parseCommand(String command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
