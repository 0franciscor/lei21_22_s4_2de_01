package httpServerAjax;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.net.InetAddress;


/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HTTPSServerAjaxDashboard {

    static private final String BASE_FOLDER="base.daemon.webServer/src/main/java/httpServerAjax/www";
    static private SSLServerSocket sock;
    static private final int PORT = 83;

    private static final String TRUSTED_STORE = "server_J.jks";
    private static final String STORE_PATH = "base.daemon.webServer/src/main/resources/" + TRUSTED_STORE;
    private static final String KEYSTORE_PASS="forgotten";

    public static void main(String[] args) throws IOException {

        SSLSocket cliSock;

        System.setProperty("javax.net.ssl.trustStore", STORE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        System.setProperty("javax.net.ssl.keyStore", STORE_PATH);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(PORT);
        }
        catch(IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while(true) {
            cliSock = (SSLSocket) sock.accept();
            HTTPAjaxDashboardRequest req = new HTTPAjaxDashboardRequest(cliSock, BASE_FOLDER);
            req.start();
            incAccessesCounter();
        }

    }

    private static int accessesCounter;
    private static String data;


    private static synchronized void incAccessesCounter() { accessesCounter++; }

    public static synchronized void getData(String dataAux) {
        data = dataAux;
    }

    public static synchronized String updateData(){
        String textHtml = "";

        if (!data.isBlank()){
            String[] data1 = data.split(",");
            int count = 0;
            while ((data1.length - 3) > count){
                textHtml += "<div style=\"border:1px solid black\"> <center> AGV ID - " + data1[count] + "<br> AGV Position - " + data1[count + 1] + "<br>AGV Status - " + data1[count + 2] + "<br> </center> </div> <br>";
                count += 3;
            }
        }
        textHtml += textHtml + "</ul><hr><p>HTTP server accesses counter: " + accessesCounter + "</p><hr>";


        return textHtml;
    }

}