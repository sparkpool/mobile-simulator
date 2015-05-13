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
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author user
 */
public class BasicIO {
    
    private static BasicIO basicIO;
    
    private List<CardTerminal> terminalList= null;
    
    Card card;
    CardChannel channel;
    private ResponseAPDU respAPDU;
    
    public static BasicIO getBasicIOInstance()
    {
        if(basicIO == null)
        {
            basicIO = new BasicIO();
        }
        return basicIO;
    }

    
    public int connetWithCard(int readerNumber)
    {  
        try
        {
            CardTerminal terminal = terminalList.get(readerNumber);
            card = terminal.connect("T=0");
            channel = card.getBasicChannel();
            
        }catch(CardException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void disconnectwithCard(int cardReaderId)
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
    
    public String getATR()
    {
        return DatatypeConverter.printHexBinary(card.getATR().getBytes());
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
        try
        {
            respAPDU = channel.transmit(new CommandAPDU(command));
        }
        catch(CardException e)
        {
            e.printStackTrace();
        }
    }
    
    public byte[] getResponseData()
    {
        return respAPDU.getData();
    }
}
