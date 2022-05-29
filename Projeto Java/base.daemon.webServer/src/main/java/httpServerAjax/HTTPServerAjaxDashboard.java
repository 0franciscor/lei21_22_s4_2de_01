package httpServerAjax;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



/**
 *
 * @author ANDRE MOREIRA (asc@isep.ipp.pt)
 */
public class HTTPServerAjaxDashboard {

    static private final String BASE_FOLDER="www";
    static private ServerSocket sock;
    static private final int PORT = 100;


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
        String textHtml = "<p> Olá, meu nome é manuela </p>";
        /*
        List<String> listOfAgvs = controller.getAGVInfo();

        System.out.println("CHEGUEI" + listOfAgvs);

        String textHtml = "<hr><p>";
        for(int i=0; i<listOfAgvs.size(); i++) {

            String[] arrOfStr = listOfAgvs.get(i).split(",", 3);

            textHtml += "AGV ID - " + arrOfStr[0] + "\nAGV Position - " + arrOfStr[1] + "\nAGV Status - " + arrOfStr[2] + "\n";

        }

        textHtml += "</p></hr>";
        textHtml = textHtml + "</ul><hr><p>HTTP server accesses counter: " + accessesCounter + "</p><hr>";
        return textHtml;
        */
        return textHtml;
    }

}