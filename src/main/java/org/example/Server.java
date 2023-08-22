package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Map<String,Socket> clientMap = new HashMap<>();
    private ServerSocket server;
    private Socket connection;

    public void startConnection(int port){
        if (server != null){
            System.out.println("Server running on port : "+server.getLocalPort());
            return;
        }
        try {
            server = new ServerSocket(port);
            System.out.println("Server is listening on port : "+port);

            ExecutorService service = Executors.newFixedThreadPool(2);

            while(true){
                connection = server.accept();

                InetAddress inetAddress = connection.getLocalAddress();
                System.out.println("Connected with : "+inetAddress.getHostName());

                ServerCommunication communication = new ServerCommunication(connection);

                service.execute(communication);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() throws IOException {
        server.close();
        connection.close();
    }

    public static void main(String[] args) {
        Server serverTest = new Server();
        serverTest.startConnection(8090);
    }
}
