package httpServerAjax;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HTTPServerAjaxDashboard {

    static private final String BASE_FOLDER="base.daemon.webServer/src/main/java/httpServerAjax/www";
    static private ServerSocket sock;
    static private final int PORT = 81;


    public static void main(String[] args) throws IOException {
        Socket cliSock;
        try {
            sock = new ServerSocket(PORT);
        }
        catch(IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            HTTPAjaxDashboardRequest req = new HTTPAjaxDashboardRequest(cliSock, BASE_FOLDER);
            req.start();
            incAccessesCounter();
        }

    }

    private static int accessesCounter;

    private static synchronized void incAccessesCounter() { accessesCounter++; }

    public static synchronized String getData(String data) throws IOException {

        String textHtml = "<p>";
        String data1[] = data.split(",");
        int count = 0;
        while ((data1.length - 3) > count){
            textHtml += "AGV ID - " + data1[count] + "\nAGV Position - " + data1[count + 1] + "\nAGV Status - " + data1[count + 2] + "\n";
            count += 3;
        }

        textHtml += "</p>";
        textHtml = textHtml + "</ul><hr><p>HTTP server accesses counter: " + accessesCounter + "</p><hr>";

        System.out.println(textHtml);
        return textHtml;

    }

}