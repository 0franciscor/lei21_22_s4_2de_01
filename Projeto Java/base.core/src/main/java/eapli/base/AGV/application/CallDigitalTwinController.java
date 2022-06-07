package eapli.base.AGV.application;

import eapli.base.MessageUtils;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import java.io.*;

import java.net.Socket;


public class CallDigitalTwinController {

    private static final String TRUSTED_STORE = "keystore.jks";

    private static final String STORE_PATH = "base.daemon.digitalTwin/src/main/resources/" + TRUSTED_STORE;

    private static final String KEYSTORE_PASS="password";

    private Socket sock;

    private DataOutputStream output;

    private DataInputStream input;

    public boolean connectDaemon(final int port) throws IOException {
        try {
            sock = getServerSocket(port).accept();
        } catch (final IOException ignored) {}

        output = new DataOutputStream(sock.getOutputStream());
        input = new DataInputStream(sock.getInputStream());

        return MessageUtils.testCommunicationWithServer(output, input);
    }

    private SSLServerSocket getServerSocket(final int port){
        final var fileName = new File(STORE_PATH).getAbsolutePath();

        System.setProperty("javax.net.ssl.trustStore", fileName);
        System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore", fileName);
        System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

        var sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sock = null;

        try {
            sock = (SSLServerSocket) sslF.createServerSocket(port);
            sock.setNeedClientAuth(true);
        } catch(IOException ex) {
            System.out.println("Server failed to open local port " + port);
            System.exit(1);
        }
        return sock;
    }

    public String getAGVInfo(String agvId) throws IOException {
        var sentArray = MessageUtils.writeMessageWithData((byte) CallAGVManagerController.DASHBOARD_REQUEST, agvId, output);
        var message = MessageUtils.getDataFromMessage(sentArray, input);
        closeConnection();
        return message;
    }


    public void closeConnection() throws IOException {
        sock.close();
    }
}
