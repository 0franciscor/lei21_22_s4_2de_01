package eapli.base.orderServer.tcp;

import eapli.base.AppSettings;
import eapli.base.Application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpOrderSrv {

    static AppSettings app = Application.settings();
    static final int serverPortProperties = app.getServerPortKey();

    static ServerSocket sock;


    public static void main(String[] args) throws Exception {
        Socket cliSock;

        try { sock = new ServerSocket(9999); }
        catch(IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while(true) {
            cliSock=sock.accept();
            new Thread(new TcpOrderSrvThread(cliSock)).start();
        }
    }

     static class TcpOrderSrvThread implements Runnable {

        private Socket s;

        public TcpOrderSrvThread(Socket cli_s) {
            s=cli_s;
        }

        @Override
        public void run() {

        }
    }
}
