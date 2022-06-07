package eapli.base.AGV.application;

import eapli.base.MessageUtils;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.io.*;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class CallDigitalTwinController {

    private static final String TRUSTED_STORE = "keystore.jks";

    private static final String STORE_PATH = "base.daemon.digitalTwin/src/main/resources/" + TRUSTED_STORE;

    private static final String KEYSTORE_PASS="password";

    private SSLSocket sock;

    private DataOutputStream output;

    private DataInputStream input;

    public static void main(String[] args) throws IOException {
        CallDigitalTwinController controller = new CallDigitalTwinController();
        System.out.println(controller.connectDaemon(8891));

        String agv = controller.getAGVInfo("12345678");

        controller.closeConnection();
    }

    public boolean connectDaemon(final int port) throws IOException {
        try {
            sock = getClientSocket(port);
            //sock.startHandshake();
        } catch (final IOException ignored) {}

        output = new DataOutputStream(sock.getOutputStream());
        input = new DataInputStream(sock.getInputStream());

        return MessageUtils.testCommunicationWithServer(output, input);
    }

    private SSLSocket getClientSocket(final int port) throws UnknownHostException {
        final var fileName = new File(STORE_PATH).getAbsolutePath();

        System.setProperty("javax.net.ssl.trustStore", fileName);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore", fileName);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        var sslF = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sock = null;

        var serverIP = InetAddress.getByName("localhost");

        try {
            sock = (SSLSocket) sslF.createSocket(serverIP, port);
        } catch(IOException ex) {
            System.out.println("Client failed to connect to local port " + port);
            System.exit(1);
        }
        return sock;
    }

    public String getAGVInfo(String agvId) throws IOException {
        MessageUtils.writeMessageWithData((byte) CallAGVManagerController.DASHBOARD_REQUEST, agvId, output);
        var response = input.readNBytes(4);
        var message = MessageUtils.getDataFromMessage(response, input);

        return message;
    }


    public void closeConnection() throws IOException {
        sock.close();
    }
}
