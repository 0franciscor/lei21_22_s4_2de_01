package eapli.base.AGV.application;
import httpServerAjax.HTTPMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;

public class DashboardService {
    private final CallAGVManagerController controller = new CallAGVManagerController();
    static private Socket sock;
    static private InetAddress serverIP;
    static private int serverPort;
    static private DataOutputStream sOut;
    static private DataInputStream sIn;
    HTTPMessage request = new HTTPMessage();
    private StringBuilder string = new StringBuilder("/agvData/");


    public void sendAGVInformation() throws IOException {

        controller.connectDaemon("localhost", 8890);
        List<String> strings = controller.getAGVInfo();


        for (String s : strings){
            string.append(s);
        }

        request.setRequestMethod("PUT");
        request.setURI(string.toString());


        try{
            request.send(sOut);
        } catch(Exception e){
            e.printStackTrace();
        }
        controller.closeConnection();
    }

    public void openDashboard() throws IOException {

        try { serverIP = InetAddress.getByName("localhost"); }
        catch(UnknownHostException ex) {
            System.out.println("Invalid SERVER-ADDRESS.");
            System.exit(1);
        }

        try { serverPort = 83; }
        catch(NumberFormatException ex) {
            System.out.println("Invalid SERVER-PORT.");
            System.exit(1);
        }

        try { sock = new Socket(serverIP, serverPort); }
        catch(IOException ex) {
            System.out.println("Failed to connect to provided SERVER-ADDRESS and SERVER-PORT.");
            System.out.println("Application aborted.");
            System.exit(1);
        }


        sOut = new DataOutputStream(sock.getOutputStream());

        sIn = new DataInputStream(sock.getInputStream());

        sendAGVInformation();


        java.awt.Desktop.getDesktop().browse(URI.create("http://localhost:83"));
    }
}
