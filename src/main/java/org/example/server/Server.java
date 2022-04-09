package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    public static final int PORT = 60666;
    public static final int DELAY = 2000;

    public void startServer() throws InterruptedException {

        System.out.println("Server started.");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (!clientSocket.isClosed()) {
                System.out.println("Getting request.");

                String entry = in.readLine();

                if (entry.equalsIgnoreCase("quit")) {
                    System.out.println("Client initialize server termination ...");
                    out.flush();
                    Thread.sleep(DELAY);
                    break;
                }

                out.println("Server returned: " + getFNumber(Integer.parseInt(entry)));
            }

            System.out.println("Client disconnected.");

            in.close();
            out.close();
            clientSocket.close();

            System.out.println("Job is done!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Server stopped.");
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (InterruptedException e) {
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
