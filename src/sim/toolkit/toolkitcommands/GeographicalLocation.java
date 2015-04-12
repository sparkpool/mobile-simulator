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
public class GeographicalLocation extends BasicCommand{
    
     private static GeographicalLocation singletonInstance;
    
    private GeographicalLocation(){}
    
    public static GeographicalLocation getInstance()
    {
        if(singletonInstance == null)
        {
            singletonInstance = new GeographicalLocation();
        }
        return singletonInstance;
    }

    @Override
    public void parseCommand(String command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
