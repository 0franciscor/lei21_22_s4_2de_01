package eapli.base.AGV.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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

    public void closeConnection() throws IOException {
        sock.close();
    }
}