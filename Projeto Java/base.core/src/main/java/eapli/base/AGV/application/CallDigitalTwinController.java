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

    public boolean connectDaemon(final int port) throws IOException {
        try {
            sock = getClientSocket(port);
            sock.startHandshake();
        } catch (final IOException ignored) {}

        output = new DataOutputStream(sock.getOutputStream());
        input = new DataInputStream(sock.getInputStream());

        return testConnection();
    }

    public String getAGVInfo(String agvId) throws IOException {
        byte[] message = {(byte) 0, (byte) CallAGVManagerController.DASHBOARD_REQUEST, (byte) agvId.length(), (byte) 0};
        output.write(message);
        output.write(agvId.getBytes());

        var array= input.readNBytes(4);
        int length = array[2] + 256*array[3];
        var info = new String(input.readNBytes(length));

        return info;
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

    public boolean testConnection() throws IOException {
        byte code = 0;
        byte[] testArray = new byte[]{code, code, code, code};
        output.write(testArray);

        var ackArray = input.readNBytes(4);

        return 2 == ackArray[1];
    }

    public void closeConnection() throws IOException {
        byte code = 0;
        byte[] testArray = new byte[]{code, (byte) 1, code, code};
        output.write(testArray);

        var ackArray = input.readNBytes(4);

        if(ackArray[1] == 2)
            sock.close();
    }
}