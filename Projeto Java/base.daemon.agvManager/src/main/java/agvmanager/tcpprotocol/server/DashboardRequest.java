package agvmanager.tcpprotocol.server;

import eapli.base.AGV.application.CallDigitalTwinController;

import java.io.IOException;

public class DashboardRequest extends AGVManagerProtocolRequest{

    private final String agvId;
    public DashboardRequest(String inputRequest, String agvId) {
        super(new CallDigitalTwinController(), inputRequest);
        this.agvId = agvId;
    }

    @Override
    public String execute() {

        try {
            return buildResponse(agvId);
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }

    public String buildResponse(final String agvId) throws IOException {
        callTwinController.connectDaemon(8891);
        String info = null;
        try{
            info = callTwinController.getAGVInfo(agvId);
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            callTwinController.closeConnection();
        }
        return info;
    }
}
