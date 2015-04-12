/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.basic.io;
import java.util.List;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author user
 */
class BasicIO {
    
    private static BasicIO basicIO;
    
    private List<CardTerminal> terminalList= null;
    
    Card card;
    CardChannel channel;
    private ResponseAPDU respAPDU;
    
    static BasicIO getBasicIOInstance()
    {
        if(basicIO == null)
        {
            basicIO = new BasicIO();
        }
        return basicIO;
    }

    
    int connetWithCard(int readerNumber)
    {  
        try
        {
            CardTerminal terminal = terminalList.get(readerNumber);
            card = terminal.connect("T=0");
            channel = card.getBasicChannel();
            
        }catch(CardException e)
        {
        }
        return 0;
    }
    
    void disconnectwithCard(String cardReaderName)
    {
       try{
           card.disconnect(true);
       }catch(CardException e)
       {
       }
    }
    
    public byte[] getAtr()
    {
        return card.getATR().getBytes();
    }
    
    public List<CardTerminal> getAvailableCardReader() throws CardException
    {
        TerminalFactory factory = TerminalFactory.getDefault();
        terminalList = factory.terminals().list();
        return terminalList;
    }
    
    public int getSW1()
    {
        return respAPDU.getSW1();
    }
    
    public int getSW2()
    {
        return respAPDU.getSW2();
    }
    public int getSW1SW2()
    {
        return respAPDU.getSW();
    }
    
    public int getResponseDataLength()
    {
        return respAPDU.getNr();
    }
    
    public void sendAPDU(byte[] command)
    {
        try{
            respAPDU = channel.transmit(new CommandAPDU(command));
        }catch(CardException e)
        {
        }
    }
    
    public byte[] getResponseData()
    {
        return respAPDU.getData();
    }
}
