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
public class FileSystemCommands extends Command
{
    private static FileSystemCommands fileInstance;
 
    /**
     * This method used to get the singleton instance of the FileSystemCommands class
     * @return : Singleton instance of the class
     */
    public static FileSystemCommands getFileSystemInstance()
    {
        if(fileInstance == null)
        {
            fileInstance = new FileSystemCommands();                    
        }
        return fileInstance;
    }
    
    /**
     * This method used to select the file 
     * @param fileId : Id of the file to select
     * @return: Return the status word of the command
     */
    public String selectFile(String fileId)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "A4";
        Command.P1="00";
        Command.P2="00";        
        int trLength = fileId.length()/2;
        Command.P3 = convertToHextString(trLength);
        Command.DATA = fileId;
        //System.out.println(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        if(validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA))
        {
            return executeCommand();
        }
        else
        {
            return null;
        }
       
        
    }
    
    /**
     * This method used to read the binary file 
     * @param startOffset: Offset of the file from where data will be read
     * @param length: Length of the data to read
     * @return : Return the status word of the command execution.
     */
    public String readBinary(int startOffset, int length)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "B0";
        int temp = startOffset/255;
        int temp1 = startOffset%255;
        Command.P1=convertToHextString(temp);
        Command.P2=convertToHextString(temp1);
        Command.P3= convertToHextString(length);
        Command.DATA ="";
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    /**
     * This method used to read the record of an Linear fixed file
     * @param mode: Mode of the read record command
     * @param recordNumber: Record number to read
     * @param recordLength: Length of the record
     * @return : Return the Status word of the command
     */
    public String readReacord(int mode, int recordNumber, int recordLength)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "B2";
        Command.P1=convertToHextString(recordNumber);
        Command.P2=convertToHextString(mode);
        Command.P3= convertToHextString(recordLength);
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    /**
     * This method used to update the record of the linear fixed file
     * @param mode: Mode of the update record command
     * @param recordNumber: Record number to update
     * @param recordLength: Length of the record
     * @param recordData: Data to update the record, data should be in hex format
     * @return : Return the status word of the command
     */
    public String updateRecord(int mode, int recordNumber, int recordLength, String recordData)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "DC";
        Command.P1=convertToHextString(recordNumber);
        Command.P2=convertToHextString(mode);
        Command.P3= convertToHextString(recordLength);
        Command.DATA = recordData;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    /**
     * This method used to update the transparent file based on the provided parameter
     * @param startOffset: Start offset of the file from where data need to be updated
     * @param length: Length of the data to update
     * @param data: Data to update the transparent file
     * @return : Return the status word of the command
     */
    public String updateBinary(int startOffset, int length, String data)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "D6";
        int temp = startOffset/255;
        int temp1 = startOffset%255;
        Command.P1=convertToHextString(temp);
        Command.P2=convertToHextString(temp1);
        Command.P3= convertToHextString(length);
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    /**
     * This method used to send the status command
     * @param length: Length of the data needed in the response of the status command
     * @return : Return the status word of the command
     */
    public String status(int length)
    {   
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "F2";
        Command.P1="00";
        Command.P2="00";
        Command.P3= convertToHextString(length);
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
     
    /**
     * This method used to issue the seek command
     * @param typeMode: Mode of the seek command
     * @param patternLength: Length of the pattern data
     * @param pattern: Pattern in the hex data format
     * @return : return the status  word of the command
     */
    public String seek(String typeMode, int patternLength, String pattern)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "A2";
        Command.P1="00";
        Command.P2=typeMode;
        int trLength = pattern.length()/2;
        Command.DATA = pattern;
        Command.P3= convertToHextString(trLength);
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    /**
     * This method used to send the increase command to increase the content of the cyclic file
     * @param length: Length of the data to increase, it must be 3
     * @param data: Data 
     * @return : return the status word
     */
    public String increase(int length, String data)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "32";
        Command.P1="00";
        Command.P2="00";
        Command.P3= convertToHextString(length);
        Command.DATA = data;
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    /**
     * This method used to issue the get Response command
     * @param length: Length of data which will be fetched
     * @return : Return the status word
     */
    public String getResponse(int length)
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "C0";
        Command.P1="00";
        Command.P2="00";
        Command.P3= convertToHextString(length);
        Command.DATA="";
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3);
        return executeCommand();
    }
    
    public String rehabilitate()
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "44";
        Command.P1="00";
        Command.P2="00";
        Command.P3="00";
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
    
    public String Stinvalidate()
    {
        Command.CLS = operationMode.equals("2G")?"A0":"00";
        Command.INS = "04";
        Command.P1="00";
        Command.P2="00";
        Command.P3="00";
        validateCommand(Command.CLS+Command.INS+Command.P1+Command.P2+Command.P3+Command.DATA);
        return executeCommand();
    }
}
