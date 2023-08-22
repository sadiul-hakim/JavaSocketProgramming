package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriter implements Runnable{
    private final Socket socket;
    private PrintWriter writer;

    public ClientWriter(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("Inside client writer....");
        Scanner input = new Scanner(System.in);
        while (true){
            String msg = input.nextLine();

            writer.println(msg);
            writer.flush();
        }
    }
}
