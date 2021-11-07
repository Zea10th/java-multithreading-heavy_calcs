package org.example;

import org.example.server.Server;

public class ServerApp {
    public static void main(String[] args) {
        Thread server = new Thread(new Server());

        System.out.println("Server started.");
        server.start();
        System.out.println("Server stopped.");
    }
}
