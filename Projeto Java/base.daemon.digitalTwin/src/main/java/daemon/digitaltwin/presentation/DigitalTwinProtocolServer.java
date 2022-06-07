package daemon.digitaltwin.presentation;

import digitaltwin.tcpprotocol.server.DigitalTwinProtocolRequest;
import digitaltwin.tcpprotocol.server.RequestMessageParser;
import eapli.base.MessageUtils;
import eapli.base.orderServer.tcp.utils.ConstantsServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server socket for booking daemon using the CSV-based protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
public class DigitalTwinProtocolServer {

    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinProtocolServer.class);

    /**
     * Client socket.
     *
     * @author Paulo Gandra Sousa 01/06/2020
     */
    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        private final RequestMessageParser parser;

        public ClientHandler(final Socket socket) {
            this.clientSocket = socket;
            parser = new RequestMessageParser();
        }

        @Override
        public void run() {
            final var clientIP = clientSocket.getInetAddress();
            LOGGER.debug("Accepted connection from {}:{}", clientIP.getHostAddress(), clientSocket.getPort());

            try {
                var in = new DataInputStream(clientSocket.getInputStream());
                var out = new DataOutputStream(clientSocket.getOutputStream());

                var inputArray = in.readNBytes(4);

                if (inputArray[1] == ConstantsServer.START_CODE) {
                    LOGGER.debug("[SUCCESS] TEST CODE RECEIVED");

                    MessageUtils.writeMessage((byte) 2, out);
                    LOGGER.debug("[ACKNOWLEDGMENT] SENDING ACKNOWLEDGMENT MESSAGE");
                }

                if (inputArray[1] == 3) {

                    int length = inputArray[2] + (256 * inputArray[3]);

                    String extraInfo = null;
                    byte[] string;
                    if (length > 0) {
                        string = in.readNBytes(length);
                        extraInfo = new String(string);
                    }

                    LOGGER.debug("Received message:----{}----", inputArray);
                    final DigitalTwinProtocolRequest request = parser.parse(inputArray, extraInfo);
                    String response = request.execute();
                    byte[] array = calculateSize(response);

                    inputArray[2] = array[0];
                    inputArray[3] = array[1];

                    out.write(inputArray);
                    if(response != null)
                        out.write(response.getBytes());

                    LOGGER.debug("Sent message:----\n{}\n----", response);
                }

                if (inputArray[1] == ConstantsServer.FINISH_CODE) {
                    LOGGER.debug("[SUCCESS] DISCONNECT CODE RECEIVED");

                    MessageUtils.writeMessage((byte) 2, out);
                    LOGGER.debug("[ACKNOWLEDGMENT] SENDING ACKNOWLEDGMENT MESSAGE");
                }


            } catch (final IOException e) {
                LOGGER.error(e);
            } finally {
                try {
                    clientSocket.close();
                    LOGGER.debug("Closing client socket {}:{}", clientIP.getHostAddress(), clientSocket.getPort());
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket {}:{}", clientIP.getHostAddress(), clientSocket.getPort(), e);
                }
                clientSocket = null;
            }
        }
    }


    /**
     * @param string created response string
     *
     * Method used to calculate the size of the response
     *
     * @return array containing the sizes
     */
    private static byte[] calculateSize(final String string){
        byte d1 = 0, d2 = 0, stringSize = Byte.parseByte(String.valueOf(string.length()));

        while(stringSize > 0) {
            if (stringSize > 256) {
                d2 = (byte) (stringSize / 256);
                stringSize %= 256;
            } else {
                d1 = stringSize;
                break;
            }
        }
        return new byte []{d1, d2};
    }

    /**
     * Wait for connections.
     * <p>
     * Suppress warning java:S2189 - Loops should not be infinite
     *
     * @param port
     */
    @SuppressWarnings("java:S2189")
    private void listen(final int port) {
        try (var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Wait for connections.
     *
     * @param port of the socket
     * @param blocking
     *            if {@code false} the socket runs in its own thread and does not block calling
     *            thread.
     */
    public void start(final int port, final boolean blocking) {
        if (blocking) {
            listen(port);
        } else {
            new Thread(() -> listen(port)).start();
        }
    }
}

