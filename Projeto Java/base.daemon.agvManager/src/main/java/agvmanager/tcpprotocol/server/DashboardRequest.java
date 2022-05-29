package agvmanager.tcpprotocol.server;

import eapli.base.AGV.application.AGVManagerControllerImplementation;
import eapli.base.AGV.domain.AGV;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DashboardRequest extends AGVManagerProtocolRequest{

    public DashboardRequest(String inputRequest) {
        super(new AGVManagerControllerImplementation(), inputRequest);
    }

    @Override
    public String execute() {

        try {
            final Iterable<AGV> agvs = controller.getAGVsInformationForDashboard();
            return buildResponse(agvs);
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }

    public String buildResponse(final Iterable<AGV> agvs) throws IOException {

        File csvFile = new File("agvInformation.csv");


        if (csvFile.exists()) {

            csvFile.delete();
            try {
                csvFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fileWriter = new FileWriter(csvFile);


        for (final AGV each : agvs) {
            fileWriter.write(each.getAgvId().getAGVId() + "," + each.getAgvPosition().getAgvPosition() + "," + each.getAgvStatus().obtainStatus().name()+ "\n");
          
        }

        String response = String.format("%d,%d,%d,%d,%d");

        return response;
    }
}
