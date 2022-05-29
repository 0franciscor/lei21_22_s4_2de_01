package agvmanager.tcpprotocol.server;

import eapli.base.AGV.application.CallAGVManagerController;
import eapli.base.AGV.application.DashBoardController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DashboardRequest extends AGVManagerProtocolRequest{

    private final String agvId;
    public DashboardRequest(String inputRequest, String agvId) {
        super(new DashBoardController(), inputRequest);
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
        InetAddress serverIP = InetAddress.getByName("localhost");
        int port = 8891;

        Socket socket = new Socket(serverIP, port);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.printf("Connected to {%s}\n", serverIP);

        String requestedAGV = String.format("%d,%d,%d,%s", CallAGVManagerController.VERSION, CallAGVManagerController.DASHBOARD_REQUEST, agvId.length(), agvId);

        output.println(requestedAGV);
        String receivedInfo = input.readLine();

        socket.close();

        return receivedInfo;
    }
}
