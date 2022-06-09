package eapli.base.AGV.application;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehouse.domain.AGVDock;
import eapli.base.warehouse.domain.WarehousePlant;
import eapli.base.warehouse.repositories.AGVDockRepository;
import eapli.base.warehouse.repositories.WarehouseRepository;
import httpServerAjax.HTTPMessage;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class DashboardService {

    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouse();
    private final AGVDockRepository agvDockRepository = PersistenceContext.repositories().dock();

    static private SSLSocket sock;
    static private InetAddress serverIP;
    static private int serverPort;
    static private DataOutputStream sOut;
    static private DataInputStream sIn;

    static private String WAREHOUSE_ID = "1";

    private static final String TRUSTED_STORE = "client3_J.jks";
    private static final String STORE_PATH = "base.daemon.webServer/src/main/resources/" + TRUSTED_STORE;
    private static final String KEYSTORE_PASS="forgotten";

    HTTPMessage request = new HTTPMessage();
    private StringBuilder string = new StringBuilder("/dashboardData/");

    public void sendInformationToDashboard(){

        request.setRequestMethod("PUT");
        request.setURI(string.toString());
        try{
            request.send(sOut);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void warehouseInformation(String id){
        WarehousePlant warehousePlant = warehouseRepository.findById(id).getWarehousePlant();
        string.append(warehousePlant.getLength()).append(",");
        string.append(warehousePlant.getWidth()).append(",");
        string.append(warehousePlant.getSquare()).append(",");
    }


/*    public void AgvDockInformation(){
        for (AGVDock agvDock: agvDockRepository.findAll()){
            string.append(";");
            string.append(agvDock.getBegin().getBeginLSquare());
            string.append(",");
            string.append(agvDock.getEnd());
            string.append(",");
            string.append((agvDock.identity()));
            string.append(",");
        }
    }

    public void sendAisle(){
        for (Aisle aisle: aisleRepository.findAll()){
            string.append("/");
            string.append(aisle.AisleBeginLSquare().BeginLSquare());
            string.append(",");
            string.append(aisle.AisleEndLSquare().EndLSquare());
            string.append(",");
            string.append(aisle.AisleBeginWSquare().BeginWSquare());
            string.append(",");
            string.append(aisle.AisleEndWSquare().EndWSquare());
            string.append(",");
            string.append(aisle.AisleDepthLSquare().DepthLSquare());
            string.append(",");
            string.append(aisle.AisleDepthWSquare().DepthWSquare());
            string.append(",");
        }
    }

    public void sendAGV(){
        for (AGV agv: agvRepository.findAll()){
            string.append("?");
            string.append(agv.AgvDock().identity().Id());
            string.append(",");
        }
    }

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
    }*/

    public void openDashboard() throws IOException {

        System.setProperty("javax.net.ssl.trustStore", STORE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore", STORE_PATH);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName("localhost");
        }
        catch(UnknownHostException ex) {
            System.out.println("Invalid SERVER-ADDRESS.");
            System.exit(1);
        }

        try { serverPort = 83; }
        catch(NumberFormatException ex) {
            System.out.println("Invalid SERVER-PORT.");
            System.exit(1);
        }

        try {
            sock =  (SSLSocket) factory.createSocket(serverIP,serverPort);
        }
        catch(IOException ex) {
            System.out.println("Failed to connect to provided SERVER-ADDRESS and SERVER-PORT.");
            System.out.println("Application aborted.");
            System.exit(1);
        }


        sOut = new DataOutputStream(sock.getOutputStream());

        sIn = new DataInputStream(sock.getInputStream());


        java.awt.Desktop.getDesktop().browse(URI.create("http://localhost:83"));
    }
}
