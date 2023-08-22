package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCommunication implements Runnable {
    private final Socket socket;
    private final PrintWriter writer;
    private final BufferedReader reader;

    public ServerCommunication(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        System.out.println("Inside server connection.......");
        while (true){
            try {
                String msg = reader.readLine();
                System.out.println(msg);

                String result = "Received msg : "+msg;

                writer.println(result);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private void reader(BufferedReader reader){
        while (true){
            reader.lines().forEach(System.out::println);
        }
    }
    private void writer(PrintWriter writer){
        while (true){

        }
    }
}
