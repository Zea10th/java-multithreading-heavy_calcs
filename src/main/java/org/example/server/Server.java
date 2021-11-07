package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public static final int PORT = 60666;

    public void startServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String request = in.readLine();
        Integer number;

        try {
            number = Integer.parseInt(request);
            out.printf("Server returned: %d", getFNumber(number));
        } catch (NumberFormatException e) {
            out.println(e.getMessage());
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getFNumber(int number) {
        if (number <= 1) {
            return number;
        } else {
            return getFNumber(number - 1) + getFNumber(number - 2);
        }
    }
}
