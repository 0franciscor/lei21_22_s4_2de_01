package httpServerAjax;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HTTPServerAjaxDashboard {

    static private final String BASE_FOLDER="base.daemon.webServer/src/main/java/httpServerAjax/www";
    static private ServerSocket sock;
    static private final int PORT = 83;


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