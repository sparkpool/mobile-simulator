/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sim.toolkit.envelope;

/**
 *
 * @author user
 */
public class EnvelopePreparator {
    
    private static EnvelopePreparator instance;
    
    private EnvelopePreparator(){}
    
    public static EnvelopePreparator getInstance()
    {
        if(instance == null )
        {
            instance = new EnvelopePreparator();
        }
        return instance;
    }
    
    public String prepareEnvMenuSelection(String itemId, boolean isHelpRequired)
    {
        return null;
    }
    
    public String prepareEnvCallControl(String addressTLV, String subAddressTLV,String ccpTLV1, String ccpTLV2, String lociInfo, String bcRepeaterTLv)
    {
        return null;
    }
    
    public String prepareEnvTimerExp(int timerId, String timerValueTLV)
    {
        return null;
    }
    
    public String prepareMTEnvelope(String transactionId, String addressTLV, String subAddressTLV)
    {
        return null;
    }
    
    public String prepareEnvCallDisconnected(String transactionId, String cause)
    {
        return null;
    }
    
    public String prepareEnvLocationStatus(byte locationStatus, String locationInfoTLV)
    {
        return null;
    }
    
    public String prepareEnvUserActivity()
    {
        return null;
    }
    
    public String prepareEnvIdleScreen(String cardReaderStatus)
    {
        return null;
    }
    
    public String prepareEnvLanguageSelection()
    {
        return null;
    }
    
    public String PrepareEnvBrowserTermination(String browserTerminationCause)
    {
        return null;
    }
    
    public String PrepareEnvDataAvailable(String channelDataTLV, String channelStatusTLV)
    {
        return null;
    }
    
    public String prepareEnvChannelStatus(String channelStatusTLV, String bearerDescTLV, String otherAddressTLV)
    {
        return null;
    }
    
    public String prepareEnvAccessTechChange(String accessTech)
    {
        return null;
    }
    
    public String prepareEnvDisplayParaChange(String displayParaTLV)
    {
        return null;
    }
    
    
    public String prepareEnvLocalConnection(String serviceRecordTLV, String remoteEntityAddTLV, String uiccTransportLevelTLV, String remoteEntityTransportTLV )
    {
        return null;
    }
    
    public String prepareEnvNetworkSearchModeChanged(String networkSearchMode)
    {
        return null;
    }
    
    public String prepareEnvBrowsingStatus(String browsingStatus)
    {
        return null;
    }
    
    public String prepareEnvFrameInformationCahnged(String frameInfoTLV)
    {
        return null;
    }
    
    public String prepareEnvHCIConnectivity()
    {
        return null;
    }
    
    public String prepareEnvPollInterval(String durationTLV)
    {
        return null;
    }
}
