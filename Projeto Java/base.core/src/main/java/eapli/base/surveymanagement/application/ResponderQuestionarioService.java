package eapli.base.surveymanagement.application;

import eapli.base.MessageUtils;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.shoppingcarmanagement.application.AddProductToShoppingCarService;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

public class ResponderQuestionarioService {

    private static class ClienteSocket {
        static final int SERVER_PORT=10000;
        static final String KEYSTORE_PASS="forgotten";
        private static final String TRUSTED_STORE = "C:\\Users\\arian\\OneDrive - Instituto Superior de Engenharia do Porto\\Desktop\\lei21_22_s4_2de_01\\Projeto Java\\certificates\\clientOrder_J.jks";

        private SSLSocket sock;
        private InetAddress serverIP;
        private DataOutputStream sOutData;
        private DataInputStream sInData;

        public void connect(final String address, final int port) throws IOException {

            // Trust these certificates provided by servers
            System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
            System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

            // Use this certificate and private key for client certificate when requested by the server
            System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
            System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

            try {
                serverIP = InetAddress.getByName(address);
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server specified: " + serverIP);
                System.exit(1);
            }

            try {
                sock = (SSLSocket) sf.createSocket(serverIP,SERVER_PORT); }
            catch(IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            System.out.println("Connected to: " + serverIP + ":" + 10001);

            sock.startHandshake();

            sOutData = new DataOutputStream(sock.getOutputStream());
            sInData = new DataInputStream(sock.getInputStream());
        }

        public void stop() throws IOException {
            sock.close();
        }
    }

    public Iterable<QuestionnaireDTO> getUnansweredCustomerSurveys(String clientEmail) {
        Iterable<QuestionnaireDTO> questionnaireDTOS = null;

        try {
            final var socket = new ClienteSocket();
            socket.connect(getAddress(), getPort());

            try {
                if (MessageUtils.testCommunicationWithServer(socket.sOutData,socket.sInData)) {
                    MessageUtils.writeMessageWithData((byte) 7, clientEmail, socket.sOutData);

                    // mostrar os questionários
                    ObjectInputStream sInputObject = new ObjectInputStream(socket.sock.getInputStream());
                    questionnaireDTOS = (Iterable<QuestionnaireDTO>) sInputObject.readObject();

                    if (MessageUtils.wantsToExit(socket.sOutData,socket.sInData)) {
                        socket.stop();

                    } else {
                        System.out.println("==> ERROR: Erro no pacote do Servidor");

                    }
                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");
                }
            } catch (IOException e) {
                System.out.println("==> ERROR: Falha durante a troca de informação com o server");
            } finally {
                try {
                    socket.stop();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }
            return questionnaireDTOS;
        } catch (Exception e) {
            System.out.println("Server down");
            System.out.println(e.getMessage());
        }
        return questionnaireDTOS;
    }


    private int getPort() {
        return 10001;
    }

    private String getAddress() {
        return "localhost";
    }

}
