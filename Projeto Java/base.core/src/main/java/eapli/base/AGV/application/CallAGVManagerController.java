package eapli.base.AGV.application;

import eapli.base.AGV.domain.AGV;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CallAGVManagerController {

    public static final int VERSION = 1;

    public static final int CALL_FIFO = 1;

    public static final int DASHBOARD_REQUEST = 2;

    private Socket sock;

    private PrintWriter output;

    private BufferedReader input;

    public void connectDaemon(final String address, final int port) throws IOException {
        InetAddress serverIP = InetAddress.getByName(address);

        sock = new Socket(serverIP, port);
        output = new PrintWriter(sock.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        System.out.printf("Connected to {%s}\n", address);
    }

    public void callFIFO(){
        String request = String.format("%d,%d,0", VERSION, CALL_FIFO);
        output.println(request);
    }

    public List<String> getAGVInfo() throws IOException {
        List<String> agvInfoList = new ArrayList<>();

        for(AGV agv : PersistenceContext.repositories().agv().findAll()){
            String agvId = agv.getAgvId().getAGVId();
            String request = String.format("%d,%d,%d,%s", VERSION, DASHBOARD_REQUEST, agvId.length(), agvId);
            output.println(request);
            agvInfoList.add(input.readLine());
        }
        return agvInfoList;
    }

    public void closeConnection() throws IOException {
        sock.close();
    }
}