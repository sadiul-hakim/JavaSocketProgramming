package org.example;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket client;

    public Socket start() {
        try {
            client = new Socket("127.0.0.1", 8090);
            return client;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stop() throws IOException {
        client.close();
    }

    public static void main(String[] args) throws IOException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        Client clientTest = new Client();
        Socket clientConnection = clientTest.start();

        if(clientConnection != null){
            ClientReader reader = new ClientReader(clientConnection);
            ClientWriter writer = new ClientWriter(clientConnection);

            service.execute(writer);
            service.execute(reader);
        }

    }
}
